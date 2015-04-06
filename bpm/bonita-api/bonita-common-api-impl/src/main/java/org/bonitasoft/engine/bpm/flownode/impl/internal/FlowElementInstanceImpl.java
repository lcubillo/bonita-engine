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
package org.bonitasoft.engine.bpm.flownode.impl.internal;

import java.util.Objects;

import org.bonitasoft.engine.bpm.NamedElement;
import org.bonitasoft.engine.bpm.flownode.FlowElementInstance;

/**
 * @author Emmanuel Duchastenier
 */
public abstract class FlowElementInstanceImpl extends NamedElement implements FlowElementInstance {

    private static final long serialVersionUID = -8382446613679794971L;

    private long parentContainerId;

    private long rootContainerId;

    private boolean aborting;

    public FlowElementInstanceImpl(final FlowElementInstance flowElementInstance) {
        super(flowElementInstance.getName());
        rootContainerId = flowElementInstance.getRootContainerId();
        parentContainerId = flowElementInstance.getRootContainerId();
    }

    @Override
    public long getRootContainerId() {
        return rootContainerId;
    }

    public void setRootContainerId(final long rootContainerId) {
        this.rootContainerId = rootContainerId;
    }

    @Override
    public long getParentContainerId() {
        return parentContainerId;
    }

    public void setParentContainerId(final long parentContainerId) {
        this.parentContainerId = parentContainerId;
    }

    @Override
    public boolean isAborting() {
        return aborting;
    }

    public void setAborting(final boolean aborting) {
        this.aborting = aborting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlowElementInstanceImpl)) return false;
        if (!super.equals(o)) return false;
        FlowElementInstanceImpl that = (FlowElementInstanceImpl) o;
        return Objects.equals(parentContainerId, that.parentContainerId) &&
                Objects.equals(rootContainerId, that.rootContainerId) &&
                Objects.equals(aborting, that.aborting);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), parentContainerId, rootContainerId, aborting);
    }
}
