package ec.com.sofka.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionRequestDTO {
    private int id;
    private BranchRequestDTO branch;
    private LocalDateTime date;
    private String type;
    private BigDecimal amount;
    private String description;
    private AccountRequestDTO destinationAccount;
    private AccountRequestDTO sourceAccount;
}
