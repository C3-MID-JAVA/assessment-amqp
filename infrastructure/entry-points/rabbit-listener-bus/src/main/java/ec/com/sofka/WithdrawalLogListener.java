package ec.com.sofka;

import ec.com.sofka.applogs.SaveTransactionLogUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WithdrawalLogListener {
    private final SaveTransactionLogUseCase saveTransactionLogUseCase;
    private final ObjectMapper objectMapper;

    public WithdrawalLogListener(SaveTransactionLogUseCase saveTransactionLogUseCase, ObjectMapper objectMapper) {
        this.saveTransactionLogUseCase = saveTransactionLogUseCase;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "${withdrawal.queue.name}")
    public void onWithdrawalMessage(String message) {
        try {
            TransactionLog log = objectMapper.readValue(message, TransactionLog.class);

            saveTransactionLogUseCase.saveLog(log)
                    .doOnError(error -> System.err.println("Failed to save withdrawal log: " + error.getMessage()))
                    .subscribe();
        } catch (Exception e) {
            System.err.println("Failed to process deposit message: " + e.getMessage());
        }
    }
}
