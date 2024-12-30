package ec.com.sofka.cases.user;

import ec.com.sofka.exception.ConflictException;
import ec.com.sofka.User;
import ec.com.sofka.gateway.ErrorBusMessage;
import ec.com.sofka.gateway.UserRepository;
import ec.com.sofka.model.ErrorMessage;
import reactor.core.publisher.Mono;

public class CreateUserUseCase {
    private final UserRepository userRepository;
    private final ErrorBusMessage errorBusMessage;

    public CreateUserUseCase(UserRepository userRepository, ErrorBusMessage errorBusMessage) {
        this.userRepository = userRepository;
        this.errorBusMessage = errorBusMessage;
    }

    public Mono<User> apply(User user) {
        return userRepository.findByCi(user.getCi())
                .flatMap(userFound -> {
                    errorBusMessage.sendMsg(new ErrorMessage("User is already registered (" + user.getCi() + ")",
                            "Create User"));
                    return Mono.<User>error(new ConflictException("The user is already registered."));
                })
                .switchIfEmpty(userRepository.save(user));
    }

}