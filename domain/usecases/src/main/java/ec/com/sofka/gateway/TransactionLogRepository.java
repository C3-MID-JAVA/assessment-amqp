package ec.com.sofka.gateway;

import ec.com.sofka.TransactionLog;
import reactor.core.publisher.Mono;

public interface TransactionLogRepository {
    Mono<Void> saveTransactionLog(TransactionLog transactionLog);
}
