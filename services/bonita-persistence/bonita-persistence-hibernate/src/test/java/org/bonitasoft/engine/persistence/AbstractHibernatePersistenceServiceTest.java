package org.bonitasoft.engine.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hamcrest.CoreMatchers;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.bonitasoft.engine.log.technical.TechnicalLoggerService;

@RunWith(MockitoJUnitRunner.class)
public class AbstractHibernatePersistenceServiceTest {

    // We can't call the constructor as Hibernate currently tries to instantiate a Connection
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private AbstractHibernatePersistenceService persistenceService;

    @Before
    public void setUp() throws Exception {
        doReturn("#").when(persistenceService).getLikeEscapeCharacter();
        given(persistenceService.getClassAliasMappings()).willReturn(
                Collections.singletonMap("org.bonitasoft.engine.persistence.DummyPersistentObject", "DummyPersistentObject"));
    }

    @Test
    public void should_getQueryFilters_append_OR_clause_when_wordSearch_is_enabled() throws Exception {
        StringBuilder queryBuilder = new StringBuilder();

        persistenceService.buildLikeClauseForOneFieldOneTerm(queryBuilder, "myField", "foo", true);

        assertThat(queryBuilder.toString(), CoreMatchers.containsString("LIKE 'foo%'"));
        assertThat(queryBuilder.toString(), CoreMatchers.containsString("LIKE '% foo%'"));
    }

    @Test
    public void should_getQueryFilters_append_OR_clause_when_wordSearch_is_not_enabled() throws Exception {
        StringBuilder queryBuilder = new StringBuilder();

        persistenceService.buildLikeClauseForOneFieldOneTerm(queryBuilder, "myField", "foo", false);

        assertThat(queryBuilder.toString(), CoreMatchers.containsString("LIKE 'foo%'"));
        assertThat(queryBuilder.toString(), CoreMatchers.not(CoreMatchers.containsString("LIKE '% foo%'")));
    }

    @Test
    public void should_filter_the_request_for_each_field_term_couple() throws Exception {
        StringBuilder builder = new StringBuilder();
        SearchFields searchFields = new SearchFields(
                Arrays.asList("term 1", "term 2"),
                createFields(DummyPersistentObject.class, Arrays.asList("field 1", "field 2")));

        persistenceService.handleMultipleFilters(builder, searchFields, new HashSet<String>(), false);

        assertEquals(" WHERE (" +
                "DummyPersistentObject.field 2 LIKE 'term 1%' ESCAPE '#' OR " +
                "DummyPersistentObject.field 2 LIKE 'term 2%' ESCAPE '#' OR " +
                "DummyPersistentObject.field 1 LIKE 'term 1%' ESCAPE '#' OR " +
                "DummyPersistentObject.field 1 LIKE 'term 2%' ESCAPE '#')", builder.toString());
    }

    @Test
    public void should_not_add_anything_to_the_query_when_there_is_no_term() throws Exception {
        StringBuilder builder = new StringBuilder();
        SearchFields searchFields = new SearchFields(
                Collections.<String> emptyList(),
                createFields(DummyPersistentObject.class, Arrays.asList("field 1", "field 2")));

        persistenceService.handleMultipleFilters(builder, searchFields, new HashSet<String>(), false);

        assertEquals(" WHERE ( OR )", builder.toString());
    }

    @Test
    public void should_remove_specific_filters_from_the_query() throws Exception {
        StringBuilder builder = new StringBuilder();
        SearchFields searchFields = new SearchFields(
                Arrays.asList("term"),
                createFields(DummyPersistentObject.class, Arrays.asList("field 1", "field 2")));

        persistenceService.handleMultipleFilters(
                builder,
                searchFields,
                new HashSet<String>(Arrays.asList("DummyPersistentObject.field 1")), false);

        assertEquals(
                " WHERE (DummyPersistentObject.field 2 LIKE 'term%' ESCAPE '#')",
                builder.toString());
    }

    @Test
    public void should_not_add_anything_to_the_query_when_there_is_no_fields() throws Exception {
        StringBuilder builder = new StringBuilder();
        SearchFields searchFields = new SearchFields(
                Arrays.asList("term"),
                createFields(DummyPersistentObject.class, Collections.<String> emptyList()));

        persistenceService.handleMultipleFilters(builder, searchFields, new HashSet<String>(), false);

        assertEquals("", builder.toString());
    }

    @Test
    public void should_include_word_search_in_the_filter() throws Exception {
        StringBuilder builder = new StringBuilder();
        SearchFields searchFields = new SearchFields(
                Arrays.asList("term"),
                createFields(DummyPersistentObject.class, Arrays.asList("field")));

        persistenceService.handleMultipleFilters(builder, searchFields, new HashSet<String>(), true);

        assertEquals(" WHERE (" +
                "DummyPersistentObject.field LIKE 'term%' ESCAPE '#' OR " +
                "DummyPersistentObject.field LIKE '% term%' ESCAPE '#')", builder.toString());
    }

    @Test
    public void should_start_search_where_clause_with_an_end_when_the_request_already_contains_a_where() throws Exception {
        StringBuilder builder = new StringBuilder("WHERE whatever");
        SearchFields searchFields = new SearchFields(
                Arrays.asList("term"),
                createFields(DummyPersistentObject.class, Arrays.asList("field")));

        persistenceService.handleMultipleFilters(builder, searchFields, new HashSet<String>(), false);

        assertEquals(
                "WHERE whatever AND (DummyPersistentObject.field LIKE 'term%' ESCAPE '#')",
                builder.toString());
    }

    @Test
    public void should_escape_search_term() throws Exception {
        StringBuilder builder = new StringBuilder();
        SearchFields searchFields = new SearchFields(
                Arrays.asList(" 'term' # % _"),
                createFields(DummyPersistentObject.class, Arrays.asList("field")));

        persistenceService.handleMultipleFilters(builder, searchFields, new HashSet<String>(), false);

        assertEquals(
                " WHERE (DummyPersistentObject.field LIKE ' ''term'' ## #% #_%' ESCAPE '#')",
                builder.toString());
    }

    private Map<Class<? extends PersistentObject>, Set<String>> createFields(Class<DummyPersistentObject> clazz, List<String> fields) {
        return Collections.
                <Class<? extends PersistentObject>, Set<String>> singletonMap(clazz, new HashSet<String>(fields));
    }
}
