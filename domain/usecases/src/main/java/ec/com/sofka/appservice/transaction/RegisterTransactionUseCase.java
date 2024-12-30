package ec.com.sofka.appservice.transaction;

import ec.com.sofka.Transaction;
import ec.com.sofka.TransactionType;
import ec.com.sofka.Log;
import ec.com.sofka.gateway.AccountRepository;
import ec.com.sofka.gateway.BusMessage;
import ec.com.sofka.gateway.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class RegisterTransactionUseCase{

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    private final BusMessage busMessage;


    public RegisterTransactionUseCase(TransactionRepository transactionRepository,
                                      AccountRepository accountRepository, BusMessage busMessage) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.busMessage = busMessage;
    }

    private static final Logger logger = LoggerFactory.getLogger(RegisterTransactionUseCase.class);
    private final Consumer<Transaction> logTransaction = transaction -> logger.info("Transaction successfully registered: {}", transaction.getId());

public Mono<Transaction> apply(String accountNumber, Transaction transaction) {
        TransactionType transactionType = transaction.getTransactionType();
        BigDecimal fee = transactionType.getFee();

        return accountRepository.findByAccountNumber(accountNumber)
                .switchIfEmpty(Mono.error(new RuntimeException("Account with ID " + accountNumber + " not found")))
                .flatMap(account -> {
                    if (isTransactionTypeWithFee.test(transactionType)) {
                        BigDecimal totalAmount = transaction.getAmount().add(fee);
                        if (account.getBalance().compareTo(totalAmount) < 0) {
                            return Mono.error(new RuntimeException("Insufficient balance for transaction"));
                        }
                    }

                    Transaction newTransaction = new Transaction();
                    newTransaction.setTransactionType(transactionType);
                    newTransaction.setAmount(transaction.getAmount());
                    newTransaction.setFee(fee);
                    newTransaction.setDate(LocalDateTime.now());
                    newTransaction.setDescription(transaction.getDescription());
                    newTransaction.setAccount(account);

                    account.setBalance(account.getBalance().subtract(transaction.getAmount()).subtract(fee));

                    Log log = new Log("New transaction was registered with id: " + transaction.getId(), "transaction", null);
                    busMessage.sendMsg(log);
                    return accountRepository.save(account)
                            .then(transactionRepository.save(newTransaction))
                            .doOnNext(logTransaction);
                });
    }


    private final Predicate<TransactionType> isTransactionTypeWithFee =
            transactionType ->  transactionType == TransactionType.WITHDRAW_ATM ||
                    transactionType == TransactionType.ONLINE_PURCHASE ||
                    transactionType == TransactionType.DEPOSIT_ATM ||
                    transactionType == TransactionType.DEPOSIT_OTHER_ACCOUNT ||
                    transactionType == TransactionType.BRANCH_DEPOSIT ||
                    transactionType == TransactionType.ONSITE_CARD_PURCHASE;
}
