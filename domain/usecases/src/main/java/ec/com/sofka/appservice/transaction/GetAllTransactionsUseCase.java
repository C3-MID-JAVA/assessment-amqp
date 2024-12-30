package ec.com.sofka.appservice.transaction;

import ec.com.sofka.Transaction;
import ec.com.sofka.gateway.BusMessage;
import ec.com.sofka.gateway.TransactionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class GetAllTransactionsUseCase {

    private final TransactionRepository transactionRepository;

    private final BusMessage busMessage;

    public GetAllTransactionsUseCase(TransactionRepository transactionRepository,
                                     BusMessage busMessage) {
        this.transactionRepository = transactionRepository;
        this.busMessage = busMessage;
    }

    public Flux<Transaction> apply() {
        busMessage.sendMsg("Loading all transactions....");
        return transactionRepository.getAllTransactions();
    }

}
