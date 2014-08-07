package org.bonitasoft.engine.test;

import java.util.Date;

import org.bonitasoft.engine.session.APISession;

/**
 * @author Matthieu Chaffotte
 */
public class FakeSession implements APISession {

    private static final long serialVersionUID = -957530454714716273L;

    private long id;

    private final Date creationDate;

    private long duration;

    private final String userName;

    private final long userId;

    private final String tenant;

    private final long tenantId;

    private boolean technicalUser = false;

    public FakeSession(final APISession session) {
        id = session.getId();
        creationDate = session.getCreationDate();
        duration = session.getDuration();
        userName = session.getUserName();
        tenant = session.getTenantName();
        tenantId = session.getTenantId();
        userId = session.getUserId();
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public long getDuration() {
        return duration;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public long getUserId() {
        return userId;
    }

    public void setDuration(final long newDuration) {
        duration = newDuration;
    }

    public void setId(final long identifier) {
        id = identifier;
    }

    @Override
    public String getTenantName() {
        return tenant;
    }

    @Override
    public long getTenantId() {
        return tenantId;
    }

    @Override
    public boolean isTechnicalUser() {
        return technicalUser;
    }

    public void setTechnicalUser(final boolean technicalUser) {
        this.technicalUser = technicalUser;
    }

    @Override
    public String getProgramName() {
        return "UNTRUSTED_APPLICATION";
    }

}
