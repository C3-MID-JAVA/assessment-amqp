package ec.com.sofka.adapter;

import ec.com.sofka.Log;
import ec.com.sofka.config.LogMongoRepository;
import ec.com.sofka.data.LogEntity;
import ec.com.sofka.gateway.LogRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class LogMongoAdapter implements LogRepository {

    private final LogMongoRepository repository;

    public LogMongoAdapter(LogMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Void> create(Log log) {
        LogEntity logEntity = new LogEntity(log.getMessage(), log.getEntity(), log.getTimestamp());
        return repository.save(logEntity).then();
    }
}
