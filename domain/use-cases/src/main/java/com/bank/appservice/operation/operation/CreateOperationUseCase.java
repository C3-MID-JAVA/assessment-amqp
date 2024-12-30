package com.bank.appservice.operation.operation;

import com.bank.Operation;
import com.bank.gateway.IBusMessage;
import com.bank.gateway.IOperationRepository;
import reactor.core.publisher.Mono;

public class CreateOperationUseCase {
    private final IOperationRepository repository;

    private final IBusMessage busMessage;

    public CreateOperationUseCase(IOperationRepository repository, IBusMessage busMessage) {
        this.repository = repository;
        this.busMessage = busMessage;
    }

    public Mono<Operation> apply(Operation operation) {
        busMessage.sendMsg("Creating Operation.");
        return repository.createOperation(operation);
    }
}
