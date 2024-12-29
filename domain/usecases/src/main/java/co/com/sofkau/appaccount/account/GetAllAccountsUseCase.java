package co.com.sofkau.appaccount.account;

import co.com.sofkau.Account;
import co.com.sofkau.Log;
import co.com.sofkau.gateway.BusMessage;
import co.com.sofkau.gateway.IAccountRepository;
import reactor.core.publisher.Flux;


public class GetAllAccountsUseCase  {

   private final IAccountRepository accountRepository;
   private final BusMessage busMessage;

    public GetAllAccountsUseCase(IAccountRepository accountRepository, BusMessage busMessage) {
        this.accountRepository = accountRepository;
        this.busMessage = busMessage;
    }

    public Flux<Account> apply(){
        Log log = new Log( "Getting all accounts","account", "INFO", null, null);
        busMessage.sendMsg(log);
        return accountRepository.findAllAccounts();
    }
}
