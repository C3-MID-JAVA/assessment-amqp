package ec.com.sofka.UC.get.account;

import ec.com.sofka.account.Account;
import ec.com.sofka.gateway.repository.AccountRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GetAccountByIdUseCase {

    private final AccountRepository repository;

    public GetAccountByIdUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    public Mono<Account> apply(int id) {
        return repository.findAccountById(Mono.just(id));
    }
}
