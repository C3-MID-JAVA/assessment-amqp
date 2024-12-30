package ec.com.sofka.appservice.transaction;

import ec.com.sofka.Transaction;
import ec.com.sofka.gateway.BusMessage;
import ec.com.sofka.gateway.TransactionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
@Component
public class CreateTransactionsUseCase {

    private final TransactionRepository transactionRepository;

    private final BusMessage busMessage;

    public CreateTransactionsUseCase(
            TransactionRepository transactionRepository,
                                     BusMessage busMessage)
    {
        this.transactionRepository = transactionRepository;
        this.busMessage = busMessage;
    }

    public Mono<Transaction> apply(Transaction transaction) {
        busMessage.sendMsg("Transaction created of type: " + transaction.getType());
        return transactionRepository.createTransaction(transaction);
    }

}
