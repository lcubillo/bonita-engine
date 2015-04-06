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
package org.bonitasoft.engine.profile.impl;

import java.util.Objects;

import org.bonitasoft.engine.bpm.NamedElement;
import org.bonitasoft.engine.profile.ProfileEntry;

/**
 * @author Celine Souchet
 */
public class ProfileEntryImpl extends NamedElement implements ProfileEntry {

    private static final long serialVersionUID = -6338293070515058067L;

    private long profileId;

    private long parentId;

    private String description;

    private long index;

    private String type;

    private String page;

    private boolean custom;

    public ProfileEntryImpl(final String name) {
        super(name);
    }

    public ProfileEntryImpl(final String name, final long profileId) {
        super(name);
        this.profileId = profileId;
    }

    @Override
    public long getParentId() {
        return parentId;
    }

    public void setParentId(final long parentId) {
        this.parentId = parentId;
    }

    @Override
    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(final long profileId) {
        this.profileId = profileId;
    }

    @Override
    public long getIndex() {
        return index;
    }

    public void setIndex(final long index) {
        this.index = index;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    @Override
    public String getPage() {
        return page;
    }

    public void setPage(final String page) {
        this.page = page;
    }

    @Override
    public boolean isCustom() {
        return custom;
    }

    public void setCustom(final boolean custom) {
        this.custom = custom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfileEntryImpl)) return false;
        if (!super.equals(o)) return false;
        ProfileEntryImpl that = (ProfileEntryImpl) o;
        return Objects.equals(profileId, that.profileId) &&
                Objects.equals(parentId, that.parentId) &&
                Objects.equals(index, that.index) &&
                Objects.equals(custom, that.custom) &&
                Objects.equals(description, that.description) &&
                Objects.equals(type, that.type) &&
                Objects.equals(page, that.page);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), profileId, parentId, description, index, type, page, custom);
    }
}
