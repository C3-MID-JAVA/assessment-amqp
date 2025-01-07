package ec.com.sofka.aggregate;

import ec.com.sofka.account.Account;
import ec.com.sofka.account.value.AccountId;
import ec.com.sofka.account.event.AccountCreated;
import ec.com.sofka.account.event.AccountUpdated;
import ec.com.sofka.aggregate.handler.OperationHandler;
import ec.com.sofka.aggregate.handler.UserHandler;
import ec.com.sofka.aggregate.value.OperationId;
import ec.com.sofka.aggregate.value.UserId;
import ec.com.sofka.branch.Branch;
import ec.com.sofka.branch.event.BranchCreated;
import ec.com.sofka.branch.event.BranchUpdated;
import ec.com.sofka.branch.value.BranchId;
import ec.com.sofka.card.Card;
import ec.com.sofka.card.event.CardCreated;
import ec.com.sofka.card.event.CardUpdated;
import ec.com.sofka.card.value.CardId;
import ec.com.sofka.generic.domain.DomainEvent;
import ec.com.sofka.generic.util.AggregateRoot;
import ec.com.sofka.transaction.Transaction;
import ec.com.sofka.transaction.event.TransactionCreated;
import ec.com.sofka.transaction.event.TransactionUpdated;
import ec.com.sofka.transaction.value.TransactionId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Operation extends AggregateRoot<OperationId> {
    private Branch branch;
    private Card card;
    private Transaction transaction;

    public Operation() {
        super(new OperationId());
        setSubscription(new OperationHandler(this));
    }

    private Operation(final String id) {
        super(OperationId.of(id));
        setSubscription(new OperationHandler(this));
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public void createBranch(String address, String branchId, String name, String phone, String status) {
        addEvent(new BranchCreated(new BranchId().getValue(), address, branchId, name, phone, status)).apply();
    }

    public void updateBranch(String address, String branchId, String name, String phone, String status) {
        addEvent(new BranchUpdated(new BranchId().getValue(), address, branchId, name, phone, status)).apply();
    }

    public void createCard(String cardId, String cardNumber, String cardType, String cvv, LocalDateTime expirationDate, String status) {
        addEvent(new CardCreated(new CardId().getValue(), cardId, cardNumber, cardType, cvv, expirationDate, status)).apply();
    }

    public void updateCard(String cardId, String cardNumber, String cardType, String cvv, LocalDateTime expirationDate, String status) {
        addEvent(new CardUpdated(new CardId().getValue(), cardId, cardNumber, cardType, cvv, expirationDate, status)).apply();
    }

    public void createTransaction(BigDecimal amount, String branchId, String cardId, LocalDateTime date, String description, String destinationAccountId, String sourceAccountId, String status, String transactionId, String type) {
        addEvent(new TransactionCreated(new TransactionId().getValue(), amount, branchId, cardId, date, description, destinationAccountId, sourceAccountId, transactionId, type, status)).apply();
    }

    public void updateTransaction(BigDecimal amount, String branchId, String cardId, LocalDateTime date, String description, String destinationAccountId, String sourceAccountId, String status, String transactionId, String type) {
        addEvent(new TransactionUpdated(new TransactionId().getValue(), amount, branchId, cardId, date, description, destinationAccountId, sourceAccountId, transactionId, type, status)).apply();
    }

    public static Operation from(final String id, List<DomainEvent> events) {
        Operation operation = new Operation(id);
        events.stream()
                .filter(event -> id.equals(event.getAggregateRootId()))
                .forEach((event) -> operation.addEvent(event).apply());
        return operation;
    }
}
