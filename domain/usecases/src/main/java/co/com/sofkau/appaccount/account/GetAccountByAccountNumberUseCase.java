package co.com.sofkau.appaccount.account;


import co.com.sofkau.Account;
import co.com.sofkau.gateway.IAccountRepository;
import reactor.core.publisher.Mono;


public class GetAccountByAccountNumberUseCase {
    private final IAccountRepository accountRepository;

    public GetAccountByAccountNumberUseCase(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Mono<Account> apply(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).switchIfEmpty(Mono.empty());
    }
}
