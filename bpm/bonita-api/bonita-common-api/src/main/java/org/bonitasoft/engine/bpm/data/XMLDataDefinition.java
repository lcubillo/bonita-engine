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

import java.util.Objects;

import org.bonitasoft.engine.expression.Expression;

/**
 * Represents a {@link DataDefinition} of XML type.
 *
 * @author Feng Hui
 * @author Celine Souchet
 * @since 6.0.0
 * @version 6.4.1
 */
public class XMLDataDefinition extends DataDefinition {


    private static final long serialVersionUID = 3614847378996945363L;

    private String namespace;

    private String element;

    public XMLDataDefinition(final String name, final Expression defaultValueExpression) {
        super(name, defaultValueExpression);
    }

    /**
     * Get the namespace for the XML format of the data
     *
     * @return The namespace for the XML format of the data.
     * @since 6.0.0
     */

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(final String namespace) {
        this.namespace = namespace;
    }

    /**
     * Get the element for the XML format of the data
     *
     * @return The element for the XML format of the data.
     * @since 6.0.0
     */

    public String getElement() {
        return element;
    }

    public void setElement(final String element) {
        this.element = element;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof XMLDataDefinition)) return false;
        if (!super.equals(o)) return false;
        XMLDataDefinition that = (XMLDataDefinition) o;
        return Objects.equals(namespace, that.namespace) &&
                Objects.equals(element, that.element);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), namespace, element);
    }
}
