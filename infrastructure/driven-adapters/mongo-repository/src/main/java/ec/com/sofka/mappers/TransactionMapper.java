package ec.com.sofka.mappers;

import ec.com.sofka.Transaction;
import ec.com.sofka.data.TransactionEntity;

public class TransactionMapper {

    public static Transaction toModel(TransactionEntity transactionEntity){
        if (transactionEntity == null) {
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setId(transactionEntity.getId());
        transaction.setAmount(transactionEntity.getAmount());
        transaction.setCost(transactionEntity.getCost());
        transaction.setIdAccount(transactionEntity.getIdAccount());
        transaction.setType(transactionEntity.getType());
        return transaction;
    }

    public static TransactionEntity toDocument(Transaction transaction){
        if (transaction == null) {
            return null;
        }
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setId(transaction.getId());
        transactionEntity.setAmount(transaction.getAmount());
        transactionEntity.setCost(transaction.getCost());
        transactionEntity.setIdAccount(transaction.getIdAccount());
        transactionEntity.setType(transaction.getType());
        return transactionEntity;
    }
}
