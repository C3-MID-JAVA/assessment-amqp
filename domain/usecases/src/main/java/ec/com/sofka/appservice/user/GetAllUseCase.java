package ec.com.sofka.appservice.user;

import ec.com.sofka.User;
import ec.com.sofka.gateway.BusMessage;
import ec.com.sofka.gateway.UserRepository;
import reactor.core.publisher.Flux;

public class GetAllUseCase {

    private final UserRepository repository;
    private final BusMessage busMessage;

    public GetAllUseCase(UserRepository repository, BusMessage busMessage) {
        this.repository = repository;
        this.busMessage = busMessage;
    }

    public Flux<User> apply() {
        busMessage.sendMsg("user", "Get all users");
        return repository.getAll();
    }
}
