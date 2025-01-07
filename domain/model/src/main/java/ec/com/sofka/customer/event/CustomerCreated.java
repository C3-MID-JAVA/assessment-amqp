package ec.com.sofka.customer.event;

import ec.com.sofka.generic.domain.DomainEvent;

public class CustomerCreated extends DomainEvent {
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String status;

    public CustomerCreated(String eventType, String address, String customerId, String email, String firstName, String lastName, String phone, String status) {
        super(eventType);
        this.address = address;
        this.customerId = customerId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.status = status;
    }

    public CustomerCreated(String eventType) {
        super(eventType);
    }

    public String getAddress() {
        return address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }
}
