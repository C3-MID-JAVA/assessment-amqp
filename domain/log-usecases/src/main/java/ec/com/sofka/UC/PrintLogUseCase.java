package ec.com.sofka.UC;

import ec.com.sofka.log.TransactionLog;
import ec.com.sofka.gateway.repository.LogRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

//18. UseCase for printing logs
@Component
public class PrintLogUseCase {

    private final LogRepository repository;

    public PrintLogUseCase(LogRepository repository) {
        this.repository = repository;
    }

    public Mono<Void> apply(Mono<TransactionLog> log) {
        return repository.createLog(log);
    }
}
