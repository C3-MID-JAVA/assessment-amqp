package ec.com.sofka.appservice.account;

import ec.com.sofka.Account;
import ec.com.sofka.gateway.AccountRepository;
import ec.com.sofka.gateway.BusMessage;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;

//8. Set the use case to send a message to the bus
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentDate = dateFormat.format(new Date());
        String logMessage = "Getting account by id: " + id+
                ", WS-AC[GET (/api/cuentas/{id}) ]" +
                ", Date: " + currentDate;
        busMessage.sendMsg(logMessage);
        return repository.findByAcccountId(id)
                .switchIfEmpty(Mono.empty());
    }
}
