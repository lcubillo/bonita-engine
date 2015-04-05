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

import org.bonitasoft.engine.bpm.ObjectSeeker;
import org.bonitasoft.engine.bpm.businessdata.BusinessDataDefinition;
import org.bonitasoft.engine.bpm.data.DataDefinition;
import org.bonitasoft.engine.operation.Operation;

/**
 * An Activity is work that is performed within a Business Process. An Activity can be atomic or non-atomic
 * (compound). The types of Activities that are a part of a Process are: {@link TaskDefinition}, {@link org.bonitasoft.engine.bpm.process.SubProcessDefinition},
 * and {@link CallActivityDefinition}, which allows the inclusion of re-usable Tasks and Processes.
 *
 * @author Baptiste Mesta
 * @author Feng Hui
 * @author Matthieu Chaffotte
 * @author Celine Souchet
 */
public abstract class ActivityDefinition extends FlowNodeDefinition {


    private static final long serialVersionUID = 5575175860474559979L;

    private final List<DataDefinition> dataDefinitions;

    private final List<BusinessDataDefinition> businessDataDefinitions;

    private final List<Operation> operations;
    private final List<BoundaryEventDefinition> boundaryEventDefinitions;
    private LoopCharacteristics loopCharacteristics;

    public ActivityDefinition(final long id, final String name) {
        super(id, name);
        dataDefinitions = new ArrayList<>();
        operations = new ArrayList<>();
        boundaryEventDefinitions = new ArrayList<>(1);
        businessDataDefinitions = new ArrayList<>(3);
    }

    public ActivityDefinition(final String name) {
        super(name);
        dataDefinitions = new ArrayList<>();
        operations = new ArrayList<>();
        boundaryEventDefinitions = new ArrayList<>(1);
        businessDataDefinitions = new ArrayList<>(3);
    }

    /**
     * @return The list of the definition of data on this activity
     */

    public List<DataDefinition> getDataDefinitions() {
        return dataDefinitions;
    }

    public void addDataDefinition(final DataDefinition dataDefinition) {
        dataDefinitions.add(dataDefinition);
    }

    /**
     * @return The list of operations on this activity
     */

    public List<Operation> getOperations() {
        return operations;
    }

    public void addOperation(final Operation operation) {
        operations.add(operation);
    }

    /**
     * @return The list of operations on this activity
     */
    public List<BoundaryEventDefinition> getBoundaryEventDefinitions() {
        return Collections.unmodifiableList(boundaryEventDefinitions);
    }

    public void addBoundaryEventDefinition(final BoundaryEventDefinition boundaryEventDefinition) {
        boundaryEventDefinitions.add(boundaryEventDefinition);
    }

    /**
     * @return The list of loops on this activity
     */

    public LoopCharacteristics getLoopCharacteristics() {
        return loopCharacteristics;
    }

    public void setLoopCharacteristics(final LoopCharacteristics loopCharacteristics) {
        this.loopCharacteristics = loopCharacteristics;
    }

    /**
     * @return The list of the definitions of business data of the activity.
     */
    public List<BusinessDataDefinition> getBusinessDataDefinitions() {
        return businessDataDefinitions;
    }

    public void addBusinessDataDefinition(final BusinessDataDefinition businessDataDefinition) {
        businessDataDefinitions.add(businessDataDefinition);
    }


    public BusinessDataDefinition getBusinessDataDefinition(final String name) {
        return ObjectSeeker.getNamedElement(businessDataDefinitions, name);
    }

    public DataDefinition getDataDefinition(final String name) {
        return ObjectSeeker.getNamedElement(dataDefinitions, name);
    }

}
