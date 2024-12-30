package ec.com.sofka.appservice.account;

import ec.com.sofka.Account;
import ec.com.sofka.Log;
import ec.com.sofka.gateway.AccountRepository;
import ec.com.sofka.gateway.BusMessage;
import reactor.core.publisher.Flux;

public class GetAllAccountsUseCase {
    private final AccountRepository accountRepository;
    private final BusMessage busMessage;

    public GetAllAccountsUseCase(AccountRepository accountRepository, BusMessage busMessage) {
        this.accountRepository = accountRepository;
        this.busMessage = busMessage;
    }

    public Flux<Account> apply(){
        Log log = new Log("Getting all accounts", "account", null);
        busMessage.sendMsg(log);
        return accountRepository.findAll();
    }
}
