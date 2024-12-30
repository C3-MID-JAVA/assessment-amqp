package com.bank.handler;

import com.bank.Operation;
import com.bank.data.OperationRequestDTO;
import com.bank.data.OperationResponseDTO;
import com.bank.mapper.OperationMapper;
import com.bank.appservice.operation.operation.CreateOperationUseCase;
import com.bank.appservice.operation.operation.FindAllOperationUseCase;
import com.bank.appservice.operation.operation.FindOperationByIdUseCase;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Component
@Validated
public class OperationHandler {
    private final FindAllOperationUseCase findAllOperationUseCase;

    private final FindOperationByIdUseCase findOperationByIdUseCase;

    private final CreateOperationUseCase createOperationUseCase;

    public OperationHandler(
            FindAllOperationUseCase findAllOperationUseCase,
            FindOperationByIdUseCase findOperationByIdUseCase,
            CreateOperationUseCase createOperationUseCase
    ) {
        this.findAllOperationUseCase = findAllOperationUseCase;
        this.findOperationByIdUseCase = findOperationByIdUseCase;
        this.createOperationUseCase = createOperationUseCase;
    }

    public Flux<OperationResponseDTO> findAllOperations() {
        return findAllOperationUseCase.apply()
                .map(OperationMapper::toDTO)
                .switchIfEmpty(Mono.error(new NoSuchElementException("No operations to list.")));
    }

    public Mono<OperationResponseDTO> findOperationById(String id) {
        if (id == null || id.isEmpty()) {
            return Mono.error(new IllegalArgumentException("Id cannot be null."));
        }
        return findOperationByIdUseCase.apply(id)
                .map(OperationMapper::toDTO)
                .switchIfEmpty(Mono.error(new NoSuchElementException("Operation with id " + id + " does not exist.")));
    }

    public Mono<OperationResponseDTO> createOperation(@Valid OperationRequestDTO operationRequestDTO) {
        Operation op = OperationMapper.toEntity(operationRequestDTO);
        return createOperationUseCase.apply(op)
                .map(OperationMapper::toDTO);
    }
}
