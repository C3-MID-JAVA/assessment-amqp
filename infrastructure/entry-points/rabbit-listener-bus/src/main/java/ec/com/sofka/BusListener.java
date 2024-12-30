package ec.com.sofka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ec.com.sofka.applogs.CreateLogUseCase;
import ec.com.sofka.gateway.BusMessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BusListener implements BusMessageListener {
    private final CreateLogUseCase createLogUseCase;
    private final RabbitProperties rabbitProperties;

    public BusListener(CreateLogUseCase createLogUseCase, RabbitProperties rabbitProperties) {
        this.createLogUseCase = createLogUseCase;
        this.rabbitProperties = rabbitProperties;
    }

    @Override
    @RabbitListener(queues = "#{@rabbitProperties.getAllQueues()}")
    public void receiveMsg(String payload) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> messageMap = mapper.readValue(payload, new TypeReference<>() {});
            String entity = messageMap.get("entity");
            String message = messageMap.get("message");

            createLogUseCase.accept(entity, message);
        } catch (JsonProcessingException e) {
            System.err.println("Error processing message payload: " + e.getMessage());
        }
    }
}
