package ec.com.sofka.config;

import ec.com.sofka.TransactionLog;
import ec.com.sofka.entities.TransactionLogEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TransactionLogMongoRepository extends ReactiveMongoRepository<TransactionLogEntity, String> {
}
