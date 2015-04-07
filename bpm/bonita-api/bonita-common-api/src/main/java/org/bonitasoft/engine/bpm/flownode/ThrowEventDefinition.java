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
import java.util.Objects;


/**
 * @author Elias Ricken de Medeiros
 * @author Matthieu Chaffotte
 */
public abstract class ThrowEventDefinition extends EventDefinition {


    private static final long serialVersionUID = -3142554305988571206L;

    private final List<ThrowMessageEventTriggerDefinition> messageEventTriggerDefinitions;

    private final List<ThrowSignalEventTriggerDefinition> signalEventTriggerDefinitions;

    public ThrowEventDefinition(final String name) {
        super(name);
        messageEventTriggerDefinitions = new ArrayList<>(1);
        signalEventTriggerDefinitions = new ArrayList<>(1);
    }

    public ThrowEventDefinition(final long id, final String name) {
        super(id, name);
        messageEventTriggerDefinitions = new ArrayList<>(1);
        signalEventTriggerDefinitions = new ArrayList<>(1);
    }

    public List<ThrowMessageEventTriggerDefinition> getMessageEventTriggerDefinitions() {
        return Collections.unmodifiableList(messageEventTriggerDefinitions);
    }

    public void addMessageEventTriggerDefinition(final ThrowMessageEventTriggerDefinition messageEventTriggerDefinition) {
        messageEventTriggerDefinitions.add(messageEventTriggerDefinition);
        addEventTrigger(messageEventTriggerDefinition);
    }

    public List<ThrowSignalEventTriggerDefinition> getSignalEventTriggerDefinitions() {
        return Collections.unmodifiableList(signalEventTriggerDefinitions);
    }

    public void addSignalEventTriggerDefinition(final ThrowSignalEventTriggerDefinition signalEventTrigger) {
        signalEventTriggerDefinitions.add(signalEventTrigger);
        addEventTrigger(signalEventTrigger);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ThrowEventDefinition that = (ThrowEventDefinition) o;
        return Objects.equals(messageEventTriggerDefinitions, that.messageEventTriggerDefinitions) &&
                Objects.equals(signalEventTriggerDefinitions, that.signalEventTriggerDefinitions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), messageEventTriggerDefinitions, signalEventTriggerDefinitions);
    }
}
