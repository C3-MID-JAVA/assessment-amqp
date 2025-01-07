package ec.com.sofka.UC.create;

import ec.com.sofka.customer.Customer;
import ec.com.sofka.gateway.repository.CustomerRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreateCustomerUseCase {
    private final CustomerRepository repository;

    public CreateCustomerUseCase(CustomerRepository repository) {
        this.repository = repository;
    }

    public Mono<Customer> apply(Mono<Customer> apply) {
        return repository.createCustomer(apply);
    }
}
