package ec.com.sofka.gateway.repository;

import ec.com.sofka.request.AccountRequestDTO;
import ec.com.sofka.response.AccountResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRepository {
    Mono<AccountResponseDTO> createAccount(Mono<AccountRequestDTO> account);

    Mono<AccountResponseDTO> findAccountById(Mono<Integer> id);

    Flux<AccountResponseDTO> getAccountsByCustomerId(Mono<Integer> customer_id);

    Flux<AccountResponseDTO> findAll();

    Mono<AccountResponseDTO> updateAccount(Mono<AccountRequestDTO> account);

    Mono<Void> deleteById(Mono<Integer> id);
}