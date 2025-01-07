package ec.com.sofka.gateway.repository;

import ec.com.sofka.request.CustomerRequestDTO;
import ec.com.sofka.response.CustomerResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository {

    Mono<CustomerResponseDTO> createCustomer(Mono<CustomerRequestDTO> customer);

    Mono<CustomerResponseDTO> findCustomerById(Mono<Integer> id);

    Mono<CustomerResponseDTO> findCustomerByFirstName(Mono<String> first_name);

    Mono<CustomerResponseDTO> findCustomerByLastName(Mono<String> last_name);

    Mono<CustomerResponseDTO> findCustomerByEmail(Mono<String> email);

    Flux<CustomerResponseDTO> findAll();

    Mono<CustomerResponseDTO> updateCustomer(Mono<CustomerRequestDTO> customer);

    Mono<Void> deleteById(Mono<Integer> id);
}
