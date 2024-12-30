package ec.com.sofka;


import ec.com.sofka.config.IAccountMongoRepository;
import ec.com.sofka.data.AccountEntity;
import ec.com.sofka.gateway.AccountRepository;
import ec.com.sofka.mappers.AccountMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Repository
public class MongoAdapterAccount implements AccountRepository {

    private final IAccountMongoRepository repository;

    public MongoAdapterAccount(IAccountMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Account> findByAcccountId(String id) {
        //AccountEntity found = repository.findById(id).get();
        //return new Account(found.getId(), found.getBalance(), found.getOwner(), found.getAccountNumber());
        return repository.findById(id)
                .map(AccountMapper::toModel);
    }

    @Override
    public Mono<Account>  saveAccount(Account account) {
        AccountEntity a = new AccountEntity(account.getBalance(), account.getAccountNumber(), account.getOwner(), new ArrayList<>());
        //AccountEntity saved = repository.save(a);
        //return new Account(saved.getId(), saved.getBalance(), saved.getOwner(), saved.getAccountNumber());
        return repository.save(AccountMapper.toDocument(account))
                .map(AccountMapper::toModel);
    }

    @Override
    public Flux<Account> findAllAccounts() {
        return repository.findAll()
                .map(AccountMapper::toModel);
    }
}
