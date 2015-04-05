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

import org.bonitasoft.engine.bpm.flownode.FlowElementContainerDefinition;
import org.bonitasoft.engine.bpm.flownode.FlowNodeDefinition;
import org.bonitasoft.engine.bpm.flownode.TransitionDefinition;
import org.bonitasoft.engine.expression.Expression;

/**
 * @author Baptiste Mesta
 * @author Matthieu Chaffotte
 * @author Celine Souchet
 */
public class TransitionDefinitionBuilder extends FlowElementContainerBuilder {

    private TransitionDefinition transition;

    TransitionDefinitionBuilder(final ProcessDefinitionBuilder processDefinitionBuilder, final FlowElementContainerDefinition container,
            final String source, final String target, final Boolean isDefaultTransition) {
        super(container, processDefinitionBuilder);
        addTransition(source, target, null, isDefaultTransition);
    }

    TransitionDefinitionBuilder(final ProcessDefinitionBuilder processDefinitionBuilder, final FlowElementContainerDefinition container,
            final String source, final String target, final Expression expression, final Boolean isDefaultTransition) {
        super(container, processDefinitionBuilder);
        addTransition(source, target, expression, isDefaultTransition);
    }

    private void addTransition(final String source, final String target, final Expression condition, final Boolean isDefaultTransition) {
        // Retrieve source and target flowNode
        final FlowNodeDefinition from = getContainer().getFlowNode(source);
        final FlowNodeDefinition to = getContainer().getFlowNode(target);

        if (from == null) {
            getProcessBuilder().addError("from : Unable to find a flow element named: " + source);
        }
        if (to == null) {
            getProcessBuilder().addError("to : Unable to find a flow element named: " + target);
        }
        if (to == null || from == null) {
            return;
        }

        // Create transition
        transition = new TransitionDefinition(from.getId(), to.getId());
        transition.setCondition(condition);

        if (isDefaultTransition) {
            from.setDefaultTransition(transition);
        } else {
            from.addOutgoingTransition(transition);
        }
        to.addIncomingTransition(transition);
        getContainer().addTransition(transition);
    }

}
