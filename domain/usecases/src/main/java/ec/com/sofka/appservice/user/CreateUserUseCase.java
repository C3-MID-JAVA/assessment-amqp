package ec.com.sofka.appservice.user;

import ec.com.sofka.User;
import ec.com.sofka.appservice.exception.ConflictException;
import ec.com.sofka.gateway.BusMessage;
import ec.com.sofka.gateway.UserRepository;
import reactor.core.publisher.Mono;

public class CreateUserUseCase {

    private final UserRepository repository;
    private final BusMessage busMessage;

    public CreateUserUseCase(UserRepository repository, BusMessage busMessage) {
        this.repository = repository;
        this.busMessage = busMessage;
    }

    public Mono<User> apply(User user){
        return repository.findByDocumentId(user.getDocumentId())
                .flatMap(existingUser -> Mono.error(new ConflictException("Document ID already exists.")))
                .then(Mono.defer(() -> {
                    Mono<User> createdUser = repository.create(user);
                    busMessage.sendMsg("user", "Created user with document: " + user.getDocumentId());
                    return createdUser;
                }));
    }
}
