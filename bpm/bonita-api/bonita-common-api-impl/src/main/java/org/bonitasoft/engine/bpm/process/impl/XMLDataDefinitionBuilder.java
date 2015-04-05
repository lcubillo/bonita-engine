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
package org.bonitasoft.engine.bpm.process.impl;

import org.bonitasoft.engine.bpm.data.XMLDataDefinition;
import org.bonitasoft.engine.bpm.flownode.ActivityDefinition;
import org.bonitasoft.engine.bpm.flownode.CatchMessageEventTriggerDefinition;
import org.bonitasoft.engine.bpm.flownode.FlowElementContainerDefinition;
import org.bonitasoft.engine.expression.Expression;

/**
 * @author Feng Hui
 * @author Matthieu Chaffotte
 */
public class XMLDataDefinitionBuilder extends DataDefinitionBuilder {

    public XMLDataDefinitionBuilder(final ProcessDefinitionBuilder processDefinitionBuilder, final FlowElementContainerDefinition process,
            final String name, final String className, final Expression defaultValue) {
        super(processDefinitionBuilder, process, getTextData(name, className, defaultValue));
        processDefinitionBuilder.checkExpression(toString(), defaultValue);
    }

    public XMLDataDefinitionBuilder(final ProcessDefinitionBuilder processDefinitionBuilder, final FlowElementContainerDefinition process,
            final ActivityDefinition activity, final String name, final String className, final Expression defaultValue) {
        super(processDefinitionBuilder, process, activity, getTextData(name, className, defaultValue));
        processDefinitionBuilder.checkExpression(toString(), defaultValue);
    }

    public XMLDataDefinitionBuilder(final ProcessDefinitionBuilder processDefinitionBuilder, final FlowElementContainerDefinition container,
            final CatchMessageEventTriggerDefinition messageEventTrigger, final String name, final String className, final Expression defaultValue) {
        super(processDefinitionBuilder, container, messageEventTrigger, getTextData(name, className, defaultValue));
        processDefinitionBuilder.checkExpression(toString(), defaultValue);
    }

    private static XMLDataDefinition getTextData(final String name, final String className, final Expression defaultValue) {
        final XMLDataDefinition xml = new XMLDataDefinition(name, defaultValue);
        xml.setClassName(className);
        return xml;
    }

    /**
     * Sets the XML name space.
     * @param nameSpace XML name space
     * @return
     */
    public XMLDataDefinitionBuilder setNamespace(final String nameSpace) {
        ((XMLDataDefinition) getDataDefinition()).setNamespace(nameSpace);
        return this;
    }

    /**
     * Sets the XML element.
     * @param element XML element
     * @return
     */
    public XMLDataDefinitionBuilder setElement(final String element) {
        ((XMLDataDefinition) getDataDefinition()).setElement(element);
        return this;
    }

}
