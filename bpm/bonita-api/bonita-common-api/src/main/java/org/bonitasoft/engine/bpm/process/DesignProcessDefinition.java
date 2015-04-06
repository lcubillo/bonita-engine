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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.bonitasoft.engine.bpm.actor.ActorDefinition;
import org.bonitasoft.engine.bpm.contract.ContractDefinition;
import org.bonitasoft.engine.bpm.flownode.FlowElementContainerDefinition;
import org.bonitasoft.engine.bpm.parameter.ParameterDefinition;
import org.bonitasoft.engine.expression.Expression;

/**
 * Represents the Design Definition of a process. It gives access to process attributes.
 * <ul>
 * <li>display name</li>
 * <li>description</li>
 * <li>parameters</li>
 * <li>actors</li>
 * <li>search indexes</li>
 * </ul>
 *
 * @author Matthieu Chaffotte
 * @author Celine Souchet
 * @author Laurent Leseigneur
 * @version 6.3.5
 * @since 6.0.0
 */
public class DesignProcessDefinition extends ProcessDefinition {

    private static final long serialVersionUID = -4719128363958199300L;
    private final Set<ParameterDefinition> parameters;
    private final List<ActorDefinition> actors;
    private String displayName;
    private String displayDescription;
    private ActorDefinition actorInitiator;
    private FlowElementContainerDefinition flowElementContainer;
    private String stringIndexLabel1;
    private String stringIndexLabel2;
    private String stringIndexLabel3;
    private String stringIndexLabel4;
    private String stringIndexLabel5;
    private Expression stringIndexValue1;
    private Expression stringIndexValue2;
    private Expression stringIndexValue3;
    private Expression stringIndexValue4;
    private Expression stringIndexValue5;
    private ContractDefinition contract;

    public DesignProcessDefinition(final String name, final String version) {
        super(name, version);
        parameters = new HashSet<ParameterDefinition>();
        actors = new ArrayList<ActorDefinition>();
    }

    /**
     * Retrieves the displayed name of the process definition, as set at design-time.
     *
     * @return The displayed name of the process definition, as set at design-time.
     */
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(final String name) {
        displayName = name;
    }

    /**
     * Retrieves the displayed description of the process definition, as set at design-time.
     *
     * @return The displayed description of the process definition, as set at design-time.
     */
    public String getDisplayDescription() {
        return displayDescription;
    }

    public void setDisplayDescription(final String description) {
        displayDescription = description;
    }

    /**
     * Returns a {@link org.bonitasoft.engine.bpm.flownode.FlowElementContainerDefinition} containing all flow elements of this process.
     *
     * @return a {@code FlowElementContainerDefinition} containing all flow elements of this process.
     * @see org.bonitasoft.engine.bpm.flownode.FlowElementContainerDefinition
     * @since 6.4.1
     */
    public FlowElementContainerDefinition getFlowElementContainer() {
        return flowElementContainer;
    }

    /**
     * Retrieves a Set of ParameterDefinition objects from a ProcessDefinition
     *
     * @return A set of {@link ParameterDefinition} objects
     */
    public Set<ActorDefinition> getActors() {
        return new HashSet<>(actors);
    }

    /**
     * Gets the list of all actors defined on this process.
     *
     * @return The list of {@link ActorDefinition} objects defined in this process.
     * <br>If no actors have been defined, return an empty List.
     * @since 6.1
     */
    public List<ActorDefinition> getActorsList() {
        return actors;
    }

    /**
     * Retrieves the ActorDefinition of process's actor defined as initiator.
     *
     * @return The {@link ActorDefinition} of process's actor defined as initiator.
     * @since 6.1
     */
    public ActorDefinition getActorInitiator() {
        return actorInitiator;
    }

    public void setActorInitiator(final ActorDefinition actorInitiator) {
        this.actorInitiator = actorInitiator;
    }

    /**
     * Retrieves the label for the ProcessDefinition given search index.
     * <p>
     * You can define up to five search indexes for a process. See more at <a href="http://documentation.bonitasoft.com/define-search-index">Define a search
     * index</a> Bonitasoft documentation page
     * </p>
     *
     * @param index The position of search index to retrieve. Valid values are between 1 and 5 (inclusive)
     * @return The label the Expression of the search index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public String getStringIndexLabel(final int index) {
        switch (index) {
            case 1:
                return stringIndexLabel1;
            case 2:
                return stringIndexLabel2;
            case 3:
                return stringIndexLabel3;
            case 4:
                return stringIndexLabel4;
            case 5:
                return stringIndexLabel5;
            default:
                throw new IndexOutOfBoundsException("string index label must be between 1 and 5 (included)");
        }
    }

    /**
     * Retrieves the Expression for the ProcessDefinition given search index.
     * <p>
     * You can define up to five search indexes for a process. See more at <a href="http://documentation.bonitasoft.com/define-search-index">Define a search
     * index</a> Bonitasoft documentation page
     * </p>
     *
     * @param index The position of search index to retrieve. Valid values are between 1 and 5 (inclusive)
     * @return The {@link Expression} of the search index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public Expression getStringIndexValue(final int index) {
        switch (index) {
            case 1:
                return stringIndexValue1;
            case 2:
                return stringIndexValue2;
            case 3:
                return stringIndexValue3;
            case 4:
                return stringIndexValue4;
            case 5:
                return stringIndexValue5;
            default:
                throw new IndexOutOfBoundsException("string index value must be between 1 and 5 (included)");
        }
    }

    /**
     * Contract that must be respected when starting an instance of this process
     *
     * @return the process instantiation contract
     */
    public ContractDefinition getContract() {
        return contract;
    }

    public void setContract(ContractDefinition contract) {
        this.contract = contract;
    }

    public void addParameter(final ParameterDefinition parameter) {
        parameters.add(parameter);
    }

    public Set<ParameterDefinition> getParameters() {
        return parameters;
    }

    public void addActor(final ActorDefinition actor) {
        actors.add(actor);
    }

    public FlowElementContainerDefinition getProcessContainer() {
        return flowElementContainer;
    }

    public void setProcessContainer(final FlowElementContainerDefinition processContainer) {
        flowElementContainer = processContainer;
    }

    public ActorDefinition getActor(final String actorName) {
        final Iterator<ActorDefinition> iterator = actors.iterator();
        ActorDefinition actorDefinition = null;
        boolean found = false;
        while (!found && iterator.hasNext()) {
            final ActorDefinition next = iterator.next();
            if (next.getName().equals(actorName)) {
                found = true;
                actorDefinition = next;
            }
        }
        return actorDefinition;
    }

    public void setStringIndex(final int index, final String label, final Expression initialValue) {
        switch (index) {
            case 1:
                stringIndexLabel1 = label;
                stringIndexValue1 = initialValue;
                break;
            case 2:
                stringIndexLabel2 = label;
                stringIndexValue2 = initialValue;
                break;
            case 3:
                stringIndexLabel3 = label;
                stringIndexValue3 = initialValue;
                break;
            case 4:
                stringIndexLabel4 = label;
                stringIndexValue4 = initialValue;
                break;
            case 5:
                stringIndexLabel5 = label;
                stringIndexValue5 = initialValue;
                break;
            default:
                throw new IndexOutOfBoundsException("string index label must be between 1 and 5 (included)");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DesignProcessDefinition)) return false;
        if (!super.equals(o)) return false;
        DesignProcessDefinition that = (DesignProcessDefinition) o;
        return Objects.equals(parameters, that.parameters) &&
                Objects.equals(actors, that.actors) &&
                Objects.equals(displayName, that.displayName) &&
                Objects.equals(displayDescription, that.displayDescription) &&
                Objects.equals(actorInitiator, that.actorInitiator) &&
                Objects.equals(flowElementContainer, that.flowElementContainer) &&
                Objects.equals(stringIndexLabel1, that.stringIndexLabel1) &&
                Objects.equals(stringIndexLabel2, that.stringIndexLabel2) &&
                Objects.equals(stringIndexLabel3, that.stringIndexLabel3) &&
                Objects.equals(stringIndexLabel4, that.stringIndexLabel4) &&
                Objects.equals(stringIndexLabel5, that.stringIndexLabel5) &&
                Objects.equals(stringIndexValue1, that.stringIndexValue1) &&
                Objects.equals(stringIndexValue2, that.stringIndexValue2) &&
                Objects.equals(stringIndexValue3, that.stringIndexValue3) &&
                Objects.equals(stringIndexValue4, that.stringIndexValue4) &&
                Objects.equals(stringIndexValue5, that.stringIndexValue5) &&
                Objects.equals(contract, that.contract);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), parameters, actors, displayName, displayDescription, actorInitiator, flowElementContainer, stringIndexLabel1, stringIndexLabel2, stringIndexLabel3, stringIndexLabel4, stringIndexLabel5, stringIndexValue1, stringIndexValue2, stringIndexValue3, stringIndexValue4, stringIndexValue5, contract);
    }
}
