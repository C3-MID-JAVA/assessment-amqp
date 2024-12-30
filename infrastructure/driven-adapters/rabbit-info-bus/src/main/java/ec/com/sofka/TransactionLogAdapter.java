package ec.com.sofka;

import com.fasterxml.jackson.core.JsonProcessingException;
import ec.com.sofka.gateway.TransactionBusMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TransactionLogAdapter implements TransactionBusMessage {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Value("${exchange.name}")
    private String exchangeName;
    @Value("${routing.key}")
    private String routingKey;

    public TransactionLogAdapter(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendMsg(TransactionLog log) {
        try {
            String message = objectMapper.writeValueAsString(log);
            if ("ATM_WITHDRAWAL".equalsIgnoreCase(log.getType().toString()) || "WEB_PURCHASE".equalsIgnoreCase(log.getType().toString()) || "PHYSICAL_PURCHASE".equalsIgnoreCase(log.getType().toString())) {
                rabbitTemplate.convertAndSend(exchangeName, routingKey + ".withdrawal", message);
            } else if ("BRANCH_DEPOSIT".equalsIgnoreCase(log.getType().toString()) || "ATM_DEPOSIT".equalsIgnoreCase(log.getType().toString()) || "TRANSFER_DEPOSIT".equalsIgnoreCase(log.getType().toString())) {
                rabbitTemplate.convertAndSend(exchangeName, routingKey + ".deposit", message);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize transaction log", e);
        }


    }
}
