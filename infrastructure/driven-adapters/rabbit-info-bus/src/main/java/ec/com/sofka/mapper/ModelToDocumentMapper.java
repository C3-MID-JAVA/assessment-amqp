package ec.com.sofka.mapper;

import ec.com.sofka.log.TransactionLog;
import ec.com.sofka.data.LogDocument;
import org.springframework.beans.BeanUtils;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class ModelToDocumentMapper {
    public static final Function<Mono<TransactionLog>, Mono<LogDocument>> toLog = log ->
            log.map(logModel -> {
                LogDocument logDocument = new LogDocument();
                BeanUtils.copyProperties(logModel, logDocument);
                return logDocument;
            });
}
