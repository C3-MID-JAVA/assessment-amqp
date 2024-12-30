package ec.com.sofka.appservice.account;

import ec.com.sofka.Account;
import ec.com.sofka.Log;
import ec.com.sofka.gateway.AccountRepository;
import ec.com.sofka.gateway.BusMessage;
import reactor.core.publisher.Mono;

public class CreateAccountUseCase {
    private final AccountRepository accountRepository;

    private final BusMessage busMessage;

    public CreateAccountUseCase(AccountRepository accountRepository, BusMessage busMessage) {
        this.accountRepository = accountRepository;
        this.busMessage = busMessage;
    }

    public Mono<Account> apply(Account account) {
        return accountRepository.findByAccountNumber(account.getAccountNumber())
                .hasElement()
                .flatMap(hasElement -> {
                    if(hasElement) {
                        return Mono.error(new RuntimeException("Account already exists"));
                    }
                    Log log = new Log("New account created:" + account.getAccountNumber(), "account", null);
                    busMessage.sendMsg(log);
                    return accountRepository.save(account);
                });
    }
}
