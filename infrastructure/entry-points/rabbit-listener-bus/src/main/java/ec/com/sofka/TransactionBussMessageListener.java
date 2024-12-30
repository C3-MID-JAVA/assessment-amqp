package ec.com.sofka;

import ec.com.sofka.gateway.BusMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TransactionBussMessageListener implements BusMessageListener {

    @Value("amqp.exchange.transaction")
    private String exchangeName;

    @Value("${amqp.routing.key.transaction}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public TransactionBussMessageListener( RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void receiveMsg(String message) {

        rabbitTemplate.convertAndSend(
                exchangeName,
                routingKey,
                message
        );
    }

}
