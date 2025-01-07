package ec.com.sofka.customer;

import ec.com.sofka.customer.value.CustomerId;
import ec.com.sofka.customer.value.object.*;
import ec.com.sofka.generic.object.Status;
import ec.com.sofka.generic.util.Entity;

public class Customer extends Entity<CustomerId> {
    private FirstName firstName;
    private LastName lastName;
    private Email email;
    private Phone phone;
    private Address address;
    private Status status;

    public Customer(CustomerId id, Address address, Email email, FirstName firstName, LastName lastName, Phone phone, Status status) {
        super(id);
        this.address = address;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.status = status;
    }

    protected Customer(CustomerId id) {
        super(id);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public FirstName getFirstName() {
        return firstName;
    }

    public void setFirstName(FirstName firstName) {
        this.firstName = firstName;
    }

    public LastName getLastName() {
        return lastName;
    }

    public void setLastName(LastName lastName) {
        this.lastName = lastName;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
