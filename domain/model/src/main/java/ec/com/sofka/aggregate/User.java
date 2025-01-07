package ec.com.sofka.aggregate;

import ec.com.sofka.account.Account;
import ec.com.sofka.account.event.AccountCreated;
import ec.com.sofka.account.event.AccountUpdated;
import ec.com.sofka.customer.event.CustomerCreated;
import ec.com.sofka.customer.event.CustomerUpdated;
import ec.com.sofka.aggregate.handler.UserHandler;
import ec.com.sofka.aggregate.value.UserId;
import ec.com.sofka.account.value.AccountId;
import ec.com.sofka.customer.Customer;
import ec.com.sofka.customer.value.CustomerId;
import ec.com.sofka.generic.domain.DomainEvent;
import ec.com.sofka.generic.util.AggregateRoot;

import java.math.BigDecimal;
import java.util.List;

public class User extends AggregateRoot<UserId> {
    private Account account;
    private Customer customer;

    public User() {
        super(new UserId());
        setSubscription(new UserHandler(this));
    }

    private User(final String id) {
        super(UserId.of(id));
        setSubscription(new UserHandler(this));
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void createAccount (BigDecimal balance, String accountId, String accountNumber, String accountType, String status, String customerId) {
        addEvent(new AccountCreated(new AccountId().getValue(), balance, accountId, accountNumber, accountType, status, customerId)).apply();
    }

    public void updateAccount(BigDecimal balance, String accountId, String accountNumber, String accountType, String status, String customerId) {
        addEvent(new AccountUpdated(new AccountId().getValue(), balance, accountId, accountNumber, accountType, status, customerId)).apply();
    }

    public void createCustomer(String address, String customerId, String email, String firstName, String lastName, String phone, String status) {
        addEvent(new CustomerCreated(new CustomerId().getValue(), address, customerId, email, firstName, lastName, phone, status)).apply();
    }

    public void updateCustomer(String address, String customerId, String email, String firstName, String lastName, String phone, String status) {
        addEvent(new CustomerUpdated(new CustomerId().getValue(), address, customerId, email, firstName, lastName, phone, status)).apply();
    }

    public static User from(final String id, List<DomainEvent> events) {
        User user = new User(id);
        events.stream()
                .filter(event -> id.equals(event.getAggregateRootId()))
                .forEach((event) -> user.addEvent(event).apply());
        return user;
    }
}
