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
import java.util.List;

import org.bonitasoft.engine.expression.Expression;


/**
 * @author Elias Ricken de Medeiros
 */
public abstract class MessageEventTriggerDefinition extends EventTriggerDefinition {

    private static final long serialVersionUID = 2L;
    private String messageName;
    private List<CorrelationDefinition> correlations;

    public MessageEventTriggerDefinition(String messageName) {
        this.messageName = messageName;
        this.correlations = new ArrayList<>();
    }

    public MessageEventTriggerDefinition(String messageName, List<CorrelationDefinition> correlations) {
        this.messageName = messageName;
        this.correlations = correlations;
    }

    public MessageEventTriggerDefinition(MessageEventTriggerDefinition messageEventTriggerDefinition) {
        this.messageName = messageEventTriggerDefinition.getMessageName();
        this.correlations = new ArrayList<>(messageEventTriggerDefinition.getCorrelations());
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }


    public List<CorrelationDefinition> getCorrelations() {
        return correlations;
    }

    public void setCorrelations(List<CorrelationDefinition> correlations) {
        this.correlations = correlations;
    }

    public void addCorrelation(Expression key, Expression value) {
        correlations.add(new CorrelationDefinition(key, value));
    }
}
