package ec.com.sofka.branch;

import ec.com.sofka.branch.value.BranchId;
import ec.com.sofka.branch.value.object.Address;
import ec.com.sofka.branch.value.object.Name;
import ec.com.sofka.branch.value.object.Phone;
import ec.com.sofka.generic.object.Status;
import ec.com.sofka.generic.util.Entity;

public class Branch extends Entity<BranchId> {
    private Name name;
    private Address address;
    private Phone phone;
    private Status status;

    public Branch(BranchId id, Address address, Name name, Phone phone, Status status) {
        super(id);
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.status = status;
    }

    protected Branch(BranchId id) {
        super(id);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
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
