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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.engine.bpm.BaseElement;
import org.bonitasoft.engine.bpm.NamedElement;
import org.bonitasoft.engine.bpm.ObjectSeeker;
import org.bonitasoft.engine.bpm.businessdata.BusinessDataDefinition;
import org.bonitasoft.engine.bpm.connector.ConnectorDefinition;
import org.bonitasoft.engine.bpm.data.DataDefinition;
import org.bonitasoft.engine.bpm.document.DocumentDefinition;
import org.bonitasoft.engine.bpm.document.DocumentListDefinition;

/**
 * Allows to access all flow elements (activities, gateways, events and transitions) of a process or sub-process.
 *
 * @author Matthieu Chaffotte
 * @author Celine Souchet
 */
public class FlowElementContainerDefinition extends BaseElement {


    private static final long serialVersionUID = 1L;

    private final List<ActivityDefinition> activities;

    private final Set<TransitionDefinition> transitions;

    private final List<GatewayDefinition> gateways;

    private final List<StartEventDefinition> startEvents;

    private final List<IntermediateCatchEventDefinition> intermediateCatchEvents;

    private final List<IntermediateThrowEventDefinition> intermediateThrowEvents;

    private final List<EndEventDefinition> endEvents;

    private final List<DataDefinition> dataDefinitions;

    private final List<BusinessDataDefinition> businessDataDefinitions;

    private final List<DocumentDefinition> documentDefinitions;

    private final List<DocumentListDefinition> documentListDefinitions;

    private final List<ConnectorDefinition> connectors;

    private final Map<String, FlowNodeDefinition> flowNodes;

    public FlowElementContainerDefinition() {
        activities = new ArrayList<>();
        transitions = new HashSet<>();
        gateways = new ArrayList<>();
        startEvents = new ArrayList<>(1);
        intermediateCatchEvents = new ArrayList<>(4);
        endEvents = new ArrayList<>(4);
        intermediateThrowEvents = new ArrayList<>(4);
        dataDefinitions = new ArrayList<>();
        businessDataDefinitions = new ArrayList<>();
        documentDefinitions = new ArrayList<>();
        documentListDefinitions = new ArrayList<>();
        connectors = new ArrayList<>();
        flowNodes = new HashMap<>();
    }

    public FlowNodeDefinition getFlowNode(final long sourceId) {
        final Set<FlowNodeDefinition> flowNodes = getFlowNodes();
        return getElementById(flowNodes, sourceId);
    }

    public FlowNodeDefinition getFlowNode(final String sourceName) {
        final Set<FlowNodeDefinition> flowNodes = getFlowNodes();
        return getElementByName(flowNodes, sourceName);
    }

    private Set<FlowNodeDefinition> getFlowNodes() {
        final Set<FlowNodeDefinition> flowNodes = new HashSet<FlowNodeDefinition>();
        flowNodes.addAll(gateways);
        flowNodes.addAll(activities);
        flowNodes.addAll(startEvents);
        flowNodes.addAll(intermediateCatchEvents);
        flowNodes.addAll(intermediateThrowEvents);
        flowNodes.addAll(endEvents);
        flowNodes.addAll(getBoundaryEvents());
        return Collections.unmodifiableSet(flowNodes);
    }

    private List<BoundaryEventDefinition> getBoundaryEvents() {
        final List<BoundaryEventDefinition> boundaryEvents = new ArrayList<BoundaryEventDefinition>(3);
        for (final ActivityDefinition activity : activities) {
            boundaryEvents.addAll(activity.getBoundaryEventDefinitions());
        }
        return boundaryEvents;
    }

    public List<ActivityDefinition> getActivities() {
        return Collections.unmodifiableList(activities);
    }

    public ActivityDefinition getActivity(final String name) {
        return getElementByName(activities, name);
    }

    public Set<TransitionDefinition> getTransitions() {
        return Collections.unmodifiableSet(transitions);
    }

    @Deprecated
    public Set<GatewayDefinition> getGateways() {
        return Collections.unmodifiableSet(new HashSet<GatewayDefinition>(gateways));
    }

    public List<GatewayDefinition> getGatewaysList() {
        return Collections.unmodifiableList(gateways);
    }

    public GatewayDefinition getGateway(final String name) {
        return getElementByName(gateways, name);
    }

    private <T extends BaseElement> T getElementById(final Collection<T> elements, final long id) {
        T element = null;
        boolean found = false;
        final Iterator<T> iterator = elements.iterator();
        while (!found && iterator.hasNext()) {
            final T next = iterator.next();
            if (next.getId() == id) {
                found = true;
                element = next;
            }
        }
        return element;
    }

    private <T extends NamedElement> T getElementByName(final Collection<T> elements, final String name) {
        T element = null;
        boolean found = false;
        final Iterator<T> iterator = elements.iterator();
        while (!found && iterator.hasNext()) {
            final T next = iterator.next();
            if (name.equals(next.getName())) {
                found = true;
                element = next;
            }
        }
        return element;
    }

    public List<StartEventDefinition> getStartEvents() {
        return Collections.unmodifiableList(startEvents);
    }

    public List<IntermediateCatchEventDefinition> getIntermediateCatchEvents() {
        return Collections.unmodifiableList(intermediateCatchEvents);
    }

    public List<IntermediateThrowEventDefinition> getIntermediateThrowEvents() {
        return Collections.unmodifiableList(intermediateThrowEvents);
    }

    public List<EndEventDefinition> getEndEvents() {
        return Collections.unmodifiableList(endEvents);
    }

    public List<BusinessDataDefinition> getBusinessDataDefinitions() {
        return Collections.unmodifiableList(businessDataDefinitions);
    }

    public List<DataDefinition> getDataDefinitions() {
        return Collections.unmodifiableList(dataDefinitions);
    }

    public List<DocumentDefinition> getDocumentDefinitions() {
        return Collections.unmodifiableList(documentDefinitions);
    }
    public List<DocumentListDefinition> getDocumentListDefinitions() {
        return Collections.unmodifiableList(documentListDefinitions);
    }

    public List<ConnectorDefinition> getConnectors() {
        return Collections.unmodifiableList(connectors);
    }

    public void addActivity(final ActivityDefinition activity) {
        activities.add(activity);
        flowNodes.put(activity.getName(), activity);
    }

    public void addTransition(final TransitionDefinition transition) {
        transitions.add(transition);
    }

    public void addGateway(final GatewayDefinition gateway) {
        gateways.add(gateway);
        flowNodes.put(gateway.getName(), gateway);
    }

    public void addStartEvent(final StartEventDefinition startEvent) {
        startEvents.add(startEvent);
        flowNodes.put(startEvent.getName(), startEvent);
    }

    public void addIntermediateCatchEvent(final IntermediateCatchEventDefinition event) {
        intermediateCatchEvents.add(event);
        flowNodes.put(event.getName(), event);
    }

    public void addIntermediateThrowEvent(final IntermediateThrowEventDefinition intermediateThrowEvent) {
        intermediateThrowEvents.add(intermediateThrowEvent);
        flowNodes.put(intermediateThrowEvent.getName(), intermediateThrowEvent);
    }

    public void addEndEvent(final EndEventDefinition endEvent) {
        endEvents.add(endEvent);
        flowNodes.put(endEvent.getName(), endEvent);
    }

    public void addBusinessDataDefinition(final BusinessDataDefinition businessDataDefinition) {
        businessDataDefinitions.add(businessDataDefinition);
    }

    public void addDataDefinition(final DataDefinition dataDefinition) {
        dataDefinitions.add(dataDefinition);
    }

    public void addDocumentDefinition(final DocumentDefinition documentDefinition) {
        documentDefinitions.add(documentDefinition);
    }
    public void addDocumentListDefinition(final DocumentListDefinition documentListDefinition) {
        documentListDefinitions.add(documentListDefinition);
    }

    public void addConnector(final ConnectorDefinition connectorDefinition) {
        connectors.add(connectorDefinition);
    }


    public BusinessDataDefinition getBusinessDataDefinition(final String name) {
        return ObjectSeeker.getNamedElement(businessDataDefinitions, name);
    }

    public DataDefinition getDataDefinition(final String name) {
        return ObjectSeeker.getNamedElement(dataDefinitions, name);
    }
}
