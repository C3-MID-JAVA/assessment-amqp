package com.bank;

import com.bank.data.AccountEntity;
import com.bank.gateway.IAccountRepository;
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
public class AccountAdapterTests {
    private IAccountRepository accountRepository;


    @Autowired
    public AccountAdapterTests(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Test
    public void SaveAndFindByIdOK() {
        Account account = new Account("1236", "test holder", BigDecimal.valueOf(8503));

        Mono<Account> save = accountRepository.createAccount(account);

        StepVerifier.create(save)
                .assertNext(savedAccount -> {
                    assertThat(savedAccount.getId()).isNotNull();
                    assertThat(savedAccount.getId()).isEqualTo("1236");
                })
                .verifyComplete();

        Mono<Account> find = accountRepository.findAccountById(account.getId());

        StepVerifier.create(find)
                .assertNext(foundAccount -> {
                    assertThat(foundAccount.getId()).isEqualTo(account.getId());
                    assertThat(foundAccount.getBalance()).isEqualByComparingTo(new BigDecimal(8503));
                })
                .verifyComplete();
    }
}
