package ec.com.sofka.mapper;

import ec.com.sofka.log.TransactionLog;
import ec.com.sofka.data.LogDocument;
import org.springframework.beans.BeanUtils;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class DocumentToModelMapper {
    public static final Function<Mono<LogDocument>, Mono<TransactionLog>> toLog = log ->
            log.map(logDocument -> {
                TransactionLog transactionLogModel = new TransactionLog();
                BeanUtils.copyProperties(logDocument, transactionLogModel);
                return transactionLogModel;
            });
}