package ec.com.sofka.cases.account;

import ec.com.sofka.Account;
import ec.com.sofka.exception.ConflictException;
import ec.com.sofka.gateway.AccountRepository;
import ec.com.sofka.gateway.ErrorBusMessage;
import ec.com.sofka.model.ErrorMessage;
import reactor.core.publisher.Mono;

public class CreateAccountUseCase {
    private final AccountRepository accountRepository;
    private final ErrorBusMessage errorBusMessage;

    public CreateAccountUseCase(AccountRepository accountRepository, ErrorBusMessage errorBusMessage) {
        this.accountRepository = accountRepository;
        this.errorBusMessage = errorBusMessage;
    }

    public Mono<Account> apply(Account account) {
        return accountRepository.findByNumber(account.getNumber())
                .flatMap(accountFound -> {
                    errorBusMessage.sendMsg(new ErrorMessage("Account already exists (" + account.getNumber() + ")",
                            "Create Account"));
                    return Mono.<Account>error(new ConflictException("The account is already registered."));
                })
                .switchIfEmpty(accountRepository.save(account));
    }

}