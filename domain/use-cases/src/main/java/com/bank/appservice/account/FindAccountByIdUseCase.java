package com.bank.appservice.account;

import com.bank.Account;
import com.bank.gateway.IAccountRepository;
import com.bank.gateway.IBusMessage;
import reactor.core.publisher.Mono;

public class FindAccountByIdUseCase {
    private final IAccountRepository repository;

    private final IBusMessage busMessage;

    public FindAccountByIdUseCase(IAccountRepository repository, IBusMessage busMessage) {
        this.repository = repository;
        this.busMessage = busMessage;
    }

    public Mono<Account> apply(String id) {
        busMessage.sendMsg("Getting account by id " + id);
        return repository.findAccountById(id);
    }
}
