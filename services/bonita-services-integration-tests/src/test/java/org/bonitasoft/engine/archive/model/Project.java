package org.bonitasoft.engine.archive.model;

import org.bonitasoft.engine.persistence.TenantPersistentObject;

public class Project extends SPersistentObjectImpl {

    private static final long serialVersionUID = 1L;

    private String name;

    public Project(final String name) {
        this.name = name;
    }

    public Project() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String getDiscriminator() {
        return Project.class.getName();
    }

    @Override
    public long getSourceObjectId() {
        return 0;
    }

    @Override
    public Class<? extends TenantPersistentObject> getPersistentObjectInterface() {
        return Project.class;
    }

}
