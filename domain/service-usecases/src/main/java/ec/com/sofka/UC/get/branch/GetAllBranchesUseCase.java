package ec.com.sofka.UC.get.branch;

import ec.com.sofka.branch.Branch;
import ec.com.sofka.log.TransactionLog;
import ec.com.sofka.gateway.repository.BranchRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
public class GetAllBranchesUseCase {
    private final BranchRepository repository;

    public GetAllBranchesUseCase(BranchRepository repository) {
        this.repository = repository;
    }

    public Flux<Branch> apply() {
        return createLog()
                .thenMany(repository.findAll());
    }

    private Mono<TransactionLog> createLog() {
        return Mono.fromSupplier(() -> {
            TransactionLog transactionLog = new TransactionLog();
            transactionLog.setAction("getAllBranches");
            transactionLog.setLevel("1");
            transactionLog.setMessage("test");
            transactionLog.setTimestamp(LocalDateTime.now());
            return transactionLog;
        });
    }
}
