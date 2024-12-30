package ec.com.sofka.appservice.account;

import ec.com.sofka.Account;
import ec.com.sofka.appservice.exception.NotFoundException;
import ec.com.sofka.gateway.AccountRepository;
import ec.com.sofka.gateway.BusMessage;
import reactor.core.publisher.Mono;

//8. Set the use case to send a message to the bus
public class GetAccountByNumberUseCase {

    private final AccountRepository repository;
    //Set the port
    private final BusMessage busMessage;

    public GetAccountByNumberUseCase(AccountRepository repository, BusMessage busMessage) {
        this.repository = repository;
        this.busMessage = busMessage;
    }

    public Mono<Account> apply(String accountNumber){
        //Send a message to the bus
//        busMessage.sendMsg("Getting account by number: " + accountNumber);

        return repository.findByAccountNumber(accountNumber)
                .switchIfEmpty(Mono.error(new NotFoundException("Account not found")));
    }
}
