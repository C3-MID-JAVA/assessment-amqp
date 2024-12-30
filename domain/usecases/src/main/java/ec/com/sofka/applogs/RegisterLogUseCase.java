package ec.com.sofka.applogs;

import ec.com.sofka.Log;
import ec.com.sofka.gateway.LogRepository;
import org.springframework.stereotype.Component;

@Component
public class RegisterLogUseCase {

    private final LogRepository logRepository;
    //Set the port
    public RegisterLogUseCase(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public Log apply(Log log) {

        return logRepository.saveLog(log);
    }

}
