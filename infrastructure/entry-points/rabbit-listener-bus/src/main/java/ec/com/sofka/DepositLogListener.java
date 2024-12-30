package ec.com.sofka;

import ec.com.sofka.applogs.SaveTransactionLogUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DepositLogListener {
    private final SaveTransactionLogUseCase saveTransactionLogUseCase;
    private final ObjectMapper objectMapper;

    public DepositLogListener(SaveTransactionLogUseCase saveTransactionLogUseCase, ObjectMapper objectMapper) {
        this.saveTransactionLogUseCase = saveTransactionLogUseCase;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "${deposit.queue.name}")
    public void onDepositMessage(String message) {
        try {
            TransactionLog log = objectMapper.readValue(message, TransactionLog.class);

            saveTransactionLogUseCase.saveLog(log)
                    .doOnError(error -> System.err.println("Failed to save deposit log: " + error.getMessage()))
                    .subscribe();
        } catch (Exception e) {
            System.err.println("Failed to process deposit message: " + e.getMessage());
        }
    }
}
