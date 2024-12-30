package ec.com.sofka.data;

import java.math.BigDecimal;

public class ResponseDTO {
    public String id;
    public String accountHolder;
    public BigDecimal balance;

    public ResponseDTO(String id, String accountOwner, BigDecimal balance) {
        this.id = id;
        this.accountHolder = accountOwner;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
