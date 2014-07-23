package org.bonitasoft.engine.execution.work;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.bonitasoft.engine.core.process.definition.ProcessDefinitionService;
import org.bonitasoft.engine.core.process.definition.model.SFlowNodeType;
import org.bonitasoft.engine.core.process.definition.model.SProcessDefinition;
import org.bonitasoft.engine.core.process.instance.api.GatewayInstanceService;
import org.bonitasoft.engine.core.process.instance.model.SGatewayInstance;
import org.bonitasoft.engine.log.technical.TechnicalLoggerService;
import org.bonitasoft.engine.service.TenantServiceAccessor;
import org.junit.Test;

public class RestartFlowNodesHandlerTest {

    TechnicalLoggerService logger = mock(TechnicalLoggerService.class);

    TenantServiceAccessor tenantAccessor = mock(TenantServiceAccessor.class);

    @Test
    public void shouldExecuteFlownodeIfNotGateway() throws Exception {
        SGatewayInstance gatewayInstance = mock(SGatewayInstance.class);
        when(gatewayInstance.getType()).thenReturn(SFlowNodeType.AUTOMATIC_TASK);
        RestartFlowNodesHandler restartFlowNodesHandler = spy(new RestartFlowNodesHandler());

        boolean shouldExecuteFlownode = restartFlowNodesHandler.shouldExecuteFlownode(tenantAccessor, logger, gatewayInstance);

        assertThat(shouldExecuteFlownode).isTrue();
        verifyNoMoreInteractions(tenantAccessor);
    }

    @Test
    public void shouldExecuteFlownodeForGatewayWithMatchingMergeCondition() throws Exception {
        SGatewayInstance gatewayInstance = mock(SGatewayInstance.class);
        when(gatewayInstance.getType()).thenReturn(SFlowNodeType.GATEWAY);
        GatewayInstanceService gatewayInstanceService = mock(GatewayInstanceService.class);
        when(tenantAccessor.getGatewayInstanceService()).thenReturn(gatewayInstanceService);
        ProcessDefinitionService processDefinitionService = mock(ProcessDefinitionService.class);
        when(tenantAccessor.getProcessDefinitionService()).thenReturn(processDefinitionService);
        when(gatewayInstanceService.checkMergingCondition(any(SProcessDefinition.class), eq(gatewayInstance))).thenReturn(true);
        RestartFlowNodesHandler restartFlowNodesHandler = spy(new RestartFlowNodesHandler());

        boolean shouldExecuteFlownode = restartFlowNodesHandler.shouldExecuteFlownode(tenantAccessor, logger, gatewayInstance);

        assertThat(shouldExecuteFlownode).isTrue();
    }

    @Test
    public void shouldNotExecuteFlownodeForGatewayWithNonMatchingMergeCondition() throws Exception {
        SGatewayInstance gatewayInstance = mock(SGatewayInstance.class);
        when(gatewayInstance.getType()).thenReturn(SFlowNodeType.GATEWAY);
        GatewayInstanceService gatewayInstanceService = mock(GatewayInstanceService.class);
        when(tenantAccessor.getGatewayInstanceService()).thenReturn(gatewayInstanceService);
        ProcessDefinitionService processDefinitionService = mock(ProcessDefinitionService.class);
        when(tenantAccessor.getProcessDefinitionService()).thenReturn(processDefinitionService);
        when(gatewayInstanceService.checkMergingCondition(any(SProcessDefinition.class), eq(gatewayInstance))).thenReturn(false);
        RestartFlowNodesHandler restartFlowNodesHandler = spy(new RestartFlowNodesHandler());

        boolean shouldExecuteFlownode = restartFlowNodesHandler.shouldExecuteFlownode(tenantAccessor, logger, gatewayInstance);

        assertThat(shouldExecuteFlownode).isFalse();
    }

}
