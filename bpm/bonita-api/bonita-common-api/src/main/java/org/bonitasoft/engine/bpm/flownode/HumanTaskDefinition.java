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

import org.bonitasoft.engine.bpm.userfilter.UserFilterDefinition;

/**
 * A Human task is a task having an actor and that can be assigned.
 *
 * @author Baptiste Mesta
 * @author Celine Souchet
 */
public class HumanTaskDefinition extends TaskDefinition {
    private static final long serialVersionUID = 2L;

    private final String actorName;

    private UserFilterDefinition userFilterDefinition;

    private Long expectedDuration;

    private String priority;

    public HumanTaskDefinition(final String name, final String actorName) {
        super(name);
        this.actorName = actorName;
    }

    public HumanTaskDefinition(final long id, final String name, final String actorName) {
        super(id, name);
        this.actorName = actorName;
    }

    public String getActorName() {
        return actorName;
    }

    public UserFilterDefinition getUserFilter() {
        return userFilterDefinition;
    }

    public void setUserFilter(final UserFilterDefinition userFilterDefinition) {
        this.userFilterDefinition = userFilterDefinition;
    }

    public Long getExpectedDuration() {
        return expectedDuration;
    }

    public void setExpectedDuration(final long expectedDuration) {
        this.expectedDuration = expectedDuration;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(final String priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HumanTaskDefinition that = (HumanTaskDefinition) o;
        return Objects.equals(actorName, that.actorName) &&
                Objects.equals(userFilterDefinition, that.userFilterDefinition) &&
                Objects.equals(expectedDuration, that.expectedDuration) &&
                Objects.equals(priority, that.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), actorName, userFilterDefinition, expectedDuration, priority);
    }
}
