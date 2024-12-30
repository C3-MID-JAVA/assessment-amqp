package ec.com.sofka.adapters;

import ec.com.sofka.MongoAdapterAccount;
import ec.com.sofka.Account;
import ec.com.sofka.config.IAccountMongoRepository;
import ec.com.sofka.data.AccountEntity;
import ec.com.sofka.mappers.AccountMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class MongoAdapterAccountTest {

    @Mock
    private IAccountMongoRepository repository;

    @InjectMocks
    private MongoAdapterAccount mongoAdapterAccount;

    private Account account1;
    private Account account2;
    private AccountEntity accountEntity1;
    private AccountEntity accountEntity2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        account1 = new Account("1", new BigDecimal("1000.0"), "owner1", "123456");
        account2 = new Account("2", new BigDecimal("2000.0"), "owner2", "654321");
        accountEntity1 = AccountMapper.toDocument(account1);
        accountEntity2 = AccountMapper.toDocument(account2);
    }

    @Test
    public void saveAccount_success() {
        when(repository.save(any(AccountEntity.class))).thenReturn(Mono.just(accountEntity1));

        Mono<Account> result = mongoAdapterAccount.saveAccount(account1);

        StepVerifier.create(result)
                .assertNext(savedAccount -> assertThat(savedAccount).isEqualToComparingFieldByField(account1))
                .verifyComplete();
    }

    @Test
    public void findAllAccounts_success() {
        when(repository.findAll()).thenReturn(Flux.just(accountEntity1, accountEntity2));

        Flux<Account> result = mongoAdapterAccount.findAllAccounts();

        StepVerifier.create(result)
                .assertNext(foundAccount -> assertThat(foundAccount).isEqualToComparingFieldByField(account1))
                .assertNext(foundAccount -> assertThat(foundAccount).isEqualToComparingFieldByField(account2))
                .verifyComplete();
    }

    @Test
    public void findByAcccountId_success() {
        when(repository.findById(anyString())).thenReturn(Mono.just(accountEntity1));

        Mono<Account> result = mongoAdapterAccount.findByAcccountId("1");

        StepVerifier.create(result)
                .assertNext(foundAccount -> assertThat(foundAccount).isEqualToComparingFieldByField(account1))
                .verifyComplete();
    }
}