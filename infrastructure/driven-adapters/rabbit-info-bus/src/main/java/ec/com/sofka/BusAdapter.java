package ec.com.sofka;

//Cuidao, esto dio dependencia circular. Toca colocar por fin práctico los nombres como están en la clase RabbitConfig
//import ec.com.sofka.config.RabbitConfig;
import ec.com.sofka.gateway.BusMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


//11. BusMessage implementation, this is a service so, don't forget the annotation
@Service
public class BusAdapter implements BusMessage {

    // Valores de configuración leídos desde las propiedades del entorno o el archivo application.properties
    @Value("${rabbitmq.exchangeName}")
    private String exchangeName;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;


    //13. Use of RabbitTemplate to define the sendMsg method
    private final RabbitTemplate rabbitTemplate;

    public BusAdapter(RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMsg(String message) {
        //14. Calling the config done on app, but this must be managed through Environment Variables.
        rabbitTemplate.convertAndSend(
                exchangeName,
                routingKey,
                message
        );
    }

}
