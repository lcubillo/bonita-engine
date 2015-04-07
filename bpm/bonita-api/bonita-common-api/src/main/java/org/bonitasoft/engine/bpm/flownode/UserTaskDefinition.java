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
package org.bonitasoft.engine.bpm.flownode;

import java.util.Objects;

import org.bonitasoft.engine.bpm.contract.ContractDefinition;

/**
 * A User Task is a typical “workflow” Task where a human performer performs the Task with the assistance of a
 * software application and is scheduled through a task list manager of some sort
 *
 * @author Baptiste Mesta
 * @author Matthieu Chaffotte
 */
public class UserTaskDefinition extends HumanTaskDefinition {


    private static final long serialVersionUID = -8168685139931497082L;

    private ContractDefinition contract;

    public UserTaskDefinition(final String name, final String actorName) {
        super(name, actorName);
    }

    public UserTaskDefinition(final long id, final String name, final String actorName) {
        super(id, name, actorName);
    }

    /**
     * Contract that must be respected when executing an instance of this user task
     *
     * @return
     *         the user task execution contract
     */
    public ContractDefinition getContract() {
        return contract;
    }

    public void setContract(final ContractDefinition contract) {
        this.contract = contract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserTaskDefinition that = (UserTaskDefinition) o;
        return Objects.equals(contract, that.contract);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), contract);
    }
}
