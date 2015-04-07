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


import java.util.Objects;

/**
 * An <code>ComplexInputDefinition</code> defines
 *
 * @author Laurent Leseigneur
 * @since 7.0
 */
public class SimpleInputDefinition extends InputDefinition {

    private static final long serialVersionUID = 2L;

    private final Type type;

    public SimpleInputDefinition(final String name, final Type type, final String description, final boolean multiple) {
        super(name, description, multiple);
        this.type = type;
    }

    public SimpleInputDefinition(final String name, final Type type, final String description) {
        super(name, description, false);
        this.type = type;
    }

    /**
     * Gets the type of the input.
     *
     * @return the input {@link Type}
     */
    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SimpleInputDefinition that = (SimpleInputDefinition) o;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

    @Override
    public String toString() {
        return "SimpleInputDefinition{" +
                "type=" + type +
                "} " + super.toString();
    }
}
