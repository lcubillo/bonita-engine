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

import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.engine.operation.Operation;

/**
 * @author Elias Ricken de Medeiros
 *
 */
public class CallActivityDefinition extends ActivityDefinition {
    private static final long serialVersionUID = 2L;
    private final List<Operation> dataInputOperations;
    private final List<Operation> dataOutputOperations;
    private Expression callableElement;
    private Expression callableElementVersion;
    private CallableElementType callableElementType;

    public CallActivityDefinition(final String name) {
        super(name);
        dataInputOperations = new ArrayList<>(3);
        dataOutputOperations = new ArrayList<>(3);
    }

    public CallActivityDefinition(final long id, final String name) {
        super(id, name);
        dataInputOperations = new ArrayList<>(3);
        dataOutputOperations = new ArrayList<>(3);
    }

    public Expression getCallableElement() {
        return callableElement;
    }

    public void setCallableElement(final Expression callableElement) {
        this.callableElement = callableElement;
    }

    public Expression getCallableElementVersion() {
        return callableElementVersion;
    }

    public void setCallableElementVersion(final Expression callableElementVersion) {
        this.callableElementVersion = callableElementVersion;
    }

    public List<Operation> getDataInputOperations() {
        return Collections.unmodifiableList(dataInputOperations);
    }

    public void addDataInputOperation(final Operation dataInputOperation) {
        dataInputOperations.add(dataInputOperation);
    }

    public List<Operation> getDataOutputOperations() {
        return Collections.unmodifiableList(dataOutputOperations);
    }

    public void addDataOutputOperation(final Operation dataOutputOperation) {
        dataOutputOperations.add(dataOutputOperation);
    }

    public CallableElementType getCallableElementType() {
        return callableElementType;
    }

    public void setCallableElementType(final CallableElementType callableElementType) {
        this.callableElementType = callableElementType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CallActivityDefinition that = (CallActivityDefinition) o;
        return Objects.equals(dataInputOperations, that.dataInputOperations) &&
                Objects.equals(dataOutputOperations, that.dataOutputOperations) &&
                Objects.equals(callableElement, that.callableElement) &&
                Objects.equals(callableElementVersion, that.callableElementVersion) &&
                Objects.equals(callableElementType, that.callableElementType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dataInputOperations, dataOutputOperations, callableElement, callableElementVersion, callableElementType);
    }
}
