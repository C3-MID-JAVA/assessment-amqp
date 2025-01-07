package ec.com.sofka.UC.delete;

import ec.com.sofka.account.Account;
import ec.com.sofka.branch.Branch;
import ec.com.sofka.card.Card;
import ec.com.sofka.transaction.Transaction;
import ec.com.sofka.gateway.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = DeleteAccountUseCaseTest.class)
public class DeleteAccountUseCaseTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private DeleteAccountUseCase deleteAccountUseCase;

    private Account account;

    @BeforeEach
    public void setUp() {
        Card card = new Card("1234-5678-9876-5432",
                "Debit", "123",
                LocalDateTime.of(2025, 12, 31, 23, 59, 59),
                1);
        Transaction transaction = new Transaction(BigDecimal.valueOf(200.50),
                new Branch(),
                LocalDateTime.now(),
                "Debit",
                null,
                1, null, "Payment for services");
        account = new Account(
                "AC12345",
                "Savings",
                BigDecimal.valueOf(1000.00),
                Collections.singletonList(card),
                1,
                Collections.singletonList(transaction)
        );
    }

    @Test
    public void testApply_shouldReturnAccount() {
        when(accountRepository.deleteById(Mockito.any(Mono.class)))
                .thenReturn(Mono.empty());
        Mono<Void> result = deleteAccountUseCase.apply(account.getId());
        StepVerifier.create(result)
                .verifyComplete();
        verify(accountRepository, Mockito.times(1)).deleteById(Mockito.any(Mono.class));
    }

    @Test
    void testApply_ShouldReturnError_WhenRepositoryFails() {
        when(accountRepository.deleteById(Mockito.any(Mono.class)))
                .thenReturn(Mono.error(new RuntimeException("Error saving branch")));
        Mono<Void> result = deleteAccountUseCase.apply(account.getId());
        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();
        verify(accountRepository, times(1)).deleteById(Mockito.any(Mono.class));
    }
}
