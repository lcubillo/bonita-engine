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

import org.bonitasoft.engine.bpm.NamedElement;
import org.bonitasoft.engine.bpm.flownode.UserTaskInstance;

/**
 * A <code>ConstraintDefinition</code> is a constraint when executing the {@link UserTaskInstance}.
 *
 * @author Matthieu Chaffotte
 * @author Laurent Leseigneur
 * @since 7.0
 */
public class ConstraintDefinition extends NamedElement {

    private static final long serialVersionUID = 2L;

    private final String expression;
    private final String explanation;
    private final List<String> inputNames;
    private final ConstraintType constraintType;

    public ConstraintDefinition(final String name, final String expression, final String explanation) {
        this(name, expression, explanation, ConstraintType.CUSTOM);
    }

    public ConstraintDefinition(final String name, final String expression, final String explanation, final ConstraintType constraintType) {
        super(name);
        this.constraintType = constraintType;
        this.explanation = explanation;
        this.expression = expression;
        inputNames = new ArrayList<>();
    }

    /**
     * Returns the boolean condition used to validate a part of the {@link ContractDefinition}.
     * <p>
     * This expression will be evaluated at runtime when executing the {@link UserTaskInstance}.
     *
     * @return the boolean condition
     */
    public String getExpression() {
        return expression;
    }


    /**
     * Returns the explanation of why the validation rule failed.
     *
     * @return the explanation of why the validation rule failed
     */
    public String getExplanation() {
        return explanation;
    }


    /**
     * Returns the input names involved in the validation rule.
     *
     * @return the input names involved in the validation rule
     */
    public List<String> getInputNames() {
        return inputNames;
    }

    public void addInputName(final String inputName) {
        inputNames.add(inputName);
    }


    /**
     * Return the type of the constraint
     *
     * @return the {@link ConstraintType} of the constraint
     */
    public ConstraintType getConstraintType() {
        return constraintType;
    }
}
