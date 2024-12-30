package ec.com.sofka.appservice.account;

import ec.com.sofka.Account;
import ec.com.sofka.gateway.AccountRepository;
import ec.com.sofka.gateway.BusMessage;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

//8. Set the use case to send a message to the bus
@Component
public class GetAccountByIdUseCase{

    private final AccountRepository repository;
    //Set the port
    private final BusMessage busMessage;

    public GetAccountByIdUseCase(AccountRepository repository, BusMessage busMessage) {
        this.repository = repository;
        this.busMessage = busMessage;
    }

    public Mono<Account> apply(String id){
        //Send a message to the bus
        busMessage.sendMsg("Getting account by id: " + id);
        return repository.findByAcccountId(id);
    }
}
