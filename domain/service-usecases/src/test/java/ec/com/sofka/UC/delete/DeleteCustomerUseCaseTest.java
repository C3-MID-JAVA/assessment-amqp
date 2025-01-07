package ec.com.sofka.UC.delete;

import ec.com.sofka.account.Account;
import ec.com.sofka.customer.Customer;
import ec.com.sofka.gateway.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = DeleteCustomerUseCaseTest.class)
public class DeleteCustomerUseCaseTest {
    @InjectMocks
    private DeleteCustomerUseCase deleteCustomerUseCase;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private Customer customer;

    @BeforeEach
    void setUp() {
        when(customer.getId()).thenReturn(1);
        when(customer.getAccounts()).thenReturn(Collections.singletonList(new Account()));
        when(customer.getAddress()).thenReturn("address");
        when(customer.getEmail()).thenReturn("email");
        when(customer.getFirstName()).thenReturn("firstName");
        when(customer.getLastName()).thenReturn("lastName");
        when(customer.getPhone()).thenReturn("phone");
    }

    @Test
    void testApply_shouldReturnCustomer() {
        when(customerRepository.deleteById(Mockito.any(Mono.class)))
                .thenReturn(Mono.empty());
        Mono<Void> result = deleteCustomerUseCase.apply(customer.getId());
        StepVerifier.create(result)
                .verifyComplete();
        Mockito.verify(customerRepository, Mockito.times(1)).deleteById(Mockito.any(Mono.class));
    }

    @Test
    void testApply_ShouldReturnError_WhenRepositoryFails() {
        when(customerRepository.deleteById(Mockito.any(Mono.class)))
                .thenReturn(Mono.error(new RuntimeException("Error saving branch")));
        Mono<Void> result = deleteCustomerUseCase.apply(customer.getId());
        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();
        verify(customerRepository, times(1)).deleteById(Mockito.any(Mono.class));
    }
}
