package ec.com.sofka.request;

import ec.com.sofka.response.CardResponseDTO;
import ec.com.sofka.response.TransactionResponseDTO;

import java.math.BigDecimal;
import java.util.List;

public class AccountRequestDTO {
    private int id;
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private List<CardResponseDTO> cards;
    private List<TransactionResponseDTO> transactions;

    public AccountRequestDTO(String accountNumber, String accountType, BigDecimal balance, List<CardResponseDTO> cards, int id, List<TransactionResponseDTO> transactions) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.cards = cards;
        this.id = id;
        this.transactions = transactions;
    }

    public AccountRequestDTO() {}

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<CardResponseDTO> getCards() {
        return cards;
    }

    public void setCards(List<CardResponseDTO> cards) {
        this.cards = cards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TransactionResponseDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionResponseDTO> transactions) {
        this.transactions = transactions;
    }
}