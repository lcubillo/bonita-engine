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
import java.util.Objects;

/**
 * An <code>ComplexInputDefinition</code> defines
 *
 * @author Laurent Leseigneur
 * @since 7.0
 */
public class ComplexInputDefinition extends InputDefinition {

    private static final long serialVersionUID = 2836592506382887928L;
    private final List<SimpleInputDefinition> simpleInputDefinitions;
    private final List<ComplexInputDefinition> complexInputDefinitions;

    public ComplexInputDefinition(final String name, final String description) {
        this(name, description, false, null, null);
    }

    public ComplexInputDefinition(final String name, final String description, final boolean multiple) {
        this(name, description, multiple, null, null);
    }

    public ComplexInputDefinition(final String name, final String description, final List<SimpleInputDefinition> simpleInputDefinitions,
                                  final List<ComplexInputDefinition> complexInputDefinitions) {
        this(name, description, false, simpleInputDefinitions, complexInputDefinitions);
    }

    public ComplexInputDefinition(final String name, final String description, final boolean multiple,
                                  final List<SimpleInputDefinition> simpleInputDefinitions,
                                  final List<ComplexInputDefinition> complexInputDefinitions) {
        super(name, description, multiple);
        this.simpleInputDefinitions = new ArrayList<>();
        this.complexInputDefinitions = new ArrayList<>();
        if (simpleInputDefinitions != null) {
            for (final SimpleInputDefinition simpleInputDefinition : simpleInputDefinitions) {
                this.simpleInputDefinitions.add(simpleInputDefinition);
            }
        }
        if (complexInputDefinitions != null) {
            for (final ComplexInputDefinition complexInputDefinition : complexInputDefinitions) {
                this.complexInputDefinitions.add(complexInputDefinition);
            }
        }
    }

    public List<SimpleInputDefinition> getSimpleInputs() {
        return simpleInputDefinitions;
    }

    public List<ComplexInputDefinition> getComplexInputs() {
        return complexInputDefinitions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplexInputDefinition)) return false;
        if (!super.equals(o)) return false;
        ComplexInputDefinition that = (ComplexInputDefinition) o;
        return Objects.equals(simpleInputDefinitions, that.simpleInputDefinitions) &&
                Objects.equals(complexInputDefinitions, that.complexInputDefinitions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), simpleInputDefinitions, complexInputDefinitions);
    }
}
