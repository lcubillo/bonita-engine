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
package org.bonitasoft.engine.core.document.model.builder.impl;

import org.bonitasoft.engine.core.document.model.SDocumentMetadata;
import org.bonitasoft.engine.core.document.model.builder.SDocumentMetadataBuilder;
import org.bonitasoft.engine.core.document.model.impl.SDocumentMetadataImpl;

/**
 * @author Emmanuel Duchastenier
 * @author Nicolas Chabanoles
 * @author Zhang Bole
 */
public class SDocumentMetadataBuilderImpl implements SDocumentMetadataBuilder {

    private final SDocumentMetadataImpl sDocumentMetadata;

    public SDocumentMetadataBuilderImpl(final SDocumentMetadataImpl sDocumentMetadata) {
        super();
        this.sDocumentMetadata = sDocumentMetadata;
    }


    @Override
    public SDocumentMetadataBuilder setName(final String documentName) {
        sDocumentMetadata.setName(documentName);
        return this;
    }

    @Override
    public SDocumentMetadataBuilder setAuthor(final long author) {
        sDocumentMetadata.setAuthor(author);
        return this;
    }

    @Override
    public SDocumentMetadataBuilder setHasContent(final boolean hasContent) {
        sDocumentMetadata.setHasContent(hasContent);
        return this;
    }

    @Override
    public SDocumentMetadataBuilder setStorageId(final String storageId) {
        sDocumentMetadata.setContentStorageId(storageId);
        return this;
    }

    @Override
    public SDocumentMetadataBuilder setCreationDate(final long creationDate) {
        sDocumentMetadata.setCreationDate(creationDate);
        return this;
    }

    @Override
    public SDocumentMetadataBuilder setContentFileName(final String contentFileName) {
        sDocumentMetadata.setContentFileName(contentFileName);
        return this;
    }

    @Override
    public SDocumentMetadataBuilder setContentMimeType(final String contentMimeType) {
        sDocumentMetadata.setContentMimeType(contentMimeType);
        return this;
    }

    @Override
    public SDocumentMetadataBuilder setURL(final String url) {
        sDocumentMetadata.setURL(url);
        return this;
    }

    @Override
    public SDocumentMetadata done() {
        return sDocumentMetadata;
    }
}
