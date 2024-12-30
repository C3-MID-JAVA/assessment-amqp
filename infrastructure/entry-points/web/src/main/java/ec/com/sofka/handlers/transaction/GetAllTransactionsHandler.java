package ec.com.sofka.handlers.transaction;

import ec.com.sofka.appservice.transaction.GetAllTransactionsUseCase;
import ec.com.sofka.data.dto.transactionDTO.TransactionResponseDTO;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GetAllTransactionsHandler {
    private final GetAllTransactionsUseCase getAllTransactionsUseCase;

    public GetAllTransactionsHandler(GetAllTransactionsUseCase getAllTransactionsUseCase) {
        this.getAllTransactionsUseCase = getAllTransactionsUseCase;
    }

    public Flux<TransactionResponseDTO> getTransactions() {
        return getAllTransactionsUseCase.apply()
                .map(transaction -> new TransactionResponseDTO(
                        transaction.getId(),
                        transaction.getAmount(),
                        transaction.getType(),
                        transaction.getCost(),
                        transaction.getIdAccount()
                ))
                .switchIfEmpty(Mono.empty());
    }
}