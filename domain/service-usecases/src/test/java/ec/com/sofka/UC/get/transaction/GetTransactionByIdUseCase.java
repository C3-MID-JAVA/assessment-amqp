package ec.com.sofka.UC.get.transaction;

import ec.com.sofka.transaction.Transaction;
import ec.com.sofka.gateway.repository.TransactionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GetTransactionByIdUseCase {
    private final TransactionRepository repository;

    public GetTransactionByIdUseCase(TransactionRepository repository) {
        this.repository = repository;
    }

    public Mono<Transaction> apply(int id) {
        return null;
    }
}
