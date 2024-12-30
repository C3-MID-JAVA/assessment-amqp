package ec.com.sofka;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String id;
    private BigDecimal balance;
    private String accountNumber;
    private String accountHolder;
    private List<Transaction> transactions = new ArrayList<>();

    public Account() {
    }

    public Account(String id, BigDecimal balance, String accountHolder, String accountNumber) {
        this.id = id;
        this.balance = balance;
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

}
