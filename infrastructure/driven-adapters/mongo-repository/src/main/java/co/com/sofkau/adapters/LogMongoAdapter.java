package co.com.sofkau.adapters;

import co.com.sofkau.Log;
import co.com.sofkau.config.ILogMongoRepository;
import co.com.sofkau.data.LogEntity;
import co.com.sofkau.gateway.ILogRepository;
import co.com.sofkau.mapper.TransactionMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class LogMongoAdapter implements ILogRepository {
    private final ILogMongoRepository logMongoRepository;

    public LogMongoAdapter(ILogMongoRepository logMongoRepository) {
        this.logMongoRepository = logMongoRepository;
    }

    @Override
    public Mono<Void> save(Log log) {
        LogEntity logEntity = new LogEntity(log.getId(), log.getMessage(), log.getGroup(),
                log.getLevel(), TransactionMapper.toTransactionEntity(log.getTransaction()),
                log.getTimestamp());

        return logMongoRepository.save(logEntity).then();
    }
}
