package ec.com.sofka.applogs;

import ec.com.sofka.gateway.LogRepository;
import ec.com.sofka.Log;
import reactor.core.publisher.Mono;

public class PrintLogUseCase{

    private final LogRepository logRepository;

    public PrintLogUseCase(LogRepository logRepository){
        this.logRepository = logRepository;
    }

    public Mono<Void> accept(Log log){
        System.out.println("Message received: " + log.getEntity() + " - " + log.getMessage());
        return logRepository.saveLog(log);
    }
}
