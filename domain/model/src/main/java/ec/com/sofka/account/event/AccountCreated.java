package ec.com.sofka.account.event;

import ec.com.sofka.generic.domain.DomainEvent;

import java.math.BigDecimal;

public class AccountCreated extends DomainEvent {
    private String accountId;
    private String accountNumber;
    private BigDecimal balance;
    private String accountType;
    private String status;
    private String customerId;

    public AccountCreated(String eventType, BigDecimal balance, String accountId, String accountNumber, String accountType, String status, String customerId) {
        super(eventType);
        this.balance = balance;
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.status = status;
        this.customerId = customerId;
    }

    public AccountCreated(String eventType) {
        super(eventType);
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getStatus() {
        return status;
    }

    public String getCustomerId() {
        return customerId;
    }
}
