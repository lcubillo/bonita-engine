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
package org.bonitasoft.engine.bpm.data;

import org.bonitasoft.engine.bpm.DescriptionElement;
import org.bonitasoft.engine.expression.Expression;

/**
 * It forms part of the {@link org.bonitasoft.engine.bpm.process.ProcessDefinition}. It is used to define a date in the context of a
 * {@link org.bonitasoft.engine.bpm.process.ProcessDefinition} or a {@link org.bonitasoft.engine.bpm.flownode.FlowNodeDefinition}.
 *
 * @author Matthieu Chaffotte
 * @author Feng Hui
 * @author Celine Souchet
 * @version 6.4.1
 * @since 6.0.0
 */
public class DataDefinition extends DescriptionElement {


    private static final long serialVersionUID = -4126105713210029929L;

    private String description;

    private String type;

    private boolean transientData;

    private String className;

    private Expression defaultValueExpression;

    public DataDefinition(final String name, final Expression defaultValueExpression) {
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
     * Get the class name of the type of the data.
     *
     * @return The class name of the type of the data.
     * @since 6.0.0
     */
    public String getClassName() {
        return className;
    }

    public void setClassName(final String className) {
        this.className = className;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Is it transient?
     *
     * @return <code>true</code> if the data is transient, <code>false</code> otherwise.
     * @since 6.0.0
     */
    public boolean isTransientData() {
        return transientData;
    }

    public void setTransientData(final boolean transientData) {
        this.transientData = transientData;
    }

    /**
     * Get the expression representing the default value of the data.
     *
     * @return The default value of the data.
     * @since 6.0.0
     */
    public Expression getDefaultValueExpression() {
        return defaultValueExpression;
    }

    public void setDefaultValueExpression(final Expression defaultValueExpression) {
        this.defaultValueExpression = defaultValueExpression;
    }


}
