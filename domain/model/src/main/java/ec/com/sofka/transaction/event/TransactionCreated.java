package ec.com.sofka.transaction.event;

import ec.com.sofka.generic.domain.DomainEvent;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionCreated extends DomainEvent {
    private String transactionId;
    private String branchId;
    private String cardId;
    private LocalDateTime date;
    private String type;
    private BigDecimal amount;
    private String description;
    private String destinationAccountId;
    private String sourceAccountId;
    private String status;

    public TransactionCreated(String eventType, BigDecimal amount, String branchId, String cardId, LocalDateTime date, String description, String destinationAccountId, String sourceAccountId, String status, String transactionId, String type) {
        super(eventType);
        this.amount = amount;
        this.branchId = branchId;
        this.cardId = cardId;
        this.date = date;
        this.description = description;
        this.destinationAccountId = destinationAccountId;
        this.sourceAccountId = sourceAccountId;
        this.status = status;
        this.transactionId = transactionId;
        this.type = type;
    }

    public TransactionCreated(String eventType) {
        super(eventType);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getCardId() {
        return cardId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getDestinationAccountId() {
        return destinationAccountId;
    }

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public String getStatus() {
        return status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getType() {
        return type;
    }
}
