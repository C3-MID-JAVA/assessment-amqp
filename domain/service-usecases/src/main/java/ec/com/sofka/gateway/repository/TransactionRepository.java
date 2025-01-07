package ec.com.sofka.gateway.repository;

import ec.com.sofka.request.TransactionRequestDTO;
import ec.com.sofka.response.TransactionResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface TransactionRepository {

    Mono<TransactionResponseDTO> createTransaction(Mono<TransactionRequestDTO> transaction);

    Mono<TransactionResponseDTO> findTransactionById(Mono<Integer> id);

    Flux<TransactionResponseDTO> findAll();

    Flux<TransactionResponseDTO> findTransactionsByDestinationAccountId(Mono<Integer> id);

    Flux<TransactionResponseDTO> findTransactionsBySourceAccountId(Mono<Integer> id);

    Flux<TransactionResponseDTO> findTransactionsByBranchId(Mono<Integer> id);

    Flux<TransactionResponseDTO> findTransactionsByDate(Mono<LocalDateTime> date);

    Flux<TransactionResponseDTO> findTransactionsByType(Mono<String> type);

    Mono<TransactionResponseDTO> updateTransaction(Mono<TransactionRequestDTO> transaction);

    Mono<Void> deleteById(Mono<Integer> id);
}
