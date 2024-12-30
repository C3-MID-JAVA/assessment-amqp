package ec.com.sofka.appservice.transaction;

import ec.com.sofka.Log;
import ec.com.sofka.Transaction;
import ec.com.sofka.gateway.AccountRepository;
import ec.com.sofka.gateway.BusMessage;
import ec.com.sofka.gateway.TransactionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GetTransactionsByAccountUseCase {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final BusMessage busMessage;

    public GetTransactionsByAccountUseCase(TransactionRepository transactionRepository, AccountRepository accountRepository, BusMessage busMessage){
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.busMessage = busMessage;
    }

    public Flux<Transaction> apply(String accountNumber){
        Log log = new Log("Get transactions by account:" + accountNumber, "transaction", null);
        return accountRepository.findByAccountNumber(accountNumber)
                .switchIfEmpty(Mono.error(new RuntimeException("No transactions found with account number" + accountNumber)))
                .flatMapMany(account -> {
                    busMessage.sendMsg(log);
                    return transactionRepository.findByAccountNumber(account.getAccountNumber());
                });
    }
}
