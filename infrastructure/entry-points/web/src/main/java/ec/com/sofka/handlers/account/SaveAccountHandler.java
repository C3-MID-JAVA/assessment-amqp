package ec.com.sofka.handlers.account;

import ec.com.sofka.Account;
import ec.com.sofka.appservice.account.CreateAccountUseCase;
import ec.com.sofka.data.dto.accountDTO.AccountRequestDTO;
import ec.com.sofka.data.dto.accountDTO.AccountResponseDTO;
import ec.com.sofka.validator.RequestValidator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class SaveAccountHandler {
    private final CreateAccountUseCase createAccountUseCase;
    private final RequestValidator requestValidator;

    public SaveAccountHandler(CreateAccountUseCase createAccountUseCase, RequestValidator requestValidator) {
        this.createAccountUseCase = createAccountUseCase;
        this.requestValidator = requestValidator;
    }

    public Mono<AccountResponseDTO> handle(AccountRequestDTO accountRequestDTO) {
        Account account = new Account();
        account.setId(accountRequestDTO.getId());
        account.setBalance(accountRequestDTO.getBalance());
        account.setAccountNumber(accountRequestDTO.getAccountNumber());
        account.setOwner(accountRequestDTO.getOwner());

        return createAccountUseCase.apply(account)
                .map(savedAccount -> new AccountResponseDTO(
                        savedAccount.getId(),
                        savedAccount.getBalance(),
                        savedAccount.getAccountNumber(),
                        savedAccount.getOwner()
                ));
    }
}