package ec.com.sofka.appservice.transaction;

import ec.com.sofka.Transaction;
import ec.com.sofka.appservice.exception.NotFoundException;
import ec.com.sofka.gateway.AccountRepository;
import ec.com.sofka.gateway.BusMessage;
import ec.com.sofka.gateway.TransactionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GetAllByAccountNumberUseCase {

    private final TransactionRepository repository;
    private final AccountRepository accountRepository;
    private final BusMessage busMessage;

    public GetAllByAccountNumberUseCase(TransactionRepository repository, AccountRepository accountRepository, BusMessage busMessage) {
        this.repository = repository;
        this.accountRepository = accountRepository;
        this.busMessage = busMessage;
    }

    public Flux<Transaction> apply(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .switchIfEmpty(Mono.error(new NotFoundException("Account not found")))
                .flatMapMany(account ->  {
                    busMessage.sendMsg("transaction", "Get transactions by account: " + account.getAccountNumber());
                    return repository.getAllByAccountId(account.getId());
                });
    }
}
