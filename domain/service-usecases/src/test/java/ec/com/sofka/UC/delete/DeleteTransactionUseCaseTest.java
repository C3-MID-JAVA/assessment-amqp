package ec.com.sofka.UC.delete;

import ec.com.sofka.account.Account;
import ec.com.sofka.branch.Branch;
import ec.com.sofka.transaction.Transaction;
import ec.com.sofka.gateway.BusMessage;
import ec.com.sofka.gateway.repository.TransactionRepository;
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

import static org.mockito.Mockito.*;

@SpringBootTest(classes = DeleteTransactionUseCaseTest.class)
public class DeleteTransactionUseCaseTest {
    @InjectMocks
    private DeleteTransactionUseCase deleteTransactionUseCase;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private BusMessage busMessage;

    @Mock
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        when(transaction.getId()).thenReturn(1);
        when(transaction.getAmount()).thenReturn(new BigDecimal("100.00"));
        when(transaction.getDate()).thenReturn(LocalDateTime.now());
        when(transaction.getDescription()).thenReturn("test");
        when(transaction.getType()).thenReturn("test");
        when(transaction.getDestinationAccount()).thenReturn(new Account());
        when(transaction.getSourceAccount()).thenReturn(new Account());
        when(transaction.getBranch()).thenReturn(new Branch());
    }

    @Test
    void testApply_shouldReturnCustomer() {
        when(transactionRepository.deleteById(Mockito.any(Mono.class)))
                .thenReturn(Mono.empty());
        Mono<Void> result = deleteTransactionUseCase.apply(transaction.getId());
        StepVerifier.create(result)
                .verifyComplete();
        Mockito.verify(transactionRepository, Mockito.times(1)).deleteById(Mockito.any(Mono.class));
    }

    @Test
    void testApply_ShouldReturnError_WhenRepositoryFails() {
        when(transactionRepository.deleteById(Mockito.any(Mono.class)))
                .thenReturn(Mono.error(new RuntimeException("Error saving branch")));
        Mono<Void> result = deleteTransactionUseCase.apply(transaction.getId());
        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();
        verify(transactionRepository, times(1)).deleteById(Mockito.any(Mono.class));
    }
}
