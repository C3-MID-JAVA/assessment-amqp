package ec.com.sofka.applogs;

import ec.com.sofka.Log;
import ec.com.sofka.gateway.LogRepository;

import java.time.LocalDateTime;

public class PrintLogUseCase{

    private final LogRepository logRepository;

    public PrintLogUseCase(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void accept(String entity, String message){
        Log log = new Log(message, entity, LocalDateTime.now());
        logRepository.create(log).subscribe();
    }
}
