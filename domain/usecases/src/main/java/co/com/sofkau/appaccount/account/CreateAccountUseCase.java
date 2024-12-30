package co.com.sofkau.appaccount.account;


import co.com.sofkau.Account;
import co.com.sofkau.Log;
import co.com.sofkau.gateway.BusMessage;
import co.com.sofkau.gateway.IAccountRepository;
import reactor.core.publisher.Mono;

public class CreateAccountUseCase {
    private final IAccountRepository accountRepository;
    private  final GetAccountByAccountNumberUseCase getAccountByAccountNumberUseCase;

    private final BusMessage busMessage;

    public CreateAccountUseCase(IAccountRepository accountRepository, GetAccountByAccountNumberUseCase getAccountByAccountNumberUseCase, BusMessage busMessage) {
        this.accountRepository = accountRepository;
        this.getAccountByAccountNumberUseCase = getAccountByAccountNumberUseCase;
        this.busMessage = busMessage;
    }

    public Mono<Account> apply(Account account) {
        return getAccountByAccountNumberUseCase.apply(account.getAccountNumber())
                .hasElement()
                .flatMap(hasElement -> {
                    if(hasElement) {
                        return Mono.error(new RuntimeException("Account already exists"));
                    }
                    Log log = new Log( "New Account Created: " + account.getAccountNumber(),"account", "INFO", null, null);
                    busMessage.sendMsg(log);
                    return accountRepository.save(account);
                });
    }
}
