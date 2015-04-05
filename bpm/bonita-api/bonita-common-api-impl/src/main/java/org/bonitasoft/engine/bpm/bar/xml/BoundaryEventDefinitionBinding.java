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
package org.bonitasoft.engine.bpm.bar.xml;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.engine.bpm.flownode.BoundaryEventDefinition;
import org.bonitasoft.engine.bpm.flownode.CatchErrorEventTriggerDefinition;
import org.bonitasoft.engine.bpm.flownode.CatchEventDefinition;
import org.bonitasoft.engine.bpm.flownode.FlowNodeDefinition;

/**
 * @author Elias Ricken de Medeiros
 * @author Celine Souchet
 */
public class BoundaryEventDefinitionBinding extends CatchEventDefinitionBinding {

    private final List<CatchErrorEventTriggerDefinition> errorEventTriggers;

    public BoundaryEventDefinitionBinding() {
        super();
        errorEventTriggers = new ArrayList<>(1);
    }

    @Override
    public Object getObject() {
        final BoundaryEventDefinition boundaryEventDefinitionImpl = new BoundaryEventDefinition(id, name);
        fillNode(boundaryEventDefinitionImpl);
        return boundaryEventDefinitionImpl;
    }

    @Override
    public void setChildObject(final String name, final Object value) {
        super.setChildObject(name, value);
        if (XMLProcessDefinition.CATCH_ERROR_EVENT_TRIGGER_NODE.equals(name)) {
            errorEventTriggers.add((CatchErrorEventTriggerDefinition) value);
        }
    }

    @Override
    protected void fillNode(final FlowNodeDefinition flowNode) {
        super.fillNode(flowNode);
        final CatchEventDefinition catchEventDefinition = (CatchEventDefinition) flowNode;
        for (final CatchErrorEventTriggerDefinition errorlEventTrigger : errorEventTriggers) {
            catchEventDefinition.addErrorEventTrigger(errorlEventTrigger);
        }

    }

    @Override
    public String getElementTag() {
        return XMLProcessDefinition.BOUNDARY_EVENT_NODE;
    }

}
