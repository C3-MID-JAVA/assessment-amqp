package ec.com.sofka.branch.event;

import ec.com.sofka.generic.domain.DomainEvent;

import java.time.LocalDateTime;

public class BranchCreated extends DomainEvent {
    private String branchId;
    private String name;
    private String address;
    private String phone;
    private String status;

    public BranchCreated(String eventType, String address, String branchId, String name, String phone, String status) {
        super(eventType);
        this.address = address;
        this.branchId = branchId;
        this.name = name;
        this.phone = phone;
        this.status = status;
    }

    public BranchCreated(String eventType) {
        super(eventType);
    }

    public String getAddress() {
        return address;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }
}
