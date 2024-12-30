package ec.com.sofka.applogs;

import ec.com.sofka.TransactionLog;
import ec.com.sofka.gateway.TransactionLogRepository;
import reactor.core.publisher.Mono;

public class SaveTransactionLogUseCase {
    private final TransactionLogRepository repository;

    public SaveTransactionLogUseCase(TransactionLogRepository repository) {
        this.repository = repository;
    }

    public Mono<Void> saveLog(TransactionLog log) {
        return repository.saveTransactionLog(log);
    }
}
