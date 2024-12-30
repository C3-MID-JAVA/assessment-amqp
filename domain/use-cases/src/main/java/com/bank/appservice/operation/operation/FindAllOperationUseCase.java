package com.bank.appservice.operation.operation;

import com.bank.Operation;
import com.bank.gateway.IBusMessage;
import com.bank.gateway.IOperationRepository;
import reactor.core.publisher.Flux;

public class FindAllOperationUseCase {
    private final IOperationRepository repository;

    private final IBusMessage busMessage;

    public FindAllOperationUseCase(IOperationRepository repository, IBusMessage busMessage) {
        this.repository = repository;
        this.busMessage = busMessage;
    }

    public Flux<Operation> apply() {
        busMessage.sendMsg("Getting all operations.");
        return repository.findAllOperations();
    }
}
