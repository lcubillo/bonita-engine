/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.engine.core.document.model.impl;

import org.bonitasoft.engine.core.document.model.SDocumentMetadata;

/**
 * @author Baptiste Mesta
 */
public class SDocumentMetadataImpl implements SDocumentMetadata {

    private long id;

    private long tenantId;

    private String name;

    private long author;

    private long creationDate;

    private boolean hasContent;

    private String contentFileName;

    private String contentMimeType;

    private String contentStorageId;

    private String URL;


    public long getId() {
        return id;
    }

    @Override
    public String getDiscriminator() {
        return SDocumentMetadataImpl.class.getName();
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getTenantId() {
        return tenantId;
    }

    @Override
    public void setTenantId(long tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public long getAuthor() {
        return author;
    }

    public void setAuthor(long author) {
        this.author = author;
    }

    @Override
    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isHasContent() {
        return hasContent;
    }

    public void setHasContent(boolean hasContent) {
        this.hasContent = hasContent;
    }

    @Override
    public String getContentFileName() {
        return contentFileName;
    }

    @Override
    public boolean hasContent() {
        return hasContent;
    }

    public void setContentFileName(String contentFileName) {
        this.contentFileName = contentFileName;
    }

    @Override
    public String getContentMimeType() {
        return contentMimeType;
    }

    public void setContentMimeType(String contentMimeType) {
        this.contentMimeType = contentMimeType;
    }

    @Override
    public String getContentStorageId() {
        return contentStorageId;
    }

    public void setContentStorageId(String contentStorageId) {
        this.contentStorageId = contentStorageId;
    }


    @Override
    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SDocumentMetadataImpl that = (SDocumentMetadataImpl) o;

        if (author != that.author) return false;
        if (creationDate != that.creationDate) return false;
        if (hasContent != that.hasContent) return false;
        if (id != that.id) return false;
        if (tenantId != that.tenantId) return false;
        if (URL != null ? !URL.equals(that.URL) : that.URL != null) return false;
        if (contentFileName != null ? !contentFileName.equals(that.contentFileName) : that.contentFileName != null)
            return false;
        if (contentMimeType != null ? !contentMimeType.equals(that.contentMimeType) : that.contentMimeType != null)
            return false;
        if (contentStorageId != null ? !contentStorageId.equals(that.contentStorageId) : that.contentStorageId != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (tenantId ^ (tenantId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (author ^ (author >>> 32));
        result = 31 * result + (int) (creationDate ^ (creationDate >>> 32));
        result = 31 * result + (hasContent ? 1 : 0);
        result = 31 * result + (contentFileName != null ? contentFileName.hashCode() : 0);
        result = 31 * result + (contentMimeType != null ? contentMimeType.hashCode() : 0);
        result = 31 * result + (contentStorageId != null ? contentStorageId.hashCode() : 0);
        result = 31 * result + (URL != null ? URL.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SDocumentMetadataImpl{" +
                "id=" + id +
                ", tenantId=" + tenantId +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", creationDate=" + creationDate +
                ", hasContent=" + hasContent +
                ", contentFileName='" + contentFileName + '\'' +
                ", contentMimeType='" + contentMimeType + '\'' +
                ", contentStorageId='" + contentStorageId + '\'' +
                ", URL='" + URL + '\'' +
                '}';
    }
}
