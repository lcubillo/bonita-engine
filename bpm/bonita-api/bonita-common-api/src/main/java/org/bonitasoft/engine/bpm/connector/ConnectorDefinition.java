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
package org.bonitasoft.engine.bpm.connector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.bonitasoft.engine.bpm.NamedElement;
import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.engine.operation.Operation;

/**
 * The connector definition associated to a process definition or a flow node definition
 *
 * @author Baptiste Mesta
 * @author Celine Souchet
 */
public class ConnectorDefinition extends NamedElement {


    private static final long serialVersionUID = 1892648036453422626L;

    private final String connectorId;

    private final Map<String, Expression> inputs = new HashMap<String, Expression>();

    private final List<Operation> outputs = new ArrayList<Operation>();

    private final ConnectorEvent activationEvent;

    private final String version;

    private FailAction failAction = FailAction.FAIL;

    private String errorCode;

    public ConnectorDefinition(final String name, final String connectorId, final String version, final ConnectorEvent activationEvent) {
        super(name);
        this.connectorId = connectorId;
        this.version = version;
        this.activationEvent = activationEvent;
    }

    /**
     * @return The identifier of the connector definition
     */
    public String getConnectorId() {
        return connectorId;
    }

    /**
     * @return The version of the connector
     */
    public String getVersion() {
        return version;
    }

    /**
     * @return The inputs of the connector
     */
    public Map<String, Expression> getInputs() {
        return inputs;
    }

    /**
     * @return The outputs of the connector
     */
    public List<Operation> getOutputs() {
        return outputs;
    }

    public void addInput(final String name, final Expression expression) {
        inputs.put(name, expression);
    }

    public void addOutput(final Operation operation) {
        outputs.add(operation);
    }

    /**
     * @return The event to activate the connector
     */

    public ConnectorEvent getActivationEvent() {
        return activationEvent;
    }

    /**
     * @return The fail action of the connector
     */
    public FailAction getFailAction() {
        return failAction;
    }

    public void setFailAction(final FailAction failAction) {
        this.failAction = failAction;
    }

    /**
     * @return The error code of the connector
     */
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ConnectorDefinition that = (ConnectorDefinition) o;
        return Objects.equals(connectorId, that.connectorId) &&
                Objects.equals(inputs, that.inputs) &&
                Objects.equals(outputs, that.outputs) &&
                Objects.equals(activationEvent, that.activationEvent) &&
                Objects.equals(version, that.version) &&
                Objects.equals(failAction, that.failAction) &&
                Objects.equals(errorCode, that.errorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), connectorId, inputs, outputs, activationEvent, version, failAction, errorCode);
    }
}
