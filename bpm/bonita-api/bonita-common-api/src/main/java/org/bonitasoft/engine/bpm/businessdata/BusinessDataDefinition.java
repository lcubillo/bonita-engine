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
package org.bonitasoft.engine.bpm.businessdata;

import org.bonitasoft.engine.bpm.DescriptionElement;
import org.bonitasoft.engine.expression.Expression;

/**
 * @author Emmanuel Duchastenier
 * @author Baptiste Mesta
 */
public class BusinessDataDefinition extends DescriptionElement {

    private static final long serialVersionUID = 2L;

    private String description;

    private String className;

    private boolean multiple = false;

    private Expression defaultValueExpression;

    public BusinessDataDefinition(final String name, final Expression defaultValueExpression) {
        super(name);
        this.defaultValueExpression = defaultValueExpression;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Returns the class name of the Business Data type.
     *
     * @return the class name of the Business Data type.
     */
    public String getClassName() {
        return className;
    }

    public void setClassName(final String className) {
        this.className = className;
    }

    /**
     * Returns the default value of the business data, as an Expression to evaluate.
     *
     * @return the default value of the business data, as an Expression to evaluate.
     */
    public Expression getDefaultValueExpression() {
        return defaultValueExpression;
    }

    public void setDefaultValueExpression(final Expression defaultValueExpression) {
        this.defaultValueExpression = defaultValueExpression;
    }

    /**
     * Returns if this business data is handled as a List.
     *
     * @return true if this business data is handled as a List.
     */
    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(final boolean multiple) {
        this.multiple = multiple;
    }


}
