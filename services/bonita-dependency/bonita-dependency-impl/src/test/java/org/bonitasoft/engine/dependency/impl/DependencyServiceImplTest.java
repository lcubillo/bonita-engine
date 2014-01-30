package org.bonitasoft.engine.dependency.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.engine.classloader.ClassLoaderService;
import org.bonitasoft.engine.dependency.ArtifactAccessor;
import org.bonitasoft.engine.dependency.SDependencyException;
import org.bonitasoft.engine.dependency.SDependencyMappingNotFoundException;
import org.bonitasoft.engine.dependency.SDependencyNotFoundException;
import org.bonitasoft.engine.dependency.model.SDependency;
import org.bonitasoft.engine.dependency.model.SDependencyMapping;
import org.bonitasoft.engine.log.technical.TechnicalLoggerService;
import org.bonitasoft.engine.persistence.QueryOptions;
import org.bonitasoft.engine.persistence.ReadPersistenceService;
import org.bonitasoft.engine.persistence.SBonitaReadException;
import org.bonitasoft.engine.persistence.SelectByIdDescriptor;
import org.bonitasoft.engine.persistence.SelectListDescriptor;
import org.bonitasoft.engine.recorder.Recorder;
import org.bonitasoft.engine.services.QueriableLoggerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Celine Souchet
 */
public class DependencyServiceImplTest {

    private ReadPersistenceService persistenceService;

    private Recorder recorder;

    private TechnicalLoggerService logger;

    private QueriableLoggerService queriableLoggerService;

    private ClassLoaderService classLoaderService;

    private DependencyServiceImpl dependencyServiceImpl;

    @Before
    public void setUp() {
        persistenceService = mock(ReadPersistenceService.class);
        recorder = mock(Recorder.class);
        queriableLoggerService = mock(QueriableLoggerService.class);
        logger = mock(TechnicalLoggerService.class);
        classLoaderService = mock(ClassLoaderService.class);
        dependencyServiceImpl = new DependencyServiceImpl(persistenceService, recorder, logger, queriableLoggerService, classLoaderService);
    }

    @Test
    public final void getDependencyById() throws SBonitaReadException, SDependencyNotFoundException {
        final SDependency sDependency = mock(SDependency.class);
        when(persistenceService.selectById(any(SelectByIdDescriptor.class))).thenReturn(sDependency);

        Assert.assertEquals(sDependency, dependencyServiceImpl.getDependency(456L));
    }

    @Test(expected = SDependencyNotFoundException.class)
    public final void getDependencyByIdNotExists() throws SBonitaReadException, SDependencyNotFoundException {
        when(persistenceService.selectById(any(SelectByIdDescriptor.class))).thenReturn(null);

        dependencyServiceImpl.getDependency(456L);
    }

    @Test(expected = SDependencyNotFoundException.class)
    public final void getDependencyByIdThrowException() throws SBonitaReadException, SDependencyNotFoundException {
        when(persistenceService.selectById(any(SelectByIdDescriptor.class))).thenThrow(new SBonitaReadException(""));

        dependencyServiceImpl.getDependency(456L);
    }

    @Test
    public final void getDependenciesByIds() throws SBonitaReadException, SDependencyException {
        final List<SDependency> sDependencies = new ArrayList<SDependency>();
        when(persistenceService.selectList(any(SelectListDescriptor.class))).thenReturn(sDependencies);

        Assert.assertEquals(sDependencies, dependencyServiceImpl.getDependencies(Collections.singletonList(456L)));
    }

    @Test(expected = SDependencyException.class)
    public final void getDependenciesByIdsThrowException() throws SBonitaReadException, SDependencyException {
        when(persistenceService.selectList(any(SelectListDescriptor.class))).thenThrow(new SBonitaReadException(""));

        dependencyServiceImpl.getDependencies(Collections.singletonList(456L));
    }

    @Test
    public final void getDependenciesWithOptions() throws SBonitaReadException, SDependencyException {
        final List<SDependency> sDependencies = new ArrayList<SDependency>();
        when(persistenceService.selectList(any(SelectListDescriptor.class))).thenReturn(sDependencies);

        final QueryOptions options = new QueryOptions(0, 10);
        Assert.assertEquals(sDependencies, dependencyServiceImpl.getDependencies(options));
    }

    @Test(expected = SDependencyException.class)
    public final void getDependenciesWithOptionsThrowException() throws SBonitaReadException, SDependencyException {
        when(persistenceService.selectList(any(SelectListDescriptor.class))).thenThrow(new SBonitaReadException(""));

        final QueryOptions options = new QueryOptions(0, 10);
        dependencyServiceImpl.getDependencies(options);
    }

    @Test
    public final void getDependencyIds() throws SBonitaReadException, SDependencyException {
        final List<SDependency> sDependencies = new ArrayList<SDependency>();
        when(persistenceService.selectList(any(SelectListDescriptor.class))).thenReturn(sDependencies);

        final QueryOptions options = new QueryOptions(0, 10);
        Assert.assertEquals(sDependencies, dependencyServiceImpl.getDependencyIds(54156L, "artifactType", options));
    }

    @Test(expected = SDependencyException.class)
    public final void getDependencyIdsThrowException() throws SBonitaReadException, SDependencyException {
        when(persistenceService.selectList(any(SelectListDescriptor.class))).thenThrow(new SBonitaReadException(""));

        final QueryOptions options = new QueryOptions(0, 10);
        dependencyServiceImpl.getDependencyIds(54156L, "artifactType", options);
    }

    @Test
    public final void getDependencyMappingById() throws SBonitaReadException, SDependencyMappingNotFoundException {
        final SDependencyMapping sDependencyMapping = mock(SDependencyMapping.class);
        when(persistenceService.selectById(any(SelectByIdDescriptor.class))).thenReturn(sDependencyMapping);

        Assert.assertEquals(sDependencyMapping, dependencyServiceImpl.getDependencyMapping(456L));
    }

    @Test(expected = SDependencyMappingNotFoundException.class)
    public final void getDependencyMappingByIdNotExists() throws SBonitaReadException, SDependencyMappingNotFoundException {
        when(persistenceService.selectById(any(SelectByIdDescriptor.class))).thenReturn(null);

        dependencyServiceImpl.getDependencyMapping(456L);
    }

    @Test(expected = SDependencyMappingNotFoundException.class)
    public final void getDependencyMappingByIdThrowException() throws SBonitaReadException, SDependencyMappingNotFoundException {
        when(persistenceService.selectById(any(SelectByIdDescriptor.class))).thenThrow(new SBonitaReadException(""));

        dependencyServiceImpl.getDependencyMapping(456L);
    }

    @Test
    public final void getDependencyMappingsWithArtifactIdAndTypeAndQueryOptions() throws SBonitaReadException, SDependencyException {
        final List<SDependencyMapping> sDependencyMappings = new ArrayList<SDependencyMapping>();
        when(persistenceService.selectList(any(SelectListDescriptor.class))).thenReturn(sDependencyMappings);

        final QueryOptions options = new QueryOptions(0, 10);
        Assert.assertEquals(sDependencyMappings, dependencyServiceImpl.getDependencyMappings(54156L, "artifactType", options));
    }

    @Test(expected = SDependencyException.class)
    public final void getDependencyMappingsWithArtifactIdAndTypeAndQueryOptionsThrowException() throws SBonitaReadException, SDependencyException {
        when(persistenceService.selectList(any(SelectListDescriptor.class))).thenThrow(new SBonitaReadException(""));

        final QueryOptions options = new QueryOptions(0, 10);
        dependencyServiceImpl.getDependencyMappings(54156L, "artifactType", options);
    }

    @Test
    public final void getDependencyMappingsWithDependencyIdAndQueryOptions() throws SBonitaReadException, SDependencyException {
        final List<SDependencyMapping> sDependencyMappings = new ArrayList<SDependencyMapping>();
        when(persistenceService.selectList(any(SelectListDescriptor.class))).thenReturn(sDependencyMappings);

        final QueryOptions options = new QueryOptions(0, 10);
        Assert.assertEquals(sDependencyMappings, dependencyServiceImpl.getDependencyMappings(54156L, options));
    }

    @Test(expected = SDependencyException.class)
    public final void getDependencyMappingsWithDependencyIdAndQueryOptionsThrowException() throws SBonitaReadException, SDependencyException {
        when(persistenceService.selectList(any(SelectListDescriptor.class))).thenThrow(new SBonitaReadException(""));

        final QueryOptions options = new QueryOptions(0, 10);
        dependencyServiceImpl.getDependencyMappings(54156L, options);
    }

    @Test
    public final void getDependencyMappingsWithOptions() throws SBonitaReadException, SDependencyException {
        final List<SDependencyMapping> sDependencyMappings = new ArrayList<SDependencyMapping>();
        when(persistenceService.selectList(any(SelectListDescriptor.class))).thenReturn(sDependencyMappings);

        final QueryOptions options = new QueryOptions(0, 10);
        Assert.assertEquals(sDependencyMappings, dependencyServiceImpl.getDependencyMappings(options));
    }

    @Test(expected = SDependencyException.class)
    public final void getDependencyMappingsWithOptionsThrowException() throws SBonitaReadException, SDependencyException {
        when(persistenceService.selectList(any(SelectListDescriptor.class))).thenThrow(new SBonitaReadException(""));

        final QueryOptions options = new QueryOptions(0, 10);
        dependencyServiceImpl.getDependencyMappings(options);
    }

    @Test
    public final void getDisconnectedDependencyMappingsNothing() throws SBonitaReadException, SDependencyException {
        final ArtifactAccessor artifactAccessor = mock(ArtifactAccessor.class);
        when(artifactAccessor.artifactExists(any(String.class), any(Long.class))).thenReturn(true);
        final List<SDependencyMapping> sDependencyMappings = new ArrayList<SDependencyMapping>();
        sDependencyMappings.add(mock(SDependencyMapping.class));
        when(persistenceService.selectList(any(SelectListDescriptor.class))).thenReturn(sDependencyMappings);

        final QueryOptions options = new QueryOptions(0, 10);
        Assert.assertEquals(Collections.emptyList(), dependencyServiceImpl.getDisconnectedDependencyMappings(artifactAccessor, options));
    }

    @Test
    public final void getDisconnectedDependencyMappings() throws SBonitaReadException, SDependencyException {
        final ArtifactAccessor artifactAccessor = mock(ArtifactAccessor.class);
        when(artifactAccessor.artifactExists(any(String.class), any(Long.class))).thenReturn(false);
        final List<SDependencyMapping> sDependencyMappings = new ArrayList<SDependencyMapping>();
        sDependencyMappings.add(mock(SDependencyMapping.class));
        when(persistenceService.selectList(any(SelectListDescriptor.class))).thenReturn(sDependencyMappings);

        final QueryOptions options = new QueryOptions(0, 10);
        Assert.assertEquals(sDependencyMappings, dependencyServiceImpl.getDisconnectedDependencyMappings(artifactAccessor, options));
    }

    @Test(expected = SDependencyException.class)
    public final void getDisconnectedDependencyMappingsThrowException() throws SBonitaReadException, SDependencyException {
        final ArtifactAccessor artifactAccessor = mock(ArtifactAccessor.class);
        when(persistenceService.selectList(any(SelectListDescriptor.class))).thenThrow(new SBonitaReadException(""));

        final QueryOptions options = new QueryOptions(0, 10);
        dependencyServiceImpl.getDisconnectedDependencyMappings(artifactAccessor, options);
    }

}
