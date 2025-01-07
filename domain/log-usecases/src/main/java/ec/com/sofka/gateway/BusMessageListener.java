package ec.com.sofka.gateway;

import ec.com.sofka.log.TransactionLog;
import reactor.core.publisher.Mono;

//18. Port for listening messages
public interface BusMessageListener {
    void receiveMsg(Mono<TransactionLog> log);
}