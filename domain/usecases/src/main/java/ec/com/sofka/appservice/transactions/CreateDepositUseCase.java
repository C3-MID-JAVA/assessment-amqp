package ec.com.sofka.appservice.transactions;

import ec.com.sofka.Transaction;
import ec.com.sofka.applogs.gateway.IBusMessage;
import ec.com.sofka.appservice.transactions.transactionprocess.ProcessTransactionUseCase;
import ec.com.sofka.enums.OperationType;
import reactor.core.publisher.Mono;

public class CreateDepositUseCase {

    private final ProcessTransactionUseCase processTransactionUseCase;
    private final IBusMessage busMessage;

    public CreateDepositUseCase(ProcessTransactionUseCase processTransactionUseCase, IBusMessage busMessage) {
        this.processTransactionUseCase = processTransactionUseCase;
        this.busMessage = busMessage;
    }

    public Mono<Transaction> apply(Transaction transaction) {
        return processTransactionUseCase.apply(transaction, OperationType.DEPOSIT)
                .doOnSuccess( savedTransaction -> {
                    busMessage.sendMsg("transaction", "Transaction type: deposit "+savedTransaction.toString());
                });
    }

}
