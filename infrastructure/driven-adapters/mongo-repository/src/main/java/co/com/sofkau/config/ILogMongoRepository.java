package co.com.sofkau.config;

import co.com.sofkau.data.LogEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ILogMongoRepository extends ReactiveMongoRepository<LogEntity, String> {
}
