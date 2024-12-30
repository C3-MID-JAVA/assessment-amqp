package ec.com.sofka.appservice.account;

import ec.com.sofka.Account;
import ec.com.sofka.gateway.AccountRepository;
import ec.com.sofka.appservice.exception.NotFoundException;
import ec.com.sofka.gateway.BusMessage;
import ec.com.sofka.gateway.UserRepository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateAccountUseCase {

    private final AccountRepository repository;
    private final UserRepository userRepository;
    private final BusMessage busMessage;

    public CreateAccountUseCase(AccountRepository repository, UserRepository userRepository, BusMessage busMessage) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.busMessage = busMessage;
    }

    public Mono<Account> apply(Account account) {
        return userRepository.findById(account.getUserId())
                .switchIfEmpty(Mono.error(new NotFoundException("User not found")))
                .flatMap(user -> {
                            account.setBalance(BigDecimal.valueOf(0.0));
                            account.setAccountNumber(UUID.randomUUID().toString().substring(0, 8));
                            account.setUserId(user.getId());
                            Mono<Account> createdAccount =  repository.create(account);
                            busMessage.sendMsg("account", "Created account: " + account.getAccountNumber());
                            return createdAccount;
                        }
                );

    }
}
