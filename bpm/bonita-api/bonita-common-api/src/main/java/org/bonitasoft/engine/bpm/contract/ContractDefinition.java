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
package org.bonitasoft.engine.bpm.contract;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.engine.bpm.BonitaObject;
import org.bonitasoft.engine.bpm.flownode.UserTaskDefinition;
import org.bonitasoft.engine.bpm.flownode.UserTaskInstance;

/**
 * A <code>ContractDefinition</code> defines what the {@link UserTaskInstance} needs to be executed, or the Process to be started.
 * <p>
 * A <code>ContractDefinition</code> is part of a {@link UserTaskDefinition} or a of the {@link org.bonitasoft.engine.bpm.process.ProcessDefinition} </p>
 *
 * @author Matthieu Chaffotte
 * @since 7.0
 */
public class ContractDefinition implements BonitaObject {


    private static final long serialVersionUID = 786706819903231008L;

    private final List<ConstraintDefinition> constraints;

    private final List<ComplexInputDefinition> complexInputs;

    private final List<SimpleInputDefinition> simpleInputs;

    public ContractDefinition() {
        simpleInputs = new ArrayList<>();
        complexInputs = new ArrayList<>();
        constraints = new ArrayList<>();
    }

    public void addSimpleInput(final SimpleInputDefinition input) {
        simpleInputs.add(input);
    }

    public void addConstraint(final ConstraintDefinition constraint) {
        constraints.add(constraint);
    }

    public void addComplexInput(final ComplexInputDefinition complexInput) {
        complexInputs.add(complexInput);
    }


    /**
     * Lists the validation rules of the contract.
     *
     * @return the validation rules of the contract
     */
    public List<ConstraintDefinition> getConstraints() {
        return constraints;
    }


    /**
     * Lists the simpleInputs of the contract.
     *
     * @return the simple inputs of the contract
     */
    public List<SimpleInputDefinition> getSimpleInputs() {
        return simpleInputs;
    }


    /**
     * Lists the complex inputs of the contract.
     *
     * @return the complex inputs of the contract
     */
    public List<ComplexInputDefinition> getComplexInputs() {
        return complexInputs;
    }
}
