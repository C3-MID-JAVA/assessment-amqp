package ec.com.sofka.appservice.accounts;

import ec.com.sofka.Account;
import ec.com.sofka.ConflictException;
import ec.com.sofka.applogs.gateway.IBusMessage;
import ec.com.sofka.appservice.gateway.IAccountRepository;
import reactor.core.publisher.Mono;

public class CreateAccountUseCase {

    private final IAccountRepository repository;
    private final IBusMessage busMessage;
    public CreateAccountUseCase(IAccountRepository repository, IBusMessage busMessage) {
        this.repository = repository;
        this.busMessage = busMessage;
    }

    public Mono<Account> apply(Account account) {

        return repository.findByAccountNumber(account.getAccountNumber())
                .flatMap(existingAccount -> Mono.<Account>error(new ConflictException("The account number is already registered.")))
                .switchIfEmpty(Mono.defer(() -> repository.save(account)
                        .doOnSuccess(savedAccount -> {
                            busMessage.sendMsg("account", "Create account: " + savedAccount.toString());
                        })));
    }
}
