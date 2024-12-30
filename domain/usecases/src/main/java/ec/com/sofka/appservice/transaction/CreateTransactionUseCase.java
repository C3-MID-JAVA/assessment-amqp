package ec.com.sofka.appservice.transaction;

import ec.com.sofka.Account;
import ec.com.sofka.Transaction;
import ec.com.sofka.gateway.AccountRepository;
import ec.com.sofka.gateway.BusMessage;
import ec.com.sofka.gateway.TransactionRepository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateTransactionUseCase {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final BusMessage busMessage;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    String currentDate = dateFormat.format(new Date());

    public CreateTransactionUseCase(TransactionRepository transactionRepository, AccountRepository accountRepository, BusMessage busMessage) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.busMessage = busMessage;
    }

    public Mono<Transaction> validarTransaction2(Account cuenta, BigDecimal monto, String tipo, BigDecimal costo, boolean esRetiro) {
        if (esRetiro && cuenta.getBalance().compareTo(monto.add(costo)) < 0) {
            busMessage.sendMsg("Validate Amount: " + "Account " + cuenta.getAccountNumber() +
                    " . You have insufficient balance for this transaction.  Current Amount: " + cuenta.getBalance() +
                    " , Amount to withdraw: " + monto.add(costo)+
                    ", Date: "+currentDate);
            throw new RuntimeException("Saldo insuficiente para realizar esta transacción");
        }
        actualizarSaldo(cuenta, monto, costo, tipo, esRetiro);

        Transaction transaction = new Transaction();
        transaction.setAmount(monto);
        transaction.setType(tipo);
        transaction.setCost(costo);
        transaction.setIdAccount(cuenta.getId());

        Account cuentaActualizada = new Account();
        cuentaActualizada.setId(cuenta.getId());
        cuentaActualizada.setOwner(cuenta.getOwner());
        cuentaActualizada.setBalance(cuenta.getBalance());
        cuentaActualizada.setAccountNumber(cuenta.getAccountNumber());

        return accountRepository.saveAccount(cuentaActualizada)
                .flatMap(savedAccount -> {
                    busMessage.sendMsg("Account updated:--> AccountNumber " + savedAccount.getAccountNumber() +
                            ",  New balance: " + savedAccount.getBalance()+
                            ",  Owner: "+savedAccount.getOwner()+
                            " ,  Date: "+currentDate);
                    return transactionRepository.saveTransaction(transaction)
                            .flatMap(savedTransaction -> {
                                busMessage.sendMsg("Transaction created--> Id:  " + savedTransaction.getId() +
                                        " , amount: " + savedTransaction.getAmount() +
                                        " , type: " + savedTransaction.getType() +
                                        ",  costo: " + savedTransaction.getCost()+
                                        ", idAccount: " + savedTransaction.getIdAccount() +
                                        ", WS-TR[ POST (/api/transacciones) ]" +
                                        ", Date: " + currentDate);
                                return Mono.just(savedTransaction);
                            })
                            .switchIfEmpty(Mono.error(new RuntimeException("Transaccion no fue creada")));
                });
    }

    private void actualizarSaldo(Account cuenta, BigDecimal monto, BigDecimal costo, String tipo, boolean esRetiro) {
        if (esRetiro) {
            cuenta.setBalance(cuenta.getBalance().subtract(monto.add(costo)));
        } else {
            cuenta.setBalance(cuenta.getBalance().add(monto).subtract(costo));
        }
        busMessage.sendMsg("Balance updated for account: " + cuenta.getAccountNumber() +
                " , New balance: " + cuenta.getBalance()+
                ", Date: "+currentDate);
    }

}


