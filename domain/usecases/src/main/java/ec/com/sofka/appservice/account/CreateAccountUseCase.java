package ec.com.sofka.appservice.account;

import ec.com.sofka.Account;
import ec.com.sofka.gateway.AccountRepository;
import ec.com.sofka.gateway.BusMessage;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateAccountUseCase {
    private final AccountRepository repository;
    //Set the port
    private final BusMessage busMessage;

    public CreateAccountUseCase(AccountRepository repository, BusMessage busMessage) {
        this.repository = repository;
        this.busMessage = busMessage;
    }

    public Mono<Account> apply(Account account) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentDate = dateFormat.format(new Date());
        String logMessage = "Creating account--> AccountNumber: " + account.getAccountNumber() +
                ", Balance: " + account.getBalance() +
                ", Owner: " + account.getOwner() +
                ", WS-AC[ POST (/api/cuentas) ]" +
                ", Date: " + currentDate;
        busMessage.sendMsg(logMessage);
        return repository.saveAccount(account)
                .switchIfEmpty(Mono.error(new RuntimeException("Cuenta no fue creada")));
    }
}
