package ec.com.sofka;

import ec.com.sofka.appservice.account.CreateAccountUseCase;
import ec.com.sofka.appservice.account.GetAccountByIdUseCase;
import ec.com.sofka.appservice.transaction.CreateTransactionsUseCase;
import ec.com.sofka.appservice.transaction.GetAllTransactionsUseCase;
import ec.com.sofka.data.RequestDTO;
import ec.com.sofka.data.RequestTransactionDTO;
import ec.com.sofka.data.ResponseDTO;
import ec.com.sofka.data.ResponseTransactionDTO;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class Handler {
    private final GetAccountByIdUseCase getAccountByIdUseCase;
    private final CreateAccountUseCase createAccountUseCase;
    private final GetAllTransactionsUseCase getAllTransactionsUseCase;
    private final CreateTransactionsUseCase createTransactionsUseCase;

    public Handler(GetAccountByIdUseCase getAccountByIdUseCase,
                   CreateAccountUseCase createAccountUseCase,
                   GetAllTransactionsUseCase getAllTransactionsUseCase,
                   CreateTransactionsUseCase createTransactionsUseCase)
    {
        this.getAccountByIdUseCase = getAccountByIdUseCase;
        this.createAccountUseCase = createAccountUseCase;
        this.getAllTransactionsUseCase = getAllTransactionsUseCase;
        this.createTransactionsUseCase = createTransactionsUseCase;
    }

    public Mono<ResponseDTO> getAccountById(String id) {
        return getAccountByIdUseCase.apply(id)
                .map(account -> new ResponseDTO(
                        account.getId(),
                        account.getOwner(),
                        account.getBalance()));
    }

    public Mono<ResponseDTO> createAccount(RequestDTO request) {
        Account account = new Account(
                null,
                request.getBalance(),
                request.getCustomer());

        return createAccountUseCase.apply(account) // Devuelve un Mono<Account>
                .map(a -> new ResponseDTO(
                        a.getId(),
                        a.getOwner(),
                        a.getBalance())); // Transforma el Account en ResponseDTO
    }

    public Flux<ResponseTransactionDTO> getAllTransactions() {
        return getAllTransactionsUseCase.apply() // Devuelve un Flux<Account>
                .map(account -> new ResponseTransactionDTO(
                        account.getId(),
                        account.getType(),
                        account.getAmount(),
                        account.getTransactionCost(),
                        account.getBankAccount(),
                        account.getTimestamp()
                        )); // Transforma cada Account en ResponseDTO
    }

    public Mono<ResponseTransactionDTO> createTransaction(RequestTransactionDTO dto) {
        // Mapeamos el DTO a un objeto de dominio
        Transaction transaction = new Transaction(
                "",
                dto.getType(),
                dto.getAmount(),
                dto.getTransactionCost(),
                new Account(dto.getBankAccountId()) // Crear cuenta solo con el ID
        );

        // Llamamos al caso de uso para crear la transacción
        return createTransactionsUseCase.apply(transaction)
                .map(createdTransaction -> new ResponseTransactionDTO(
                        createdTransaction.getId(),
                        createdTransaction.getType(),
                        createdTransaction.getAmount(),
                        createdTransaction.getTransactionCost(),
                        createdTransaction.getBankAccount(),
                        createdTransaction.getTimestamp()
                ));
    }


}
