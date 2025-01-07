package ec.com.sofka.UC.get.transaction;

import ec.com.sofka.transaction.Transaction;
import ec.com.sofka.gateway.repository.TransactionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class GetTransactionByBranchUseCase {

    private final TransactionRepository repository;

    public GetTransactionByBranchUseCase(TransactionRepository repository) {
        this.repository = repository;
    }

    public Flux<Transaction> apply(int id) {
        return null;
    }
}
