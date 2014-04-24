package org.bonitasoft.engine.test.persistence.repository;

import java.util.List;

import org.bonitasoft.engine.identity.model.SUser;
import org.hibernate.Query;
import org.hibernate.SessionFactory;


public class ProcessInstanceRepository extends TestRepository {

    public ProcessInstanceRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @SuppressWarnings("unchecked")
    public List<Long> getPossibleUserIdsOfPendingTasks(long activityInstanceId) {
        Query namedQuery = getNamedQuery("getPossibleUserIdsOfPendingTasks");
        namedQuery.setParameter("humanTaskInstanceId", activityInstanceId);
        return namedQuery.list();
    }

    @SuppressWarnings("unchecked")
    public List<SUser> searchPossibleUserIdsOfPendingTasks(long activityInstanceId) {
        Query namedQuery = getNamedQuery("searchSUserWhoCanStartPendingTask");
        namedQuery.setParameter("humanTaskInstanceId", activityInstanceId);
        return namedQuery.list();
    }

    public long getNumberOfSUserWhoCanStartPendingTask(long activityInstanceId) {
        Query namedQuery = getNamedQuery("getNumberOfSUserWhoCanStartPendingTask");
        namedQuery.setParameter("humanTaskInstanceId", activityInstanceId);
        return ((Number) namedQuery.uniqueResult()).longValue();
    }
}
