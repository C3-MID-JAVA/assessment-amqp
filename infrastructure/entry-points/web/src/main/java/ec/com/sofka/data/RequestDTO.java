package ec.com.sofka.data;

import java.math.BigDecimal;

public class RequestDTO {
    public String accountHolder;
    public String account;
    public BigDecimal balance;

    public RequestDTO(String accountHolder, String account, BigDecimal balance) {
        this.accountHolder = accountHolder;
        this.account = account;
        this.balance = balance;
    }


    public String getCustomer() {
        return accountHolder;
    }

    public void setCustomer(String owner) {
        this.accountHolder = owner;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
