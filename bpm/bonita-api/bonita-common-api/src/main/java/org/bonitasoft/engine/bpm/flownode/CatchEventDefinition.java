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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


/**
 * @author Elias Ricken de Medeiros
 * @author Matthieu Chaffotte
 */
public abstract class CatchEventDefinition extends EventDefinition {
    private static final long serialVersionUID = 2L;

    private final List<TimerEventTriggerDefinition> timerEventTriggers;

    private final List<CatchMessageEventTriggerDefinition> messageEventTriggers;

    private final List<CatchSignalEventTriggerDefinition> signalEventTriggers;

    private final List<CatchErrorEventTriggerDefinition> errorEventTriggers;

    private boolean isInterrupting = true;

    public CatchEventDefinition(final String name) {
        super(name);
        timerEventTriggers = new ArrayList<>(1);
        messageEventTriggers = new ArrayList<>(1);
        signalEventTriggers = new ArrayList<>(1);
        errorEventTriggers = new ArrayList<>(1);
    }

    public CatchEventDefinition(final long id, final String name) {
        super(id, name);
        timerEventTriggers = new ArrayList<>(1);
        messageEventTriggers = new ArrayList<>(1);
        signalEventTriggers = new ArrayList<>(1);
        errorEventTriggers = new ArrayList<>(1);
    }

    public List<TimerEventTriggerDefinition> getTimerEventTriggerDefinitions() {
        return Collections.unmodifiableList(timerEventTriggers);
    }

    public void addTimerEventTrigger(final TimerEventTriggerDefinition timerEventDefinition) {
        timerEventTriggers.add(timerEventDefinition);
        addEventTrigger(timerEventDefinition);
    }

    public List<CatchMessageEventTriggerDefinition> getMessageEventTriggerDefinitions() {
        return Collections.unmodifiableList(messageEventTriggers);
    }

    public void addMessageEventTrigger(final CatchMessageEventTriggerDefinition messageEventTrigger) {
        messageEventTriggers.add(messageEventTrigger);
        addEventTrigger(messageEventTrigger);
    }

    public List<CatchSignalEventTriggerDefinition> getSignalEventTriggerDefinitions() {
        return Collections.unmodifiableList(signalEventTriggers);
    }

    public void addSignalEventTrigger(final CatchSignalEventTriggerDefinition signalEventTrigger) {
        signalEventTriggers.add(signalEventTrigger);
        addEventTrigger(signalEventTrigger);
    }

    public void addErrorEventTrigger(final CatchErrorEventTriggerDefinition errorEventTrigger) {
        errorEventTriggers.add(errorEventTrigger);
        addEventTrigger(errorEventTrigger);
    }

    public List<CatchErrorEventTriggerDefinition> getErrorEventTriggerDefinitions() {
        return Collections.unmodifiableList(errorEventTriggers);
    }

    public boolean isInterrupting() {
        return isInterrupting;
    }

    public void setInterrupting(final boolean isInterrupting) {
        this.isInterrupting = isInterrupting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CatchEventDefinition that = (CatchEventDefinition) o;
        return Objects.equals(isInterrupting, that.isInterrupting) &&
                Objects.equals(timerEventTriggers, that.timerEventTriggers) &&
                Objects.equals(messageEventTriggers, that.messageEventTriggers) &&
                Objects.equals(signalEventTriggers, that.signalEventTriggers) &&
                Objects.equals(errorEventTriggers, that.errorEventTriggers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), timerEventTriggers, messageEventTriggers, signalEventTriggers, errorEventTriggers, isInterrupting);
    }
}
