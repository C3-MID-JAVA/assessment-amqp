package ec.com.sofka.adapter;

import ec.com.sofka.TransactionLog;
import ec.com.sofka.config.TransactionLogMongoRepository;
import ec.com.sofka.entities.TransactionLogEntity;
import ec.com.sofka.gateway.TransactionLogRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class TransactionLogMongoAdapter implements TransactionLogRepository {
    private final TransactionLogMongoRepository transactionLogMongoRepository;

    public TransactionLogMongoAdapter(TransactionLogMongoRepository transactionLogMongoRepository) {
        this.transactionLogMongoRepository = transactionLogMongoRepository;
    }

    @Override
    public Mono<Void> saveTransactionLog(TransactionLog transactionLog) {
        TransactionLogEntity entity = new TransactionLogEntity(
                null,
                transactionLog.getTransactionId(),
                transactionLog.getType(),
                transactionLog.getMessage(),
                transactionLog.getTimestamp()
        );
        return transactionLogMongoRepository.save(entity).then();
    }
}
