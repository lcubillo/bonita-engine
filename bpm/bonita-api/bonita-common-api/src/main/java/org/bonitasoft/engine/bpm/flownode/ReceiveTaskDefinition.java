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

import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.engine.operation.Operation;

/**
 * @author Julien Molinaro
 * @author Matthieu Chaffotte
 */
public class ReceiveTaskDefinition extends TaskDefinition {
    private static final long serialVersionUID = 2L;

    private final CatchMessageEventTriggerDefinition trigger;

    public ReceiveTaskDefinition(final String name, final String messageName) {
        super(name);
        trigger = new CatchMessageEventTriggerDefinition(messageName);
    }

    public ReceiveTaskDefinition(final long id, final String name, final CatchMessageEventTriggerDefinition catchMessageEventTriggerDefinition) {
        super(id, name);
        trigger = new CatchMessageEventTriggerDefinition(catchMessageEventTriggerDefinition);
    }

    public void addCorrelation(final Expression key, final Expression value) {
        trigger.addCorrelation(key, value);
    }

    public void addMessageOperation(final Operation operation) {
        trigger.addOperation(operation);
    }

    public CatchMessageEventTriggerDefinition getTrigger() {
        return trigger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ReceiveTaskDefinition that = (ReceiveTaskDefinition) o;
        return Objects.equals(trigger, that.trigger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), trigger);
    }
}
