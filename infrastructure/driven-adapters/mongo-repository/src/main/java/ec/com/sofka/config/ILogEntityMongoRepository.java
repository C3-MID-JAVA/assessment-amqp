package ec.com.sofka.config;

import ec.com.sofka.data.AccountEntity;
import ec.com.sofka.data.LogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


public interface ILogEntityMongoRepository extends MongoRepository<LogEntity, String> {

}
