package ec.com.sofka.adapter;

import ec.com.sofka.Account;
import ec.com.sofka.appservice.gateway.IAccountRepository;
import ec.com.sofka.config.IMongoAccountRepository;
import ec.com.sofka.mapper.AccountMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class AccountAdapter implements IAccountRepository {

    private final IMongoAccountRepository repository;

    public AccountAdapter(IMongoAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Account> findByAccountNumber(String accountNumber) {
        return repository.findByAccountNumber(accountNumber)
                .map(AccountMapper::accountEntityToAccount
                );
    }

    @Override
    public Mono<Account> save(Account account) {
        return repository.save(AccountMapper.accountToAccountEntity(account)).map(AccountMapper::accountEntityToAccount);
    }

    @Override
    public Flux<Account> findAll() {
        return repository.findAll().map(AccountMapper::accountEntityToAccount);
    }

    @Override
    public Mono<Account> findById(String id) {
        return repository.findById(id).map(AccountMapper::accountEntityToAccount);
    }
}
