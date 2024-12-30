package co.com.sofkau.applogs;


import co.com.sofkau.Log;
import co.com.sofkau.gateway.ILogRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class PrintLogUseCase {

    private final ILogRepository logRepository;

    public PrintLogUseCase(ILogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public Mono<Void> apply(Log log) {
        System.out.println("Message received: " + log.getGroup() + ": " + log.getLevel());
        return logRepository.save(log);
    }
}
