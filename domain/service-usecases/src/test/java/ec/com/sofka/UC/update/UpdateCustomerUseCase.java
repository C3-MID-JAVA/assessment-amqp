package ec.com.sofka.UC.update;

import ec.com.sofka.customer.Customer;
import ec.com.sofka.gateway.repository.CustomerRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UpdateCustomerUseCase {
    private final CustomerRepository repository;

    public UpdateCustomerUseCase(CustomerRepository repository) {
        this.repository = repository;
    }

    public Mono<Customer> apply(Mono<Customer> apply) {
        return apply;
    }
}
