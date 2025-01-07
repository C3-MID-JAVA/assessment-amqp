package ec.com.sofka.UC.update;

import ec.com.sofka.transaction.Transaction;
import ec.com.sofka.gateway.repository.TransactionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UpdateTransactionUseCase {
    private final TransactionRepository repository;

    public UpdateTransactionUseCase(TransactionRepository repository) {
        this.repository = repository;
    }

    public Mono<Transaction> apply(Mono<Transaction> transaction) {
        return transaction;
    }
}
