package ec.com.sofka.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionMessage {
    String transactionId;
    String transactionTypeId;
    String transactionDate;

    public TransactionMessage(String transactionId, String transactionTypeId) {
        this.transactionId = transactionId;
        this.transactionTypeId = transactionTypeId;
        this.transactionDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(String transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

}