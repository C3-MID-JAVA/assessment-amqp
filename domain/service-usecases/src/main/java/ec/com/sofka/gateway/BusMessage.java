package ec.com.sofka.gateway;

import ec.com.sofka.log.TransactionLog;
import reactor.core.publisher.Mono;

public interface BusMessage {
    Mono<Void> sendMsg(Mono<TransactionLog> message);
}