package ec.com.sofka.config;

import ec.com.sofka.data.RegisterEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRegisterRepository extends ReactiveMongoRepository<RegisterEntity, String> {
}
