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
package org.bonitasoft.engine.bpm.document;

import java.util.Objects;

import org.bonitasoft.engine.bpm.DescriptionElement;

/**
 * The definition of a document attached to a process definition
 *
 * @author Baptiste Mesta
 * @author Matthieu Chaffotte
 * @author Celine Souchet
 */
public class DocumentDefinition extends DescriptionElement {

    private static final long serialVersionUID = -2478390362777026410L;

    private String url;

    private String file;

    private String mimeType;

    private String fileName;

    /**
     * @param name the name of the document
     */
    public DocumentDefinition(final String name) {
        super(name);
    }


    /**
     * @return The URL for an external document
     */
    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * @return The file reference in the process resources
     */
    public String getFile() {
        return file;
    }

    public void setFile(final String file) {
        this.file = file;
    }

    /**
     * @return The mime type of the document's content.
     */
    public String getContentMimeType() {
        return mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(final String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * @return The name of the file of the document
     */
    public String getFileName() {
        return fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DocumentDefinition that = (DocumentDefinition) o;
        return Objects.equals(url, that.url) &&
                Objects.equals(file, that.file) &&
                Objects.equals(mimeType, that.mimeType) &&
                Objects.equals(fileName, that.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), url, file, mimeType, fileName);
    }
}
