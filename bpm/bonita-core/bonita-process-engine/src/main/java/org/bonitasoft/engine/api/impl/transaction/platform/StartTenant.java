/**
 * Copyright (C) 2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation
 * version 2.1 of the License.
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth
 * Floor, Boston, MA 02110-1301, USA.
 **/
package org.bonitasoft.engine.api.impl.transaction.platform;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import org.bonitasoft.engine.api.impl.NodeConfiguration;
import org.bonitasoft.engine.api.impl.TenantConfiguration;
import org.bonitasoft.engine.api.impl.transaction.SetServiceState;
import org.bonitasoft.engine.api.impl.transaction.StartServiceStrategy;
import org.bonitasoft.engine.builder.BuilderFactory;
import org.bonitasoft.engine.commons.exceptions.SBonitaException;
import org.bonitasoft.engine.commons.transaction.TransactionContent;
import org.bonitasoft.engine.execution.work.RestartException;
import org.bonitasoft.engine.execution.work.TenantRestartHandler;
import org.bonitasoft.engine.jobs.BPMEventHandlingJob;
import org.bonitasoft.engine.log.technical.TechnicalLogSeverity;
import org.bonitasoft.engine.log.technical.TechnicalLoggerService;
import org.bonitasoft.engine.platform.STenantActivationException;
import org.bonitasoft.engine.scheduler.JobRegister;
import org.bonitasoft.engine.scheduler.SchedulerService;
import org.bonitasoft.engine.scheduler.builder.SJobDescriptorBuilderFactory;
import org.bonitasoft.engine.scheduler.builder.SJobParameterBuilderFactory;
import org.bonitasoft.engine.scheduler.exception.SSchedulerException;
import org.bonitasoft.engine.scheduler.model.SJobDescriptor;
import org.bonitasoft.engine.scheduler.model.SJobParameter;
import org.bonitasoft.engine.scheduler.trigger.Trigger;
import org.bonitasoft.engine.scheduler.trigger.Trigger.MisfireRestartPolicy;
import org.bonitasoft.engine.scheduler.trigger.UnixCronTrigger;
import org.bonitasoft.engine.service.PlatformServiceAccessor;
import org.bonitasoft.engine.service.TenantServiceAccessor;

/**
 * @author Matthieu Chaffotte
 */
public final class StartTenant implements TransactionContent {

    public static final String CLEAN_INVALID_SESSIONS = "CleanInvalidSessions";

    public static final String BPM_EVENT_HANDLING = "BPMEventHandling";

    private final long tenantId;

    private final PlatformServiceAccessor platformAccessor;

    private final boolean mustRestartElements;

    private final TenantConfiguration tenantConfiguration;

    private final SchedulerService schedulerService;

    private final TechnicalLoggerService logger;

    public StartTenant(final long tenantId, final PlatformServiceAccessor platformAccessor, final TenantServiceAccessor tenantAccessor,
            final boolean mustRestartElements) {
        this.tenantId = tenantId;
        this.platformAccessor = platformAccessor;
        this.mustRestartElements = mustRestartElements;
        logger = tenantAccessor.getTechnicalLoggerService();
        tenantConfiguration = tenantAccessor.getTenantConfiguration();
        schedulerService = platformAccessor.getSchedulerService();
    }

    @Override
    public void execute() throws SBonitaException {
        platformAccessor.getTransactionService().begin();
        try {
            try {
                restartPreHandlers();
                startServices();
            } catch (final Exception e) {
                throw new STenantActivationException(e);
            }
            startEventHandling();
            registerJobs();
        } catch (final Exception e) {
            platformAccessor.getTransactionService().setRollbackOnly();
        } finally {
            platformAccessor.getTransactionService().complete();
        }

        try {
            restartPostHandlers();
        } catch (final Exception e) {
            throw new STenantActivationException(e);
        }
    }

    private void restartPreHandlers() throws Exception {
        final NodeConfiguration platformConfiguration = platformAccessor.getPlaformConfiguration();
        if (mustRestartElements && platformConfiguration.shouldResumeElements()) {
            // Here get all elements that are not "finished"
            // * FlowNodes that have flag: stateExecuting to true: call execute on them (connectors were executing)
            // * Process instances with token count == 0 (either not started again or finishing) -> same thing connectors were executing
            // * transitions that are in state created: call execute on them
            // * flow node that are completed and not deleted : call execute to make it create transitions and so on
            // * all element that are in not stable state
            final TenantServiceAccessor tenantServiceAccessor = platformAccessor.getTenantServiceAccessor(tenantId);
            for (final TenantRestartHandler restartHandler : platformConfiguration.getTenantRestartHandlers()) {
                restartHandler.beforeServicesStart(platformAccessor, tenantServiceAccessor);
            }
        }
    }

    private void startEventHandling() throws SSchedulerException {
        final String jobClassName = BPMEventHandlingJob.class.getName();
        if (schedulerService.isStarted()) {
            //            if (nodeConfiguration.shouldStartEventHandlingJob()) {
            final SJobDescriptor jobDescriptor = BuilderFactory.get(SJobDescriptorBuilderFactory.class)
                    .createNewInstance(jobClassName, BPM_EVENT_HANDLING, true)
                    .done();
            final ArrayList<SJobParameter> jobParameters = new ArrayList<SJobParameter>();
            final String cron = tenantConfiguration.getEventHandlingJobCron(); //
            final Trigger trigger = new UnixCronTrigger("UnixCronTrigger" + UUID.randomUUID().getLeastSignificantBits(), new Date(), cron,
                    MisfireRestartPolicy.NONE);
            if (logger.isLoggable(getClass(), TechnicalLogSeverity.INFO)) {
                logger.log(this.getClass(), TechnicalLogSeverity.INFO, "Starting event handling job with frequency : " + cron);
            }
            schedulerService.schedule(jobDescriptor, jobParameters, trigger);
            //            }
        } else {
            if (logger.isLoggable(ActivateTenant.class, TechnicalLogSeverity.WARNING)) {
                logger.log(ActivateTenant.class, TechnicalLogSeverity.WARNING, "The scheduler is not started: impossible to schedule job " + jobClassName);
            }
        }
    }

    private void registerJobs() {
        final List<JobRegister> jobsToRegister = tenantConfiguration.getJobsToRegister();
        for (final JobRegister jobRegister : jobsToRegister) {
            registerJob(jobRegister);
        }
    }

    private void registerJob(final JobRegister jobRegister) {
        try {
            final List<String> jobs = schedulerService.getAllJobs();
            if (!jobs.contains(jobRegister.getJobName())) {
                if (logger.isLoggable(this.getClass(), TechnicalLogSeverity.INFO)) {
                    logger.log(this.getClass(), TechnicalLogSeverity.INFO, "Register " + jobRegister.getJobDescription());
                }
                final SJobDescriptor jobDescriptor = BuilderFactory.get(SJobDescriptorBuilderFactory.class)
                        .createNewInstance(jobRegister.getJobClass().getName(), jobRegister.getJobName(), true).done();
                final ArrayList<SJobParameter> jobParameters = new ArrayList<SJobParameter>();
                for (final Entry<String, Serializable> entry : jobRegister.getJobParameters().entrySet()) {
                    jobParameters.add(BuilderFactory.get(SJobParameterBuilderFactory.class).createNewInstance(entry.getKey(), entry.getValue()).done());
                }
                final Trigger trigger = jobRegister.getTrigger();
                schedulerService.schedule(jobDescriptor, jobParameters, trigger);
            } else {
                logger.log(this.getClass(), TechnicalLogSeverity.INFO, "The " + jobRegister.getJobDescription() + " was already started");
            }
        } catch (final SSchedulerException e) {
            logger.log(this.getClass(), TechnicalLogSeverity.ERROR,
                    "Unable to register job " + jobRegister.getJobDescription() + " because " + e.getMessage());
            if (logger.isLoggable(this.getClass(), TechnicalLogSeverity.DEBUG)) {
                logger.log(this.getClass(), TechnicalLogSeverity.DEBUG, e);
            }
        }
    }

    private void startServices() throws Exception {
        final SetServiceState startService = new SetServiceState(tenantId, new StartServiceStrategy());
        startService.call();
    }

    private void restartPostHandlers() throws RestartException {
        final NodeConfiguration platformConfiguration = platformAccessor.getPlaformConfiguration();
        if (mustRestartElements && platformConfiguration.shouldResumeElements()) {
            // Here get all elements that are not "finished"
            // * FlowNodes that have flag: stateExecuting to true: call execute on them (connectors were executing)
            // * Process instances with token count == 0 (either not started again or finishing) -> same thing connectors were executing
            // * transitions that are in state created: call execute on them
            // * flow node that are completed and not deleted : call execute to make it create transitions and so on
            // * all element that are in not stable state
            final TenantServiceAccessor tenantServiceAccessor = platformAccessor.getTenantServiceAccessor(tenantId);
            for (final TenantRestartHandler restartHandler : platformConfiguration.getTenantRestartHandlers()) {
                restartHandler.afterServicesStart(platformAccessor, tenantServiceAccessor);
            }
        }
    }

}
