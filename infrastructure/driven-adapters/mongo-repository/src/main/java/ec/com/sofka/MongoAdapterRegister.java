/*package ec.com.sofka;

import ec.com.sofka.config.IRegisterRepository;
import ec.com.sofka.data.RegisterEntity;
import ec.com.sofka.gateway.RegisterRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MongoAdapterRegister implements RegisterRepository {
    private final IRegisterRepository repository;

    public MongoAdapterRegister(IRegisterRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Void> create(Register register) {
        RegisterEntity registerEntity = new RegisterEntity(register.getMessage());
        return repository.save(registerEntity).then();
    }
}
*/