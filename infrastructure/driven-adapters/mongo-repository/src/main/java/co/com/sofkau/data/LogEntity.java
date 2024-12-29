package co.com.sofkau.data;

import co.com.sofkau.transaction.Transaction;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "logs")
public class LogEntity {

    @Id
    private String id;

    private String message;
    private String group;
    private String level;
    private TransactionEntity transaction;
    private LocalDateTime timestamp;

    public LogEntity(String id, String message, String group, String level, TransactionEntity transaction, LocalDateTime timestamp) {
        this.id = id;
        this.message = message;
        this.group = group;
        this.level = level;
        this.transaction = transaction;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transaction) {
        this.transaction = transaction;
    }
}
