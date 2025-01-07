package ec.com.sofka.log;

import java.time.LocalDateTime;

public class TransactionLog {
    private Integer transactionId;     // ID único de la transacción
    private String action;             // Tipo de operación: CREATE, TRANSFER, etc.
    private Integer accountId;         // Cuenta o entidad afectada
    private String message;            // Descripción o detalle de la operación
    private String level;              // Nivel de severidad (INFO, WARN, ERROR)
    private LocalDateTime timestamp;   // Momento en que se generó el log
    private String status;             // Estado de la transacción (SUCCESS, FAILED, PENDING, etc.)
    private String type;               // (branch_transfer|another_account_deposit|store_card_purchase|online_card_purchase|atm_withdrawal|atm_deposit)

    public TransactionLog(Integer accountId, String action, String level, String message, String status, LocalDateTime timestamp, Integer transactionId, String type) {
        this.accountId = accountId;
        this.action = action;
        this.level = level;
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
        this.transactionId = transactionId;
        this.type = type;
    }

    public TransactionLog() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "TransactionLog{" +
                "transactionId='" + transactionId + '\'' +
                ", action='" + action + '\'' +
                ", accountId='" + accountId + '\'' +
                ", message='" + message + '\'' +
                ", level='" + level + '\'' +
                ", timestamp=" + timestamp + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}