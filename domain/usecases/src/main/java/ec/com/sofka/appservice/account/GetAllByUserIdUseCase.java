package ec.com.sofka.appservice.account;

import ec.com.sofka.Account;
import ec.com.sofka.appservice.exception.NotFoundException;
import ec.com.sofka.gateway.AccountRepository;
import ec.com.sofka.gateway.BusMessage;
import ec.com.sofka.gateway.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GetAllByUserIdUseCase {

    private final AccountRepository repository;
    private final UserRepository userRepository;
    private final BusMessage busMessage;

    public GetAllByUserIdUseCase(AccountRepository repository, UserRepository userRepository, BusMessage busMessage) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.busMessage = busMessage;
    }

    public Flux<Account> apply(String userid){
        return userRepository.findById(userid)
                .switchIfEmpty(Mono.error(new NotFoundException("User not found")))
                .flatMapMany(user -> {
                    busMessage.sendMsg("account", "Get account by user: " + user.getDocumentId());
                    return repository.getAllByUserId(userid);
                });
    }
}
