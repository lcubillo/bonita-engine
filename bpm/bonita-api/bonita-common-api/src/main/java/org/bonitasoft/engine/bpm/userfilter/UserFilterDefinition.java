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
package org.bonitasoft.engine.bpm.userfilter;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.engine.bpm.NamedElement;
import org.bonitasoft.engine.expression.Expression;

/**
 * Design-time object of a UserFilter (also known as actor filter).
 * A UserFilter is a derogation of the standard actor mapping mechanism.
 * If a human task has a userfilter, its execution determines the list of users that the task is pending for. Other users will not see it as available for them.
 * <code>UserFilter</code>s have many similarities to connector in their definition and execution.
 *
 * @author Baptiste Mesta
 * @see org.bonitasoft.engine.bpm.connector.ConnectorDefinition
 */
public class UserFilterDefinition extends NamedElement {


    private static final long serialVersionUID = -6045216424839658552L;

    private final String filterId;

    private final String version;

    private final Map<String, Expression> inputs = new HashMap<>();

    public UserFilterDefinition(final String name, final String filterId, final String version) {
        super(name);
        this.filterId = filterId;
        this.version = version;
    }

    /**
     * @return the ID of its definition.
     */
    public String getUserFilterId() {
        return filterId;
    }

    /**
     * @return the map of expressions that serves as input for the execution of the user filter.
     */
    public Map<String, Expression> getInputs() {
        return inputs;
    }

    public void addInput(final String name, final Expression expression) {
        inputs.put(name, expression);
    }

    /**
     * @return the version of its definition.
     */
    public String getVersion() {
        return version;
    }


}
