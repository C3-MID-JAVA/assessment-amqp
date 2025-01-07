package ec.com.sofka.account;


import ec.com.sofka.account.value.AccountId;
import ec.com.sofka.account.value.object.*;

import ec.com.sofka.customer.value.CustomerId;
import ec.com.sofka.generic.object.Status;
import ec.com.sofka.generic.util.Entity;

public class Account extends Entity<AccountId> {
    private AccountNumber accountNumber;
    private AccountType accountType;
    private Balance balance;
    private Status status;
    private CustomerId customerId;

    public Account(AccountId id, AccountNumber accountNumber, AccountType accountType, Balance balance, Status status, CustomerId customerId) {
        super(id);
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.status = status;
        this.customerId = customerId;
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(AccountNumber accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CustomerId getCustomer() {
        return customerId;
    }

    public void setCustomer(CustomerId customerId) {
        this.customerId = customerId;
    }
}
