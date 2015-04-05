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
 */
package org.bonitasoft.engine.bpm.flownode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author Elias Ricken de Medeiros
 * @author Matthieu Chaffotte
 */
public class EndEventDefinition extends ThrowEventDefinition {

    private static final long serialVersionUID = 2L;

    private final List<ThrowErrorEventTriggerDefinition> errorEventTriggerDefinitions;

    private TerminateEventTriggerDefinition terminateEventTriggerDefinition;

    public EndEventDefinition(final String name) {
        super(name);
        errorEventTriggerDefinitions = new ArrayList<>(1);
    }

    public EndEventDefinition(final long id, final String name) {
        super(id, name);
        errorEventTriggerDefinitions = new ArrayList<>(1);
    }

    public TerminateEventTriggerDefinition getTerminateEventTriggerDefinition() {
        return terminateEventTriggerDefinition;
    }

    public void setTerminateEventTriggerDefinition(final TerminateEventTriggerDefinition terminateEventTriggerDefinition) {
        this.terminateEventTriggerDefinition = terminateEventTriggerDefinition;
    }

    public List<ThrowErrorEventTriggerDefinition> getErrorEventTriggerDefinitions() {
        return Collections.unmodifiableList(errorEventTriggerDefinitions);
    }

    public void addErrorEventTriggerDefinition(final ThrowErrorEventTriggerDefinition errorEventTrigger) {
        errorEventTriggerDefinitions.add(errorEventTrigger);
        addEventTrigger(errorEventTrigger);
    }


}
