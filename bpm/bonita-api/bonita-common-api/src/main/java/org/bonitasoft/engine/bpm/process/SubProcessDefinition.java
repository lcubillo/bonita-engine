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
package org.bonitasoft.engine.bpm.process;

import org.bonitasoft.engine.bpm.flownode.ActivityDefinition;
import org.bonitasoft.engine.bpm.flownode.FlowElementContainerDefinition;

/**
 * Definition of a sub-process, which is a type of Activity.
 *
 * @author Matthieu Chaffotte
 */
public class SubProcessDefinition extends ActivityDefinition {

    private static final long serialVersionUID = -5578839351835375715L;

    private final boolean triggeredByEvent;

    private FlowElementContainerDefinition subProcessContainer;

    public SubProcessDefinition(final String name, final boolean triggeredByEvent) {
        super(name);
        this.triggeredByEvent = triggeredByEvent;
    }

    public SubProcessDefinition(final long id, final String name, final boolean triggeredByEvent) {
        super(id, name);
        this.triggeredByEvent = triggeredByEvent;
    }

    /**
     * Gets the definition of the "container" of this sub-process.
     *
     * @return the definition of the "container" of this sub-process.
     */

    public FlowElementContainerDefinition getSubProcessContainer() {
        return subProcessContainer;
    }

    public void setSubProcessContainer(final FlowElementContainerDefinition subProcessContainer) {
        this.subProcessContainer = subProcessContainer;
    }

    /**
     * Has this definition of a sub-process been triggered by a BPM event?
     *
     * @return true if this definition of a sub-process has been triggered by an event, false otherwise.
     */
    public boolean isTriggeredByEvent() {
        return triggeredByEvent;
    }


}
