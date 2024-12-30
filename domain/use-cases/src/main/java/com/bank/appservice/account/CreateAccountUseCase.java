package com.bank.appservice.account;

import com.bank.Account;
import com.bank.gateway.IAccountRepository;
import com.bank.gateway.IBusMessage;
import reactor.core.publisher.Mono;

public class CreateAccountUseCase {
    private final IAccountRepository repository;

    private final IBusMessage busMessage;

    public CreateAccountUseCase(IAccountRepository repository, IBusMessage busMessage) {
        this.repository = repository;
        this.busMessage = busMessage;
    }

    public Mono<Account> apply(Account account) {
        busMessage.sendMsg("Creating account.");
        return repository.createAccount(account);
    }
}
