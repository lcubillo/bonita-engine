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
package org.bonitasoft.engine.bpm;

/**
 * Interface <code>BaseElement</code> describes a <code>NamedElement</code>.
 *
 * @author Matthieu Chaffotte
 * @since 6.0.0
 */
public abstract class DescriptionElement extends NamedElement {

    private static final long serialVersionUID = 9005761084270734316L;

    private String description;

    public DescriptionElement(final String name, final String description) {
        super(name);
        this.description = description;
    }

    public DescriptionElement(final String name) {
        super(name);
    }

    /**
     * Gets the element description.
     *
     * @return the element description
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
