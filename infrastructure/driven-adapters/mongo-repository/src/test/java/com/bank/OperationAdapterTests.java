package com.bank;

import com.bank.gateway.IAccountRepository;
import com.bank.gateway.IOperationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@ContextConfiguration(classes = TestConfiguration.class)
@DataMongoTest
public class OperationAdapterTests {
    private IOperationRepository operationRepository;


    @Autowired
    public OperationAdapterTests(IOperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Test
    public void SaveAndFindByIdOK() {
        Operation operation = new Operation("1231235", BigDecimal.valueOf(500), OperationTypesEnum.ACCOUNT_DEPOSIT, new Account("27afdfd5-"));

        Mono<Operation> save = operationRepository.createOperation(operation);

        StepVerifier.create(save)
                .assertNext(savedOperation -> {
                    assertThat(savedOperation.getId()).isNotNull();
                    assertThat(savedOperation.getId()).isEqualTo("1231235");
                })
                .verifyComplete();

        Mono<Operation> find = operationRepository.findOperationById(operation.getId());

        StepVerifier.create(find)
                .assertNext(foundOperation -> {
                    assertThat(foundOperation.getId()).isEqualTo(operation.getId());
                    assertThat(foundOperation.getValue()).isEqualByComparingTo(new BigDecimal(500));
                })
                .verifyComplete();
    }
}
