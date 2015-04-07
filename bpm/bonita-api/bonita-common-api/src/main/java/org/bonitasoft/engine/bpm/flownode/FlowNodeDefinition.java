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
import java.util.UUID;

import org.bonitasoft.engine.bpm.DescriptionElement;
import org.bonitasoft.engine.bpm.connector.ConnectorDefinition;
import org.bonitasoft.engine.expression.Expression;

/**
 * @author Zhao Na
 * @author Matthieu Chaffotte
 * @author Celine Souchet
 * @author Baptiste Mesta
 */
public abstract class FlowNodeDefinition extends DescriptionElement {


    private static final long serialVersionUID = 2L;

    private final List<TransitionDefinition> incomings;

    private final List<TransitionDefinition> outgoings;

    private final List<ConnectorDefinition> connectors;

    private Expression displayDescription;

    private Expression displayName;

    private Expression displayDescriptionAfterCompletion;

    private TransitionDefinition defaultTransition;

    public FlowNodeDefinition(final long id, final String name) {
        super(name);
        incomings = new ArrayList<>();
        outgoings = new ArrayList<>();
        connectors = new ArrayList<>();
        setId(id);
    }

    public FlowNodeDefinition(final String name) {
        this(UUID.randomUUID().getLeastSignificantBits(), name);
    }


    public TransitionDefinition getDefaultTransition() {
        return defaultTransition;
    }

    public void setDefaultTransition(final TransitionDefinition defaultTransition) {
        this.defaultTransition = defaultTransition;
    }

    /**
     * Gets the outgoing transitions of the activity.
     *
     * @return the outgoing transitions of the activity
     */

    public List<TransitionDefinition> getOutgoingTransitions() {
        return Collections.unmodifiableList(outgoings);
    }

    /**
     * Gets the incoming transitions of the activity.
     *
     * @return the incoming transitions of the activity
     */
    public List<TransitionDefinition> getIncomingTransitions() {
        return Collections.unmodifiableList(incomings);
    }


    public List<ConnectorDefinition> getConnectors() {
        return Collections.unmodifiableList(connectors);
    }

    public void addIncomingTransition(final TransitionDefinition transition) {
        if (!incomings.contains(transition)) {
            incomings.add(transition);
        }
    }

    public void addIncomingTransition(int index, TransitionDefinition transition) {
        if (!incomings.contains(transition)) {
            incomings.add(index, transition);
        }
    }

    public void removeIncomingTransition(final TransitionDefinition transition) {
        incomings.remove(transition);
    }

    public void addOutgoingTransition(final TransitionDefinition transition) {
        if (!outgoings.contains(transition)) {
            outgoings.add(transition);
        }
    }

    public void addOutgoingTransition(final int index, final TransitionDefinition transition) {
        if (!outgoings.contains(transition)) {
            outgoings.add(index, transition);
        }
    }

    public void removeOutgoingTransition(final TransitionDefinition transition) {
        outgoings.remove(transition);
    }


    public void addConnector(final ConnectorDefinition connectorDefinition) {
        connectors.add(connectorDefinition);
    }

    public Expression getDisplayDescription() {
        return displayDescription;
    }

    public void setDisplayDescription(final Expression displayDescription) {
        this.displayDescription = displayDescription;
    }

    public Expression getDisplayName() {
        return displayName;
    }

    public void setDisplayName(final Expression displayName) {
        this.displayName = displayName;
    }

    public Expression getDisplayDescriptionAfterCompletion() {
        return displayDescriptionAfterCompletion;
    }

    public void setDisplayDescriptionAfterCompletion(final Expression displayDescriptionAfterCompletion) {
        this.displayDescriptionAfterCompletion = displayDescriptionAfterCompletion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FlowNodeDefinition that = (FlowNodeDefinition) o;
        return Objects.equals(incomings, that.incomings) &&
                Objects.equals(outgoings, that.outgoings) &&
                Objects.equals(connectors, that.connectors) &&
                Objects.equals(displayDescription, that.displayDescription) &&
                Objects.equals(displayName, that.displayName) &&
                Objects.equals(displayDescriptionAfterCompletion, that.displayDescriptionAfterCompletion) &&
                Objects.equals(defaultTransition, that.defaultTransition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), incomings, outgoings, connectors, displayDescription, displayName, displayDescriptionAfterCompletion, defaultTransition);
    }
}
