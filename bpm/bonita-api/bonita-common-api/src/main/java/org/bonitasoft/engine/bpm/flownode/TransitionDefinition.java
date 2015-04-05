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

import org.bonitasoft.engine.bpm.BaseElement;
import org.bonitasoft.engine.expression.Expression;

/**
 * Component of a process definition. It connects 2 {@link FlowNodeDefinition} between them.
 *
 * @author Baptiste Mesta
 * @author Celine Souchet
 */
public class TransitionDefinition extends BaseElement {


    private static final long serialVersionUID = -5629473055955264480L;

    private long source;

    private long target;

    private Expression expression;

    public TransitionDefinition() {
        this(-1, -1);
    }

    public TransitionDefinition(final long source, final long target) {
        this.source = source;
        this.target = target;
    }

    /**
     * @return The source of the transition
     */
    public long getSource() {
        return source;
    }

    public void setSource(final long source) {
        this.source = source;
    }

    /**
     * @return The target of the transition
     */
    public long getTarget() {
        return target;
    }

    public void setTarget(final long target) {
        this.target = target;
    }

    /**
     * @return The condition of the transition
     */
    public Expression getCondition() {
        return expression;
    }

    public void setCondition(final Expression expression) {
        this.expression = expression;
    }


}
