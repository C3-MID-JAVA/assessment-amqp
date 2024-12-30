package ec.com.sofka.adapters;

import ec.com.sofka.MongoAdapterTransaction;
import ec.com.sofka.Transaction;
import ec.com.sofka.config.ITransactionMongoRepository;
import ec.com.sofka.data.TransactionEntity;
import ec.com.sofka.mappers.TransactionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class MongoAdapterTransactionTest {

    @Mock
    private ITransactionMongoRepository repository;

    @InjectMocks
    private MongoAdapterTransaction mongoAdapterTransaction;

    private Transaction transaction1;
    private Transaction transaction2;
    private TransactionEntity transactionEntity1;
    private TransactionEntity transactionEntity2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        transaction1 = new Transaction("1", new BigDecimal("100.0"), "deposit", new BigDecimal("1.0"), "1");
        transaction2 = new Transaction("2", new BigDecimal("200.0"), "withdraw", new BigDecimal("2.0"), "2");
        transactionEntity1 = TransactionMapper.toDocument(transaction1);
        transactionEntity2 = TransactionMapper.toDocument(transaction2);
    }

    @Test
    public void saveTransaction_success() {
        when(repository.save(any(TransactionEntity.class))).thenReturn(Mono.just(transactionEntity1));

        Mono<Transaction> result = mongoAdapterTransaction.saveTransaction(transaction1);

        StepVerifier.create(result)
                .assertNext(savedTransaction -> assertThat(savedTransaction).isEqualToComparingFieldByField(transaction1))
                .verifyComplete();
    }

    @Test
    public void findAllTransactions_success() {
        when(repository.findAll()).thenReturn(Flux.just(transactionEntity1, transactionEntity2));

        Flux<Transaction> result = mongoAdapterTransaction.findAllTransactions();

        StepVerifier.create(result)
                .assertNext(foundTransaction -> assertThat(foundTransaction).isEqualToComparingFieldByField(transaction1))
                .assertNext(foundTransaction -> assertThat(foundTransaction).isEqualToComparingFieldByField(transaction2))
                .verifyComplete();
    }

    @Test
    public void findTransactionById_success() {
        when(repository.findById(anyString())).thenReturn(Mono.just(transactionEntity1));

        Mono<Transaction> result = mongoAdapterTransaction.findTransactionById("1");

        StepVerifier.create(result)
                .assertNext(foundTransaction -> assertThat(foundTransaction).isEqualToComparingFieldByField(transaction1))
                .verifyComplete();
    }
}