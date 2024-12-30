package ec.com.sofka.transaction;

import ec.com.sofka.Transaction;
import ec.com.sofka.appservice.transaction.GetAllTransactionsUseCase;
import ec.com.sofka.gateway.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

public class GetAllTransactionsUseCaseTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private GetAllTransactionsUseCase getAllTransactionsUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllTransactions_success() {
        Transaction transaction1 = new Transaction("1", new BigDecimal("100.0"), "deposit", new BigDecimal("1.0"), "1");
        Transaction transaction2 = new Transaction("2", new BigDecimal("200.0"), "withdraw", new BigDecimal("2.0"), "2");
        when(transactionRepository.findAllTransactions()).thenReturn(Flux.just(transaction1, transaction2));

        Flux<Transaction> result = getAllTransactionsUseCase.apply();

        StepVerifier.create(result)
                .expectNext(transaction1)
                .expectNext(transaction2)
                .verifyComplete();
    }

    @Test
    public void getAllTransactions_empty() {
        when(transactionRepository.findAllTransactions()).thenReturn(Flux.empty());

        Flux<Transaction> result = getAllTransactionsUseCase.apply();

        StepVerifier.create(result)
                .verifyComplete();
    }
}