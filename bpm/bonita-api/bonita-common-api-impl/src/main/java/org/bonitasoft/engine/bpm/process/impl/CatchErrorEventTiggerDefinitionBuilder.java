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
package org.bonitasoft.engine.bpm.process.impl;

import org.bonitasoft.engine.bpm.flownode.CatchErrorEventTriggerDefinition;
import org.bonitasoft.engine.bpm.flownode.CatchEventDefinition;
import org.bonitasoft.engine.bpm.flownode.FlowElementContainerDefinition;


/**
 * @author Elias Ricken de Medeiros
 * @author Matthieu Chaffotte
 */
public class CatchErrorEventTiggerDefinitionBuilder extends FlowElementContainerBuilder {

    protected CatchErrorEventTiggerDefinitionBuilder(final ProcessDefinitionBuilder processDefinitionBuilder,
            final FlowElementContainerDefinition container, final CatchEventDefinition event) {
        this(processDefinitionBuilder, container, event, null);
    }

    protected CatchErrorEventTiggerDefinitionBuilder(final ProcessDefinitionBuilder processDefinitionBuilder,
            final FlowElementContainerDefinition container, final CatchEventDefinition event, final String errorCode) {
        super(container, processDefinitionBuilder);
        if (errorCode != null && errorCode.trim().isEmpty()) {
            getProcessBuilder().addError("The error code cannot be empty.");
        }
        final CatchErrorEventTriggerDefinition triggerDefinition = new CatchErrorEventTriggerDefinition(errorCode);
        event.addErrorEventTrigger(triggerDefinition);
    }

}
