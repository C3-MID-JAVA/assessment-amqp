package ec.com.sofka.UC.get.customer;

import ec.com.sofka.customer.Customer;
import ec.com.sofka.gateway.repository.CustomerRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GetCustomerByFirstNameUseCase {
    private final CustomerRepository repository;

    public GetCustomerByFirstNameUseCase(CustomerRepository repository) {
        this.repository = repository;
    }

    public Mono<Customer> apply(String firstName) {
        return null;
    }
}
