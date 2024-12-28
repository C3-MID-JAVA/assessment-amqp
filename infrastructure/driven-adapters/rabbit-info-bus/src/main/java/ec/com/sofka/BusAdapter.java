package ec.com.sofka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ec.com.sofka.gateway.BusMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class BusAdapter implements BusMessage {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitProperties rabbitProperties;

    public BusAdapter(RabbitTemplate rabbitTemplate, RabbitProperties rabbitProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitProperties = rabbitProperties;
    }

    @Override
    public void sendMsg(String entity, String message) {
        String exchange = switch (entity) {
            case "user" -> rabbitProperties.getUserExchange();
            case "account" -> rabbitProperties.getAccountExchange();
            case "transaction" -> rabbitProperties.getTransactionExchange();
            default -> throw new IllegalArgumentException("Invalid entity type: " + entity);
        };

        String routingKey = switch (entity) {
            case "user" -> rabbitProperties.getUserRoutingKey();
            case "account" -> rabbitProperties.getAccountRoutingKey();
            case "transaction" -> rabbitProperties.getTransactionRoutingKey();
            default -> throw new IllegalArgumentException("Invalid entity type: " + entity);
        };

        ObjectMapper mapper = new ObjectMapper();

        try {
            String payload = mapper.writeValueAsString(Map.of(
                    "entity", entity,
                    "message", message
            ));
            rabbitTemplate.convertAndSend(exchange, routingKey, payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error creating JSON payload", e);
        }
    }
}
