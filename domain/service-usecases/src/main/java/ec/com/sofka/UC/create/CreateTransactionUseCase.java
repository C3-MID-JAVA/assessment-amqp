package ec.com.sofka.UC.create;

import ec.com.sofka.log.TransactionLog;
import ec.com.sofka.transaction.Transaction;
import ec.com.sofka.gateway.BusMessage;
import ec.com.sofka.gateway.repository.TransactionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
public class CreateTransactionUseCase {

    private final TransactionRepository repository;
    private final BusMessage busMessage;

    public CreateTransactionUseCase(TransactionRepository repository, BusMessage busMessage) {
        this.repository = repository;
        this.busMessage = busMessage;
    }

    public Mono<Transaction> apply(Mono<Transaction> transaction) {
        return transaction
                .flatMap(tx -> {
                    TransactionLog transactionLog = new TransactionLog(
                            tx.getSourceAccount().getId(),
                            "CREATE",
                            "INFO",
                            "Transaction ok",
                            "SUCCESS",
                            LocalDateTime.now(),
                            tx.getId(),
                            tx.getType()
                    );
                    return repository.createTransaction(Mono.just(tx))
                            .flatMap(createdTx ->
                                    busMessage.sendMsg(Mono.just(transactionLog))
                                            .then(Mono.just(createdTx))
                            );
                });
    }
}
