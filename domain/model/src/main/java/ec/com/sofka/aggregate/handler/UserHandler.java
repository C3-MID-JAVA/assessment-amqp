package ec.com.sofka.aggregate.handler;

import ec.com.sofka.account.Account;
import ec.com.sofka.account.value.object.*;
import ec.com.sofka.customer.value.object.*;
import ec.com.sofka.aggregate.User;
import ec.com.sofka.account.event.AccountCreated;
import ec.com.sofka.account.event.AccountUpdated;
import ec.com.sofka.account.value.AccountId;
import ec.com.sofka.customer.event.CustomerCreated;
import ec.com.sofka.customer.event.CustomerUpdated;
import ec.com.sofka.customer.Customer;
import ec.com.sofka.customer.value.CustomerId;
import ec.com.sofka.generic.domain.DomainActionsContainer;
import ec.com.sofka.generic.object.Status;

public class UserHandler extends DomainActionsContainer {
    public UserHandler(User user) {
        addDomainActions((AccountCreated event) -> {
            Account account = new Account(
                    AccountId.of(event.getAccountId()),
                    AccountNumber.of(event.getAccountNumber()),
                    AccountType.of(event.getAccountType()),
                    Balance.of(event.getBalance()),
                    Status.of(event.getStatus()),
                    CustomerId.of(event.getCustomerId()));
            user.setAccount(account);
        });
        addDomainActions((AccountUpdated event) -> {
            Account account = new Account(
                    AccountId.of(event.getAccountId()),
                    AccountNumber.of(event.getAccountNumber()),
                    AccountType.of(event.getAccountType()),
                    Balance.of(event.getBalance()),
                    Status.of(event.getStatus()),
                    CustomerId.of(event.getCustomerId()));
            user.setAccount(account);
        });
        addDomainActions((CustomerCreated event) -> {
            Customer customer = new Customer(
                    CustomerId.of(event.getCustomerId()),
                    Address.of(event.getAddress()),
                    Email.of(event.getEmail()),
                    FirstName.of(event.getFirstName()),
                    LastName.of(event.getLastName()),
                    Phone.of(event.getPhone()),
                    Status.of(event.getStatus()));
            user.setCustomer(customer);
        });
        addDomainActions((CustomerUpdated event) -> {
            Customer customer = new Customer(
                    CustomerId.of(event.getCustomerId()),
                    Address.of(event.getAddress()),
                    Email.of(event.getEmail()),
                    FirstName.of(event.getFirstName()),
                    LastName.of(event.getLastName()),
                    Phone.of(event.getPhone()),
                    Status.of(event.getStatus()));
            user.setCustomer(customer);
        });
    }
}
