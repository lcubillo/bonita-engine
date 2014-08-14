/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.engine.core.document.model.impl;

import org.bonitasoft.engine.core.document.model.SDocumentMapping;

/**
 * @author Nicolas Chabanoles
 * @author Baptiste Mesta
 */
public class SDocumentMappingImpl implements SDocumentMapping {

    private static final long serialVersionUID = 3494829428880067405L;

    private long id;

    private long tenantId;

    private long processInstanceId;

    private long documentMetadataId;

    public SDocumentMappingImpl() {
    }



    @Override
    public String getDiscriminator() {
        return SDocumentMappingImpl.class.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTenantId() {
        return tenantId;
    }

    public void setTenantId(long tenantId) {
        this.tenantId = tenantId;
    }

    public long getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(long processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public long getDocumentMetadataId() {
        return documentMetadataId;
    }

    public void setDocumentMetadataId(long documentMetadataId) {
        this.documentMetadataId = documentMetadataId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SDocumentMappingImpl that = (SDocumentMappingImpl) o;

        if (documentMetadataId != that.documentMetadataId) return false;
        if (id != that.id) return false;
        if (processInstanceId != that.processInstanceId) return false;
        if (tenantId != that.tenantId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (tenantId ^ (tenantId >>> 32));
        result = 31 * result + (int) (processInstanceId ^ (processInstanceId >>> 32));
        result = 31 * result + (int) (documentMetadataId ^ (documentMetadataId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "SDocumentMappingImpl{" +
                "id=" + id +
                ", tenantId=" + tenantId +
                ", processInstanceId=" + processInstanceId +
                ", documentMetadataId=" + documentMetadataId +
                '}';
    }
}
