package ec.com.sofka.appservice.account;

import ec.com.sofka.Account;
import ec.com.sofka.appservice.exception.NotFoundException;
import ec.com.sofka.gateway.AccountRepository;
import ec.com.sofka.gateway.BusMessage;
import reactor.core.publisher.Mono;

public class GetAccountByNumberUseCase {

    private final AccountRepository repository;
    private final BusMessage busMessage;

    public GetAccountByNumberUseCase(AccountRepository repository, BusMessage busMessage) {
        this.repository = repository;
        this.busMessage = busMessage;
    }

    public Mono<Account> apply(String accountNumber) {
        return repository.findByAccountNumber(accountNumber)
                .switchIfEmpty(Mono.error(new NotFoundException("Account not found")))
                .doOnNext(account ->
                        busMessage.sendMsg("account", "Account retrieved: " + account.getAccountNumber())
                );
    }
}
