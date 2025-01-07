package ec.com.sofka.aggregate.handler;


import ec.com.sofka.account.value.AccountId;
import ec.com.sofka.aggregate.Operation;
import ec.com.sofka.branch.Branch;
import ec.com.sofka.branch.event.BranchCreated;
import ec.com.sofka.branch.event.BranchUpdated;
import ec.com.sofka.branch.value.BranchId;
import ec.com.sofka.branch.value.object.Address;
import ec.com.sofka.branch.value.object.Name;
import ec.com.sofka.branch.value.object.Phone;
import ec.com.sofka.card.Card;
import ec.com.sofka.card.event.CardCreated;
import ec.com.sofka.card.event.CardUpdated;
import ec.com.sofka.card.value.CardId;
import ec.com.sofka.card.value.object.CVV;
import ec.com.sofka.card.value.object.CardNumber;
import ec.com.sofka.card.value.object.CardType;
import ec.com.sofka.card.value.object.ExpirationDate;
import ec.com.sofka.generic.domain.DomainActionsContainer;
import ec.com.sofka.generic.object.Status;
import ec.com.sofka.transaction.Transaction;
import ec.com.sofka.transaction.event.TransactionCreated;
import ec.com.sofka.transaction.event.TransactionUpdated;
import ec.com.sofka.transaction.value.TransactionId;
import ec.com.sofka.transaction.value.object.Amount;
import ec.com.sofka.transaction.value.object.Date;
import ec.com.sofka.transaction.value.object.Description;
import ec.com.sofka.transaction.value.object.Type;

public class OperationHandler extends DomainActionsContainer {
    public OperationHandler(Operation operation) {
        addDomainActions((CardCreated event) -> {
            Card card = new Card(
                    CardId.of(event.getCardId()),
                    CardNumber.of(event.getCardNumber()),
                    CardType.of(event.getCardType()),
                    CVV.of(event.getCvv()),
                    ExpirationDate.of(event.getExpirationDate()),
                    Status.of(event.getStatus())
            );
            operation.setCard(card);
        });
        addDomainActions((CardUpdated event) -> {
            Card card = new Card(
                    CardId.of(event.getCardId()),
                    CardNumber.of(event.getCardNumber()),
                    CardType.of(event.getCardType()),
                    CVV.of(event.getCvv()),
                    ExpirationDate.of(event.getExpirationDate()),
                    Status.of(event.getStatus())
            );
            operation.setCard(card);
        });
        addDomainActions((BranchCreated event) -> {
            Branch branch = new Branch(
                    BranchId.of(event.getBranchId()),
                    Address.of(event.getAddress()),
                    Name.of(event.getName()),
                    Phone.of(event.getPhone()),
                    Status.of(event.getStatus())
            );
            operation.setBranch(branch);
        });
        addDomainActions((BranchUpdated event) -> {
            Branch branch = new Branch(
                    BranchId.of(event.getBranchId()),
                    Address.of(event.getAddress()),
                    Name.of(event.getName()),
                    Phone.of(event.getPhone()),
                    Status.of(event.getStatus())
            );
            operation.setBranch(branch);
        });
        addDomainActions((TransactionCreated event) -> {
            Transaction transaction = new Transaction(
                    TransactionId.of(event.getTransactionId()),
                    Amount.of(event.getAmount()),
                    BranchId.of(event.getBranchId()),
                    CardId.of(event.getCardId()),
                    Date.of(event.getDate()),
                    Description.of(event.getDescription()),
                    AccountId.of(event.getDestinationAccountId()),
                    AccountId.of(event.getSourceAccountId()),
                    Status.of(event.getStatus()),
                    Type.of(event.getType())
            );
            operation.setTransaction(transaction);
        });
        addDomainActions((TransactionUpdated event) -> {
            Transaction transaction = new Transaction(
                    TransactionId.of(event.getTransactionId()),
                    Amount.of(event.getAmount()),
                    BranchId.of(event.getBranchId()),
                    CardId.of(event.getCardId()),
                    Date.of(event.getDate()),
                    Description.of(event.getDescription()),
                    AccountId.of(event.getDestinationAccountId()),
                    AccountId.of(event.getSourceAccountId()),
                    Status.of(event.getStatus()),
                    Type.of(event.getType())
            );
            operation.setTransaction(transaction);
        });
    }
}
