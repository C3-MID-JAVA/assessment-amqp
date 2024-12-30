package com.bank.appservice.account;

import com.bank.Account;
import com.bank.gateway.IAccountRepository;
import com.bank.gateway.IBusMessage;
import reactor.core.publisher.Flux;

public class FindAllAccountsUseCase {
    private final IAccountRepository repository;

    private final IBusMessage busMessage;

    public FindAllAccountsUseCase(IAccountRepository repository, IBusMessage busMessage) {
        this.repository = repository;
        this.busMessage = busMessage;
    }

    public Flux<Account> apply() {
        busMessage.sendMsg("Getting all accounts.");
        return repository.findAllAccounts();
    }
}
