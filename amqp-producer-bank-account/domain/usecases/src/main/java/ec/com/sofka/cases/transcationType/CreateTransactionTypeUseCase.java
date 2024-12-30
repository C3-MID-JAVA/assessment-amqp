package ec.com.sofka.cases.transcationType;

import ec.com.sofka.TransactionType;
import ec.com.sofka.exception.ConflictException;
import ec.com.sofka.gateway.ErrorBusMessage;
import ec.com.sofka.gateway.TransactionTypeRepository;
import ec.com.sofka.model.ErrorMessage;
import reactor.core.publisher.Mono;

public class CreateTransactionTypeUseCase {
    private final TransactionTypeRepository transactionTypeRepository;
    private final ErrorBusMessage errorBusMessage;

    public CreateTransactionTypeUseCase(TransactionTypeRepository transactionTypeRepository, ErrorBusMessage errorBusMessage) {
        this.transactionTypeRepository = transactionTypeRepository;
        this.errorBusMessage = errorBusMessage;
    }

    public Mono<TransactionType> apply(TransactionType transactionType) {
        return transactionTypeRepository.findByType(transactionType.getType())
                .flatMap(typeFound -> {
                    errorBusMessage.sendMsg(new ErrorMessage("Transaction type is already registered (" + transactionType.getType() + ")",
                            "Create TransactionType"));
                    return Mono.<TransactionType>error(new ConflictException("The transaction type is already registered."));
                })
                .switchIfEmpty(transactionTypeRepository.save(transactionType));
    }

}