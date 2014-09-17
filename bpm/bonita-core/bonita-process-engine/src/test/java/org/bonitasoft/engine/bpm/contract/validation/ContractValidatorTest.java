package org.bonitasoft.engine.bpm.contract.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.engine.bpm.contract.ContractViolationException;
import org.bonitasoft.engine.core.process.definition.model.SContractDefinition;
import org.bonitasoft.engine.core.process.definition.model.SType;
import org.bonitasoft.engine.core.process.definition.model.impl.SContractDefinitionImpl;
import org.bonitasoft.engine.core.process.definition.model.impl.SRuleDefinitionImpl;
import org.bonitasoft.engine.core.process.definition.model.impl.SSimpleInputDefinitionImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ContractValidatorTest {

    private static final String BOOLEAN_INPUT = "booleanInput";

    private static final String NUMBER_INPUT = "numberInput";

    private static final String NICE_COMMENT = "no way!";

    private static final String COMMENT = "comment";

    private static final String IS_VALID = "isValid";

    private static final String DATE_INPUT = "dateInput";

    private static final String DECIMAL_INPUT = "decimalInput";

    @Mock
    private ContractStructureValidator structureValidator;

    @Mock
    private ContractRulesValidator rulesValidator;

    @InjectMocks
    private ContractValidator validator;

    private SContractDefinition buildContractWithInputsAndRules() {
        final SContractDefinitionImpl contract = buildEmptyContract();
        addInputsToContract(contract);
        addRulesToContractWithInputs(contract);
        return contract;
    }

    private void addRulesToContractWithInputs(final SContractDefinition contract) {
        final SRuleDefinitionImpl rule1 = new SRuleDefinitionImpl("Mandatory", "isValid != null", "isValid must be set");
        rule1.addInputName(IS_VALID);
        contract.getRules().add(rule1);
        final SRuleDefinitionImpl rule2 = new SRuleDefinitionImpl("Comment_Needed_If_Not_Valid", "isValid || !isValid && comment != null",
                "A comment is required when no validation");
        rule2.addInputName(IS_VALID);
        rule2.addInputName(COMMENT);
        contract.getRules().add(rule2);
    }

    private SContractDefinition addInputsToContract(final SContractDefinition contract) {
        final SSimpleInputDefinitionImpl input1 = new SSimpleInputDefinitionImpl(IS_VALID);
        input1.setType(SType.BOOLEAN);
        contract.getSimpleInputs().add(input1);
        final SSimpleInputDefinitionImpl input2 = new SSimpleInputDefinitionImpl(COMMENT);
        input2.setType(SType.TEXT);
        contract.getSimpleInputs().add(input2);
        return contract;
    }

    private SContractDefinitionImpl buildEmptyContract() {
        return new SContractDefinitionImpl();
    }

    @Test
    public void isValid_should_be_true_an_return_an_empty_list() throws Exception {
        //given
        final Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(IS_VALID, true);
        variables.put(COMMENT, null);
        final SContractDefinition contract = buildContractWithInputsAndRules();

        //when
        final boolean valid = validator.isValid(contract, variables);

        //then
        assertThat(valid).isTrue();
        assertThat(validator.getComments()).isEmpty();
    }

    @Test
    public void isValid_should_be_false_when_inputs_are_missing() throws Exception {
        //given
        final Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(COMMENT, NICE_COMMENT);

        final SContractDefinition contract = new SContractDefinitionImpl();
        SSimpleInputDefinitionImpl sInputDefinition = new SSimpleInputDefinitionImpl(IS_VALID);
        sInputDefinition.setType(SType.BOOLEAN);
        contract.getSimpleInputs().add(sInputDefinition);
        SSimpleInputDefinitionImpl sInputDefinition2 = new SSimpleInputDefinitionImpl(COMMENT);
        sInputDefinition2.setType(SType.TEXT);
        contract.getSimpleInputs().add(sInputDefinition2);

        doThrow(new ContractViolationException("bad structure", Arrays.asList("field toto is missing")))
                .when(structureValidator).validate(contract, variables);

        //when
        final boolean valid = validator.isValid(contract, variables);

        //then
        assertThat(valid).as("should refuse when inputs are unexpected").isFalse();
        assertThat(validator.getComments()).isNotEmpty().contains("field toto is missing");
    }

    @Test
    public void isValid_should_be_true_when_no_contract_and_no_inputs() throws Exception {
        //given
        final Map<String, Object> variables = new HashMap<String, Object>();
        final SContractDefinition contract = buildEmptyContract();

        //when
        final boolean valid = validator.isValid(contract, variables);

        //then
        assertThat(valid).as("should validate when contract is empty and no inputs are provided").isTrue();
    }

    @Test
    public void isValid_should_be_true_when_inputs_meets_contract() throws Exception {
        //given
        final Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(IS_VALID, false);
        variables.put(COMMENT, NICE_COMMENT);
        final SContractDefinition contract = buildContractWithInputsAndRules();

        //when
        final boolean valid = validator.isValid(contract, variables);

        //then
        assertThat(validator.getComments()).as("should have no comments").isEmpty();
        assertThat(valid).as("should validate contract").isTrue();
    }

    @Test
    public void isValid_should_be_true_when_inputs_meets_contract_without_rules() throws Exception {
        //given
        final Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(IS_VALID, false);
        variables.put(COMMENT, NICE_COMMENT);
        final SContractDefinition contract = addInputsToContract(buildEmptyContract());

        //when
        final boolean valid = validator.isValid(contract, variables);

        //then
        assertThat(valid).as("should validate contract without rules").isTrue();
    }

    @Test
    public void isValid_should_validate_input_type() throws Exception {
        //given
        final SContractDefinition contract = buildEmptyContract();
        final SSimpleInputDefinitionImpl sInputDefinition = new SSimpleInputDefinitionImpl(NUMBER_INPUT);
        sInputDefinition.setType(SType.INTEGER);
        contract.getSimpleInputs().add(sInputDefinition);

        final SSimpleInputDefinitionImpl sInputDefinition2 = new SSimpleInputDefinitionImpl(BOOLEAN_INPUT);
        sInputDefinition2.setType(SType.BOOLEAN);
        contract.getSimpleInputs().add(sInputDefinition2);

        final SSimpleInputDefinitionImpl sInputDefinition3 = new SSimpleInputDefinitionImpl(DATE_INPUT);
        sInputDefinition3.setType(SType.DATE);
        contract.getSimpleInputs().add(sInputDefinition3);

        final SSimpleInputDefinitionImpl sInputDefinition4 = new SSimpleInputDefinitionImpl(DECIMAL_INPUT);
        sInputDefinition4.setType(SType.DECIMAL);
        contract.getSimpleInputs().add(sInputDefinition4);

        final Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(NUMBER_INPUT, BigInteger.valueOf(123));
        variables.put(BOOLEAN_INPUT, false);
        variables.put(DATE_INPUT, new Date());
        variables.put(DECIMAL_INPUT, BigDecimal.valueOf(1.5f));

        //when
        final boolean valid = validator.isValid(contract, variables);

        //then
        assertThat(validator.getComments()).isEmpty();
        assertThat(valid).as("should validate contract without rules").isTrue();
    }

}
