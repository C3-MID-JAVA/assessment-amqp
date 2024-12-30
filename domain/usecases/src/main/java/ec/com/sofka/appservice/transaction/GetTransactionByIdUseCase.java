package ec.com.sofka.appservice.transaction;

import ec.com.sofka.Transaction;
import ec.com.sofka.gateway.BusMessage;
import ec.com.sofka.gateway.TransactionRepository;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTransactionByIdUseCase {
    private final TransactionRepository transactionRepository;
    private final BusMessage busMessage;

    public GetTransactionByIdUseCase(TransactionRepository transactionRepository, BusMessage busMessage) {
        this.transactionRepository = transactionRepository;
        this.busMessage = busMessage;
    }

    public Mono<Transaction> apply(String id) {
       // busMessage.sendMsg("Get Transaction by id: " + id);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentDate = dateFormat.format(new Date());
        String logMessage = "Get Transaction by id: " + id+
                ", WS-TR[POST (/api/transacciones/{id}) ]" +
                ", Date: " + currentDate;
        busMessage.sendMsg(logMessage);
        return transactionRepository.findTransactionById(id);
    }
}