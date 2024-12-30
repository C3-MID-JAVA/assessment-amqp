package ec.com.sofka.transaction;

import ec.com.sofka.Transaction;
import ec.com.sofka.appservice.transaction.GetTransactionByIdUseCase;
import ec.com.sofka.gateway.BusMessage;
import ec.com.sofka.gateway.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class GetTransactionByIdUseCaseTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private BusMessage busMessage;

    @InjectMocks
    private GetTransactionByIdUseCase getTransactionByIdUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getTransactionById_success() {
        Transaction transaction = new Transaction("1", new BigDecimal("100.0"), "deposit", new BigDecimal("1.0"), "1");
        when(transactionRepository.findTransactionById(anyString())).thenReturn(Mono.just(transaction));

        Mono<Transaction> result = getTransactionByIdUseCase.apply("1");

        StepVerifier.create(result)
                .expectNext(transaction)
                .verifyComplete();
    }

    @Test
    public void getTransactionById_notFound() {
        when(transactionRepository.findTransactionById(anyString())).thenReturn(Mono.empty());

        Mono<Transaction> result = getTransactionByIdUseCase.apply("1");

        StepVerifier.create(result)
                .verifyComplete();
    }
}