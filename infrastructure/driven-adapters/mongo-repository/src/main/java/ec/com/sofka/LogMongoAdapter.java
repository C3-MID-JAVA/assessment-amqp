package ec.com.sofka;

import ec.com.sofka.config.ILogEntityMongoRepository;
import ec.com.sofka.data.LogEntity;
import ec.com.sofka.gateway.LogRepository;
import ec.com.sofka.mapper.LogMapper;
import org.springframework.stereotype.Repository;

import java.io.Console;

@Repository
public class LogMongoAdapter implements LogRepository {

    private final ILogEntityMongoRepository logEntityMongoRepository;

    public LogMongoAdapter(ILogEntityMongoRepository logEntityMongoRepository) {
        this.logEntityMongoRepository = logEntityMongoRepository;
    }


    @Override
    public Log saveLog(Log log) {

        LogEntity logDocument = LogMapper.toEntity(log);
        LogEntity logSaved = logEntityMongoRepository.save(logDocument);

        return LogMapper.toDomain(logSaved);
    }

}
