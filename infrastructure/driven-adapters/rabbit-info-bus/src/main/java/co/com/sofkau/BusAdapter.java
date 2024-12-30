package co.com.sofkau;


import co.com.sofkau.gateway.BusMessage;
import co.com.sofkau.transaction.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class BusAdapter implements BusMessage {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitEnvProperties envProperties;

    public BusAdapter(RabbitTemplate rabbitTemplate, RabbitEnvProperties envProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.envProperties = envProperties;
    }

    @Override
    public void sendMsg(Log log) {
        String exchange = switch (log.getGroup()) {
            case "card" -> envProperties.getCardExchange();
            case "account" -> envProperties.getAccountExchange();
            case "transaction" -> envProperties.getTransactionExchange();
            default -> throw new IllegalArgumentException("Invalid group type: " + log.getGroup());
        };

        String routingKey = switch (log.getGroup()) {
            case "card" -> envProperties.getCardRoutingKey();
            case "account" -> envProperties.getAccountRoutingKey();
            case "transaction" -> envProperties.getTransactionRoutingKey();
            default -> throw new IllegalArgumentException("Invalid group type: " + log.getGroup());
        };

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerSubtypes(AtmTransaction.class, BranchDeposit.class,
                PaymentWebTransaction.class, PaymentStoreTransaction.class, AccountDeposit.class);
        mapper.registerModule(new JavaTimeModule());

        try {
            Map<String, Object> transactionData = log.getTransaction() != null
                    ? mapper.convertValue(log.getTransaction(), new TypeReference<Map<String, Object>>() {})
                    : new HashMap<>();;


            String payload = mapper.writeValueAsString(Map.of(
                    "group", log.getGroup(),
                    "message", log.getMessage(),
                    "level", log.getLevel(),
                    "transaction", transactionData
            ));
            rabbitTemplate.convertAndSend(exchange, routingKey, payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error creating payload", e);
        }
    }
}
