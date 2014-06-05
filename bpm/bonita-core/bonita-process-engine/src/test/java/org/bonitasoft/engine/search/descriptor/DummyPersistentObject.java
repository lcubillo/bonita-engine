package org.bonitasoft.engine.search.descriptor;

import org.bonitasoft.engine.persistence.PersistentObject;

public class DummyPersistentObject implements PersistentObject {

    private static final long serialVersionUID = -8872547982747785407L;

    private long id;

    public DummyPersistentObject(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getDiscriminator() {
        return null;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public void setTenantId(long id) {
    }
}
