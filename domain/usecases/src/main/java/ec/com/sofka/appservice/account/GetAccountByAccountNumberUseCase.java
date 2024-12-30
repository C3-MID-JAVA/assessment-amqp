package ec.com.sofka.appservice.account;

import ec.com.sofka.Account;
import ec.com.sofka.Log;
import ec.com.sofka.gateway.AccountRepository;
import ec.com.sofka.gateway.BusMessage;
import reactor.core.publisher.Mono;

public class GetAccountByAccountNumberUseCase {

    private final AccountRepository accountRepository;
    private final BusMessage busMessage;

    public GetAccountByAccountNumberUseCase(AccountRepository accountRepository, BusMessage busMessage) {

        this.accountRepository = accountRepository;
        this.busMessage = busMessage;
    }

    public Mono<Account> apply(String accountNumber){
        Log log = new Log("Account retrieved:" + accountNumber, "account", null);
        return accountRepository.findByAccountNumber(accountNumber)
                .switchIfEmpty(Mono.empty())
                .doOnNext(account ->
                        busMessage.sendMsg(log)
                );
    }
}
