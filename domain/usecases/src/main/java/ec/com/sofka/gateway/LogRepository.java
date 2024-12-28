package ec.com.sofka.gateway;

import ec.com.sofka.Log;
import reactor.core.publisher.Mono;

public interface LogRepository {
    Mono<Void> create(Log log);
}
