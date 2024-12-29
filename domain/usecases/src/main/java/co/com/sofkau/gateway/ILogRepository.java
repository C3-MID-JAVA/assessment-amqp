package co.com.sofkau.gateway;

import co.com.sofkau.Log;
import reactor.core.publisher.Mono;

public interface ILogRepository {
    Mono<Void> save(Log log);
}
