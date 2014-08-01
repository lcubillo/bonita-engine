/**
 * Copyright (C) 2012-2013 BonitaSoft S.A.
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
package org.bonitasoft.engine.execution.work;

import java.util.List;

import org.bonitasoft.engine.commons.exceptions.SBonitaException;
import org.bonitasoft.engine.core.process.definition.model.SFlowNodeType;
import org.bonitasoft.engine.core.process.definition.model.SProcessDefinition;
import org.bonitasoft.engine.core.process.instance.api.ActivityInstanceService;
import org.bonitasoft.engine.core.process.instance.api.GatewayInstanceService;
import org.bonitasoft.engine.core.process.instance.model.SFlowNodeInstance;
import org.bonitasoft.engine.core.process.instance.model.SGatewayInstance;
import org.bonitasoft.engine.log.technical.TechnicalLogSeverity;
import org.bonitasoft.engine.log.technical.TechnicalLoggerService;
import org.bonitasoft.engine.persistence.QueryOptions;
import org.bonitasoft.engine.service.PlatformServiceAccessor;
import org.bonitasoft.engine.service.TenantServiceAccessor;
import org.bonitasoft.engine.work.WorkRegisterException;
import org.bonitasoft.engine.work.WorkService;

/**
 * Restart flow nodes for works: {@link ExecuteFlowNodeWork} {@link ExecuteConnectorOfActivity} {@link NotifyChildFinishedWork}
 *
 * @author Baptiste Mesta
 * @author Celine Souchet
 * @author Matthieu Chaffotte
 */
public class RestartFlowNodesHandler implements TenantRestartHandler {

    @Override
    public void handleRestart(final PlatformServiceAccessor platformServiceAccessor, final TenantServiceAccessor tenantServiceAccessor) throws RestartException {
        final ActivityInstanceService activityInstanceService = tenantServiceAccessor.getActivityInstanceService();
        final WorkService workService = platformServiceAccessor.getWorkService();
        final TechnicalLoggerService logger = tenantServiceAccessor.getTechnicalLoggerService();
        try {
            QueryOptions queryOptions = QueryOptions.defaultQueryOptions();
            List<SFlowNodeInstance> sFlowNodeInstances;
            final boolean info = logger.isLoggable(getClass(), TechnicalLogSeverity.INFO);
            if (info) {
                logger.log(getClass(), TechnicalLogSeverity.INFO, "Restarting flow nodes...");
            }
            do {
                sFlowNodeInstances = activityInstanceService.getFlowNodeInstancesToRestart(queryOptions);
                queryOptions = QueryOptions.getNextPage(queryOptions);
                for (final SFlowNodeInstance sFlowNodeInstance : sFlowNodeInstances) {
                    if (sFlowNodeInstance.isTerminal()) {
                        // NotifyChildFinishedWork
                        // if it is terminal it means the notify was not called yet
                        if (info) {
                            logger.log(getClass(), TechnicalLogSeverity.INFO, "Restarting flow node (Notify...) with name = " + sFlowNodeInstance.getName()
                                    + ", and id = " + sFlowNodeInstance.getId() + " in state " + sFlowNodeInstance.getStateName());
                        }
                        workService.registerWork(WorkFactory.createNotifyChildFinishedWork(sFlowNodeInstance.getProcessDefinitionId(),
                                sFlowNodeInstance.getParentProcessInstanceId(), sFlowNodeInstance.getId(), sFlowNodeInstance.getParentContainerId(),
                                sFlowNodeInstance.getParentContainerType().name(), sFlowNodeInstance.getStateId()));
                    } else {
                        if (shouldExecuteFlownode(tenantServiceAccessor, logger, sFlowNodeInstance)) {
                            if (info) {
                                logger.log(getClass(), TechnicalLogSeverity.INFO, "Restarting flow node (Execute..) with name = " + sFlowNodeInstance.getName()
                                        + ", and id = " + sFlowNodeInstance.getId() + " in state " + sFlowNodeInstance.getStateName());
                            }
                            // ExecuteFlowNodeWork and ExecuteConnectorOfActivityWork
                            workService.registerWork(WorkFactory.createExecuteFlowNodeWork(sFlowNodeInstance.getId(), null, null,
                                    sFlowNodeInstance.getParentProcessInstanceId()));
                        } else {
                            if (logger.isLoggable(getClass(), TechnicalLogSeverity.TRACE)) {
                                logger.log(getClass(), TechnicalLogSeverity.TRACE, "Found flow node which execution should not be restored: "
                                        + sFlowNodeInstance.getName() + ":" + sFlowNodeInstance.getId());
                            }
                        }
                    }
                }
            } while (sFlowNodeInstances.size() == queryOptions.getNumberOfResults());
        } catch (final WorkRegisterException e) {
            handleException(e, "Unable to restart flowNodes: can't register work");
        } catch (final SBonitaException e) {
            handleException(e, "Unable to restart flowNodes: can't read flow nodes");
        }

    }

    /**
     * Determines if the found flownode should be relaunched at restart or not. For now, only Gateways must not always be restarted under certain conditions.
     *
     * @param logger
     *        TechnicalLoggerService to log errors if necessary
     * @param sFlowNodeInstance
     *        the flownode to check
     * @return true if the flownode should be relaunched because it has not finished its work in progress, false otherwise.
     * @throws SBonitaException
     *         in case of error.
     */
    protected boolean shouldExecuteFlownode(final TenantServiceAccessor tenantServiceAccessor, final TechnicalLoggerService logger,
            final SFlowNodeInstance sFlowNodeInstance) throws SBonitaException {
        try {
            final boolean isGateway = SFlowNodeType.GATEWAY.equals(sFlowNodeInstance.getType());
            if (isGateway) {
                final SGatewayInstance gatewayInstance = (SGatewayInstance) sFlowNodeInstance;
                final GatewayInstanceService gatewayInstanceService = tenantServiceAccessor.getGatewayInstanceService();
                final long processDefinitionId = gatewayInstance.getProcessDefinitionId();
                final SProcessDefinition processDefinition = tenantServiceAccessor.getProcessDefinitionService().getProcessDefinition(processDefinitionId);
                final boolean checkMergingCondition = gatewayInstanceService.checkMergingCondition(processDefinition, gatewayInstance);
                if (!checkMergingCondition) {
                    logger.log(this.getClass(), TechnicalLogSeverity.DEBUG, "Gateway not restarted: " + gatewayInstance.toString());
                }
                return checkMergingCondition;
            }
            return true;
        } catch (final SBonitaException e) {
            if (logger.isLoggable(this.getClass(), TechnicalLogSeverity.ERROR)) {
                logger.log(this.getClass(), TechnicalLogSeverity.ERROR, e);
            }
            throw e;
        }
    }

    private void handleException(final Exception e, final String message) throws RestartException {
        throw new RestartException(message, e);
    }

}
