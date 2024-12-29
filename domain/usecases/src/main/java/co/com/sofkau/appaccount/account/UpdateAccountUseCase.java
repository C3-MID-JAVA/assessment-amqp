package co.com.sofkau.appaccount.account;


import co.com.sofkau.Account;
import co.com.sofkau.gateway.IAccountRepository;
import reactor.core.publisher.Mono;


public class UpdateAccountUseCase {
    private final IAccountRepository accountRepository;


    public UpdateAccountUseCase(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Mono<Account> apply(Account account) {
        return accountRepository.save(account);
    }
}
