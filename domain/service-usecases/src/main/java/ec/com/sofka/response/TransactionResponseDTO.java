package ec.com.sofka.response;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponseDTO {
    private int id;
    private BranchResponseDTO branch;
    private LocalDateTime date;
    private String type;
    private BigDecimal amount;
    private String description;
    private AccountResponseDTO destinationAccount;
    private AccountResponseDTO sourceAccount;

    public TransactionResponseDTO(BigDecimal amount, BranchResponseDTO branch, LocalDateTime date, String description, AccountResponseDTO destinationAccount, int id, AccountResponseDTO sourceAccount, String type) {
        this.amount = amount;
        this.branch = branch;
        this.date = date;
        this.description = description;
        this.destinationAccount = destinationAccount;
        this.id = id;
        this.sourceAccount = sourceAccount;
        this.type = type;
    }

    public TransactionResponseDTO() {}

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BranchResponseDTO getBranch() {
        return branch;
    }

    public void setBranch(BranchResponseDTO branch) {
        this.branch = branch;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AccountResponseDTO getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(AccountResponseDTO destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccountResponseDTO getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(AccountResponseDTO sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
