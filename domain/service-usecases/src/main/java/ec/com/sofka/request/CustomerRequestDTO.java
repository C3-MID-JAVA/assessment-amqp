package ec.com.sofka.request;

import java.util.List;

public class CustomerRequestDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private List<AccountRequestDTO> accounts;

    public CustomerRequestDTO(List<AccountRequestDTO> accounts, String address, String email, String firstName, int id, String lastName, String phone) {
        this.accounts = accounts;
        this.address = address;
        this.email = email;
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.phone = phone;
    }

    public CustomerRequestDTO() {}

    public List<AccountRequestDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountRequestDTO> accounts) {
        this.accounts = accounts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
