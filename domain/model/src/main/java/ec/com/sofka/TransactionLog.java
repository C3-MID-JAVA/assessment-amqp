package ec.com.sofka;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TransactionLog implements Serializable {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    private String id;
    private String transactionId;
    private String type;
    private String message;
    private LocalDateTime timestamp;

    public TransactionLog(String id, String transactionId, String message, LocalDateTime timestamp, String type) {
        this.id = id;
        this.transactionId = transactionId;
        this.message = message;
        this.timestamp = timestamp;
        this.type = type;
    }

    public TransactionLog(String transactionId, String type, String message, LocalDateTime timestamp) {
        this.transactionId = transactionId;
        this.type = type;
        this.message = message;
        this.timestamp = timestamp;
    }

    public TransactionLog(String type, String message, LocalDateTime timestamp) {
        this.type = type;
        this.message = message;
        this.timestamp = timestamp;
    }

    public TransactionLog() {
    }
}
