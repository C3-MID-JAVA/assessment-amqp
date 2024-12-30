package ec.com.sofka.account;

import ec.com.sofka.Account;
import ec.com.sofka.appservice.account.CreateAccountUseCase;
import ec.com.sofka.gateway.AccountRepository;
import ec.com.sofka.gateway.BusMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CreateAccountUseCaseTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private BusMessage busMessage;

    @InjectMocks
    private CreateAccountUseCase createAccountUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createAccount_success() {
        Account account = new Account("1", new BigDecimal("1000.0"), "123456", "John Doe");
        when(accountRepository.saveAccount(any(Account.class))).thenReturn(Mono.just(account));

        Mono<Account> result = createAccountUseCase.apply(account);

        StepVerifier.create(result)
                .expectNext(account)
                .verifyComplete();
    }

    @Test
    public void createAccount_failure() {
        Account account = new Account("1", new BigDecimal("1000.0"), "123456", "John Doe");
        when(accountRepository.saveAccount(any(Account.class))).thenReturn(Mono.empty());

        Mono<Account> result = createAccountUseCase.apply(account);

        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();
    }
}