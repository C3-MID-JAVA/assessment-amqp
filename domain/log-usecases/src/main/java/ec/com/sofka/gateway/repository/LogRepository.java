package ec.com.sofka.gateway.repository;

import ec.com.sofka.log.TransactionLog;
import reactor.core.publisher.Mono;

public interface LogRepository {
    Mono<Void> createLog(Mono<TransactionLog> log);
}