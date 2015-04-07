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
 */
public class EventDefinition extends FlowNodeDefinition {

    private static final long serialVersionUID = 2L;

    private final List<EventTriggerDefinition> eventTriggers;

    public EventDefinition(final String name) {
        super(name);
        eventTriggers = new ArrayList<>();
    }

    public EventDefinition(final long id, final String name) {
        super(id, name);
        eventTriggers = new ArrayList<>();
    }

    public List<EventTriggerDefinition> getEventTriggers() {
        return Collections.unmodifiableList(eventTriggers);
    }

    protected void addEventTrigger(final EventTriggerDefinition eventTrigger) {
        eventTriggers.add(eventTrigger);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EventDefinition that = (EventDefinition) o;
        return Objects.equals(eventTriggers, that.eventTriggers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), eventTriggers);
    }
}
