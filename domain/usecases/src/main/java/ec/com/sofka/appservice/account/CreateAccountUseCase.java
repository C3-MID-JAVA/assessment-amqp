package ec.com.sofka.appservice.account;

import ec.com.sofka.Account;
import ec.com.sofka.gateway.AccountRepository;
import ec.com.sofka.gateway.BusMessage;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreateAccountUseCase {
    private final AccountRepository repository;
    //Set the port
    private final BusMessage busMessage;

    public CreateAccountUseCase(AccountRepository repository, BusMessage busMessage) {
        this.repository = repository;
        this.busMessage = busMessage;
    }

    public Mono<Account> apply(Account account){
        busMessage.sendMsg("Creating account: " + account.getOwner());
        return repository.save(account);
    }

}
