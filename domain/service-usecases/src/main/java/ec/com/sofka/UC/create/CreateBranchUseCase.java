package ec.com.sofka.UC.create;

import ec.com.sofka.branch.Branch;
import ec.com.sofka.log.TransactionLog;
import ec.com.sofka.gateway.repository.BranchRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
public class CreateBranchUseCase {
    private final BranchRepository repository;

    public CreateBranchUseCase(BranchRepository repository) {
        this.repository = repository;}

    public Mono<Branch> apply(Mono<Branch> branch) {
        return createLog()
                .then(repository.createBranch(branch));
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
