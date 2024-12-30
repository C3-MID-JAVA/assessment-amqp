package ec.com.sofka;

import ec.com.sofka.config.ITransactionsRepository;
import ec.com.sofka.data.AccountEntity;
import ec.com.sofka.data.TransactionsEntity;
import ec.com.sofka.gateway.TransactionRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class TransactionsAdapter implements TransactionRepository {

    private final ITransactionsRepository repository;

    public TransactionsAdapter(ITransactionsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<Transaction> getAllTransactions() {
        // Obtenemos todas las transacciones desde el repositorio y las mapeamos a objetos Transaction
        return repository.findAll()
                .switchIfEmpty(Flux.error(new RuntimeException("No transactions found")))
                .map(this::mapToTransaction);  // Mapear de TransactionEntity a Transaction
    }

    @Override
    public Mono<Transaction> createTransaction(Transaction transaction) {
        // Mapeamos Transaction a TransactionEntity para guardar en el repositorio
        TransactionsEntity entity = mapToTransactionEntity(transaction);

        return repository.save(entity)
                .onErrorMap(e -> new RuntimeException("Error while creating transaction", e))
                .map(this::mapToTransaction);  // Mapear de TransactionEntity a Transaction
    }

    // Método para mapear de TransactionEntity a Transaction
    private Transaction mapToTransaction(TransactionsEntity entity) {
        return new Transaction(
                entity.getId(),
                entity.getType(),
                entity.getAmount(),
                entity.getTransactionCost(),
                new Account(entity.getBankAccount().getId(),entity.getAmount(),entity.getBankAccount().getOwner())
        );
    }

    // Método para mapear de Transaction a TransactionEntity
    private TransactionsEntity mapToTransactionEntity(Transaction transaction) {
        return new TransactionsEntity(
                transaction.getType(),
                transaction.getAmount(),
                transaction.getTransactionCost(),
                new AccountEntity(transaction.getBankAccount().getBalance(),
                        transaction.getBankAccount().getOwner())
        );
    }

}
