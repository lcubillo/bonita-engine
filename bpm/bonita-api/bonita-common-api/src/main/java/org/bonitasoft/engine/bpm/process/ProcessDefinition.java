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
package org.bonitasoft.engine.bpm.process;

import java.util.Objects;

import org.bonitasoft.engine.bpm.NamedElement;

/**
 * Represents the Definition of a process. Gives access to basic information of the process, whereas it is deployed or not.
 * For information about deployment information, use {@link ProcessDeploymentInfo}.
 *
 * @author Baptiste Mesta
 * @author Matthieu Chaffotte
 * @see ProcessDeploymentInfo
 */
public class ProcessDefinition extends NamedElement {

    private static final long serialVersionUID = 2L;

    private String version;

    private String description;

    public ProcessDefinition(String name) {
        super(name);
    }


    public ProcessDefinition(final String name, final String version) {
        super(name);
        this.version = version;
    }


    /**
     * @return The version of the process definition
     */
    public String getVersion() {
        return version;
    }

    /**
     * @return The description of the process definition, as set at design-time.
     */
    public String getDescription() {

        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProcessDefinition)) return false;
        if (!super.equals(o)) return false;
        ProcessDefinition that = (ProcessDefinition) o;
        return Objects.equals(version, that.version) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), version, description);
    }
}
