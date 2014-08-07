/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.engine.api.impl;

import org.bonitasoft.engine.core.login.LoginService;
import org.bonitasoft.engine.log.technical.TechnicalLogSeverity;
import org.bonitasoft.engine.log.technical.TechnicalLoggerService;
import org.bonitasoft.engine.scheduler.SchedulerService;
import org.bonitasoft.engine.scheduler.exception.SSchedulerException;
import org.bonitasoft.engine.service.PlatformServiceAccessor;
import org.bonitasoft.engine.service.TenantServiceAccessor;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.session.InvalidSessionException;

/**
 * @author  Emmanuel Duchastenier
 */
public class APISessionChecker {

    public void checkTenantSession(final PlatformServiceAccessor platformAccessor, final APISession session, final TechnicalLoggerService technicalLogger)
            throws SSchedulerException {
        final SchedulerService schedulerService = platformAccessor.getSchedulerService();
        final TechnicalLoggerService logger = platformAccessor.getTechnicalLoggerService();
        if (!schedulerService.isStarted() && logger.isLoggable(this.getClass(), TechnicalLogSeverity.DEBUG)) {
            logger.log(this.getClass(), TechnicalLogSeverity.DEBUG, "The scheduler is not started!");
        }
        final TenantServiceAccessor tenantAccessor = platformAccessor.getTenantServiceAccessor(session.getTenantId());
        final LoginService tenantLoginService = tenantAccessor.getLoginService();
        if (!tenantLoginService.isValid(session.getId())) {
            throw new InvalidSessionException("Invalid session");
        }
    }
}