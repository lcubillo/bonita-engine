package org.bonitasoft.engine.search.descriptor;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.bonitasoft.engine.persistence.PersistentObject;
import org.bonitasoft.engine.persistence.SearchFields;

public class SearchEntityDescriptorTest {

    @Test
    public void should_add_term_to_the_search_term() throws Exception {
        SearchEntityDescriptor descriptor = createSearchEntityDescriptor(Arrays.asList("field 1", "field 2"));

        SearchFields searchTerm = descriptor.getEntitySearchTerm("term");

        assertEquals("[term]", searchTerm.getTerms().toString());
    }

    @Test
    public void should_add_multiple_term_as_a_list_of_search_term() throws Exception {
        SearchEntityDescriptor descriptor = createSearchEntityDescriptor(Arrays.asList("field 1", "field 2"));

        SearchFields searchTerm = descriptor.getEntitySearchTerm("term1 term2");

        assertEquals("[term1, term2]", searchTerm.getTerms().toString());
    }

    @Test
    public void should_add_a_mapping_persistence_object_fields_to_the_search_fields() throws Exception {
        SearchEntityDescriptor descriptor = createSearchEntityDescriptor(Arrays.asList("field 1", "field 2"));

        SearchFields searchTerm = descriptor.getEntitySearchTerm("term");

        assertEquals(
                "{class org.bonitasoft.engine.search.descriptor.DummyPersistentObject=[field 1, field 2]}",
                searchTerm.getFields().toString());
    }

    private SearchEntityDescriptor createSearchEntityDescriptor(final List<String> fields) {
        return new SearchEntityDescriptor() {
                @Override
                protected Map<String, FieldDescriptor> getEntityKeys() {
                    return null;
                }

                @Override
                protected Map<Class<? extends PersistentObject>, Set<String>> getAllFields() {
                    return Collections.<Class<? extends PersistentObject>, Set<String>> singletonMap(
                            DummyPersistentObject.class,
                            new HashSet<String>(fields));
                }
            };
    }
}