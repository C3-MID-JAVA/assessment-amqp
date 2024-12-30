package ec.com.sofka.gateway;

import ec.com.sofka.Log;
import reactor.core.publisher.Mono;

public interface LogRepository {
    Mono<Void> saveLog (Log log);
}
