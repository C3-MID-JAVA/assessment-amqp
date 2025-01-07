package ec.com.sofka.data.request;

import ec.com.sofka.request.AccountRequestDTO;
import ec.com.sofka.request.BranchRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
