package com.bank.handler;

import com.bank.Account;
import com.bank.appservice.account.CreateAccountUseCase;
import com.bank.appservice.account.FindAccountByIdUseCase;
import com.bank.appservice.account.FindAllAccountsUseCase;
import com.bank.data.AccountRequestDTO;
import com.bank.data.AccountResponseDTO;
import com.bank.mapper.AccountMapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Component
@Validated
public class AccountHandler {
    private final FindAllAccountsUseCase findAllAccountsUseCase;

    private final FindAccountByIdUseCase findAccountByIdUseCase;

    private final CreateAccountUseCase createAccountUseCase;

    public AccountHandler(
            FindAllAccountsUseCase findAllAccountsUseCase,
            FindAccountByIdUseCase findAccountByIdUseCase,
            CreateAccountUseCase createAccountUseCase
    ) {
        this.findAllAccountsUseCase = findAllAccountsUseCase;
        this.findAccountByIdUseCase = findAccountByIdUseCase;
        this.createAccountUseCase = createAccountUseCase;
    }

    public Flux<AccountResponseDTO> findAllAccounts() {
        return findAllAccountsUseCase.apply()
                .map(AccountMapper::toDTO)
                .switchIfEmpty(Mono.error(new NoSuchElementException("No accounts to list.")));
    }

    public Mono<AccountResponseDTO> findAccountById(String id) {
        if (id == null || id.isEmpty()) {
            return Mono.error(new IllegalArgumentException("Id cannot be null."));
        }
        return findAccountByIdUseCase.apply(id)
                .map(AccountMapper::toDTO)
                .switchIfEmpty(Mono.error(new NoSuchElementException("Account with id " + id + " does not exist.")));
    }

    public Mono<AccountResponseDTO> createAccount(@Valid AccountRequestDTO accountRequestDTO) {
        Account acc = AccountMapper.toEntity(accountRequestDTO);
        return createAccountUseCase.apply(acc)
                .map(AccountMapper::toDTO);
    }
}
