package co.com.sofkau.gateway;

import co.com.sofkau.transaction.Transaction;
import reactor.core.publisher.Mono;

public interface ITransactionRepository {
    Mono<Transaction> save(Transaction transaction);
}
