package ec.com.sofka.config;

import ec.com.sofka.data.TransactionsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ITransactionsRepository extends ReactiveMongoRepository<TransactionsEntity, String> {
}
