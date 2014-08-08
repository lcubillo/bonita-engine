package org.bonitasoft.engine.identity.model;

import org.bonitasoft.engine.persistence.TenantPersistentObject;

/**
 * @author Vincent Elcrin
 */
public interface SSingleNamedElement extends TenantPersistentObject {

    /**
     * Gets the name of the element.
     *
     * @return the element name
     */
    String getName();

    /**
     * Obtains the description of the element
     *
     * @return the element description
     */
    String getDescription();
}
