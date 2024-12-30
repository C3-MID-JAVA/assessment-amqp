package ec.com.sofka.config;

import ec.com.sofka.entities.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserMongoRepository extends ReactiveMongoRepository<UserEntity, String> {
    Mono<UserEntity> findByDocumentId(String documentId);
}
