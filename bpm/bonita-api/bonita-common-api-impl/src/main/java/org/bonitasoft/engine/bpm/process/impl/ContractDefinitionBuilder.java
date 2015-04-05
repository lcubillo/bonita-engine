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

import java.util.Arrays;
import java.util.List;

import org.bonitasoft.engine.bpm.contract.ComplexInputDefinition;
import org.bonitasoft.engine.bpm.contract.ConstraintDefinition;
import org.bonitasoft.engine.bpm.contract.ConstraintType;
import org.bonitasoft.engine.bpm.contract.ContractDefinition;
import org.bonitasoft.engine.bpm.contract.SimpleInputDefinition;
import org.bonitasoft.engine.bpm.contract.Type;
import org.bonitasoft.engine.bpm.flownode.FlowElementContainerDefinition;
import org.bonitasoft.engine.bpm.flownode.UserTaskDefinition;
import org.bonitasoft.engine.bpm.process.DesignProcessDefinition;

/**
 * @author Matthieu Chaffotte
 * @author Laurent Leseigneur
 */
public class ContractDefinitionBuilder extends FlowElementContainerBuilder {

    private final ContractDefinition contract;

    public ContractDefinitionBuilder(final ProcessDefinitionBuilder processDefinitionBuilder, final FlowElementContainerDefinition container,
                                     final UserTaskDefinition activity) {
        super(container, processDefinitionBuilder);
        contract = new ContractDefinition();
        activity.setContract(contract);
    }
    public ContractDefinitionBuilder(final ProcessDefinitionBuilder processDefinitionBuilder, final DesignProcessDefinition container) {
        super(container.getProcessContainer(), processDefinitionBuilder);
        contract = new ContractDefinition();
        container.setContract(contract);
    }

    public ContractDefinitionBuilder addSimpleInput(final String name, final Type type, final String description) {
        return addSimpleInput(name, type, description, false);
    }

    public ContractDefinitionBuilder addSimpleInput(final String name, final Type type, final String description, final boolean multiple) {
        final SimpleInputDefinition input = new SimpleInputDefinition(name, type, description, multiple);
        contract.addSimpleInput(input);
        return this;
    }

    public ContractDefinitionBuilder addComplexInput(final String name, final String description, final List<SimpleInputDefinition> simpleInputs,
            final List<ComplexInputDefinition> complexInputs) {
        return addComplexInput(name, description, false, simpleInputs, complexInputs);
    }

    public ContractDefinitionBuilder addComplexInput(final String name, final String description, final boolean multiple,
            final List<SimpleInputDefinition> simpleInputs, final List<ComplexInputDefinition> complexInputs) {
        final ComplexInputDefinition input = new ComplexInputDefinition(name, description, multiple, simpleInputs, complexInputs);
        contract.addComplexInput(input);
        return this;
    }

    public ContractDefinitionBuilder addFileInput(final String name, final String description) {
        final SimpleInputDefinition nameInput= new SimpleInputDefinition("name", Type.TEXT, "The file name");
        final SimpleInputDefinition contentInput= new SimpleInputDefinition("content", Type.BYTE_ARRAY, "The file content");
        final ComplexInputDefinition fileInput = new ComplexInputDefinition(name, description,
                Arrays.<SimpleInputDefinition> asList(nameInput, contentInput), null);
        contract.addComplexInput(fileInput);
        return this;
    }

    public ContractDefinitionBuilder addConstraint(final String name, final String expression, final String explanation, final String... inputNames) {
        final ConstraintDefinition constraintDefinition = new ConstraintDefinition(name, expression, explanation, ConstraintType.CUSTOM);
        for (final String inputName : inputNames) {
            constraintDefinition.addInputName(inputName);
        }
        contract.addConstraint(constraintDefinition);
        return this;
    }

    public ContractDefinitionBuilder addMandatoryConstraint(final String inputName) {
        final StringBuilder expression = new StringBuilder().append(inputName).append("!=null");
        expression.append(" && !");
        expression.append(inputName);
        expression.append(".toString().isEmpty()");

        final ConstraintDefinition constraint = new ConstraintDefinition(inputName, expression.toString(), new StringBuilder().append("input ")
                .append(inputName).append(" is mandatory").toString(), ConstraintType.MANDATORY);
        constraint.addInputName(inputName);
        contract.addConstraint(constraint);
        return this;
    }
}
