package ec.com.sofka.appservice.account;

import ec.com.sofka.Account;
import ec.com.sofka.gateway.AccountRepository;
import ec.com.sofka.gateway.BusMessage;
import reactor.core.publisher.Flux;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

public class GetAllAccountsUseCase {
    private final AccountRepository accountRepository;
    //Set the port
    private final BusMessage busMessage;

    public GetAllAccountsUseCase(AccountRepository accountRepository, BusMessage busMessage) {
        this.accountRepository = accountRepository;
        this.busMessage = busMessage;
    }

    public Flux<Account> apply() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentDate = dateFormat.format(new Date());

        return accountRepository.findAllAccounts()
                .collectList()
                .flatMapMany(accounts -> {
                    String accountsInfo = accounts.stream()
                            .map(account -> "Account Number: " + account.getAccountNumber() +
                                    ", Owner: " + account.getOwner()+
                                    ", Balance: "+account.getBalance())
                            .collect(Collectors.joining("; "));
                    String logMessage = "Accounts List: [ " + accountsInfo + " ],  WS-AC[GET (/api/cuentas ] "+
                            "Date: " + currentDate;
                    busMessage.sendMsg(logMessage);
                    return Flux.fromIterable(accounts);
                })
                .switchIfEmpty(Flux.empty());
    }
}
