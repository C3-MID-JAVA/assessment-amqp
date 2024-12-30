package ec.com.sofka.appservice.accounts;

import ec.com.sofka.Account;
import ec.com.sofka.applogs.gateway.IBusMessage;
import ec.com.sofka.appservice.gateway.IAccountRepository;
import reactor.core.publisher.Flux;

public class GetAccountsUseCase {

    private final IAccountRepository repository;
    private final IBusMessage busMessage;
    public GetAccountsUseCase(IAccountRepository repository, IBusMessage busMessage) {
        this.repository = repository;
        this.busMessage = busMessage;
    }

    public Flux<Account> apply() {

        busMessage.sendMsg("account","found accounts: ");
        return repository.findAll();
    }

}
