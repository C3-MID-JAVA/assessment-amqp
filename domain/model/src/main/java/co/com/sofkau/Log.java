package co.com.sofkau;

import co.com.sofkau.transaction.Transaction;

import java.time.LocalDateTime;

public class Log {
    private String id;
    private String message;
    private String group;
    private String level;
    private Transaction transaction;
    private LocalDateTime timestamp;

    public Log(String message, String group, String level, Transaction transaction, LocalDateTime timestamp) {
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

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
