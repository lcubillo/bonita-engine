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

import org.bonitasoft.engine.bpm.data.DataDefinition;
import org.bonitasoft.engine.expression.Expression;

/**
 * @author Matthieu Chaffotte
 * @author Yanyan Liu
 */
public class ThrowMessageEventTriggerDefinition extends MessageEventTriggerDefinition {
    private static final long serialVersionUID = 2L;
    private final List<DataDefinition> dataDefinitions;
    private Expression targetProcess;
    private Expression targetFlowNode;

    public ThrowMessageEventTriggerDefinition(final String messageName) {
        super(messageName);
        dataDefinitions = new ArrayList<>();
    }

    public ThrowMessageEventTriggerDefinition(final String name, final Expression targetProcess, final Expression targetFlowNode) {
        super(name);
        this.targetProcess = targetProcess;
        this.targetFlowNode = targetFlowNode;
        dataDefinitions = new ArrayList<>();
    }

    public ThrowMessageEventTriggerDefinition(final String name, final Expression targetProcess) {
        super(name);
        this.targetProcess = targetProcess;
        targetFlowNode = null;
        dataDefinitions = new ArrayList<>();
    }

    public ThrowMessageEventTriggerDefinition(final String name, final Expression targetProcess, final Expression targetFlowNode,
                                              final List<DataDefinition> dataDefinitions, final List<CorrelationDefinition> correlations) {
        super(name, correlations);
        this.targetProcess = targetProcess;
        this.targetFlowNode = targetFlowNode;
        this.dataDefinitions = dataDefinitions;
    }

    public ThrowMessageEventTriggerDefinition(final ThrowMessageEventTriggerDefinition trigger) {
        super(trigger);
        targetFlowNode = trigger.getTargetFlowNode();
        targetProcess = trigger.getTargetProcess();
        dataDefinitions = trigger.getDataDefinitions();
    }

    public Expression getTargetProcess() {
        return targetProcess;
    }

    public void setTargetProcess(final Expression targetProcess) {
        this.targetProcess = targetProcess;
    }

    public Expression getTargetFlowNode() {
        return targetFlowNode;
    }

    public void setTargetFlowNode(final Expression targetFlowNode) {
        this.targetFlowNode = targetFlowNode;
    }

    public List<DataDefinition> getDataDefinitions() {
        return Collections.unmodifiableList(dataDefinitions);
    }

    public void addDataDefinition(final DataDefinition datadefiniton) {
        dataDefinitions.add(datadefiniton);
    }

}
