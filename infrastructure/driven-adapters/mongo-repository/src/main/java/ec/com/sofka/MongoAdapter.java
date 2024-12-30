package ec.com.sofka;

import ec.com.sofka.config.IMongoRepository;
import ec.com.sofka.data.AccountEntity;
import ec.com.sofka.gateway.AccountRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MongoAdapter implements AccountRepository {

    private final IMongoRepository repository;

    public MongoAdapter(IMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Account> findByAcccountId(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Account not found with id: " + id)))
                .map(found -> new Account(found.getId(), found.getBalance(), found.getOwner()));
    }

    @Override
    public Mono<Account> save(Account account) {
        AccountEntity accountEntity = new AccountEntity(account.getBalance(), account.getOwner());
        return repository.save(accountEntity)
                .map(saved -> new Account(saved.getId(), saved.getBalance(), saved.getOwner()));
    }


}
