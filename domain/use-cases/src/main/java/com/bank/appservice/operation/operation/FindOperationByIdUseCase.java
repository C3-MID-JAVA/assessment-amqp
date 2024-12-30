package com.bank.appservice.operation.operation;

import com.bank.Operation;
import com.bank.gateway.IBusMessage;
import com.bank.gateway.IOperationRepository;
import reactor.core.publisher.Mono;

public class FindOperationByIdUseCase {
    private final IOperationRepository repository;

    private final IBusMessage busMessage;

    public FindOperationByIdUseCase(IOperationRepository repository, IBusMessage busMessage) {
        this.repository = repository;
        this.busMessage = busMessage;
    }

    public Mono<Operation> apply(String id) {
        busMessage.sendMsg("Getting operation by id " + id);
        return repository.findOperationById(id);
    }
}
