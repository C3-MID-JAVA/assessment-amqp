package ec.com.sofka;

//Cuidao, esto dio dependencia circular. Toca colocar por fin práctico los nombres como están en la clase RabbitConfig
//import ec.com.sofka.config.RabbitConfig;
//import ec.com.sofka.config.IRegisterRepository;
//import ec.com.sofka.data.RegisterEntity;

import ec.com.sofka.config.IRegisterRepository;
import ec.com.sofka.data.RegisterEntity;
import ec.com.sofka.gateway.BusMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


//11. BusMessage implementation, this is a service so, don't forget the annotation
/*
@Service
public class BusAdapter implements BusMessage {

    //13. Use of RabbitTemplate to define the sendMsg method
    private final RabbitTemplate rabbitTemplate;

    public BusAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMsg(String message) {
        //14. Calling the config done on app, but this must be managed through Environment Variables.
        rabbitTemplate.convertAndSend("example.exchange",
                "example.routingKey", //Here you can define a pattern for routing keys to be considered: example.**
                message);
    }
}
*/


import org.springframework.beans.factory.annotation.Value;
//@Repository
@Service
public class BusAdapter implements BusMessage {

    //13. Use of RabbitTemplate to define the sendMsg method
    private final RabbitTemplate rabbitTemplate;
    private final IRegisterRepository repository;


    @Value("${app.EXCHANGE_NAME}")
    private String exchangeName;

    @Value("${app.ROUTING_KEY}")
    private String routingKey;

    public BusAdapter(RabbitTemplate rabbitTemplate, IRegisterRepository repository) {
        this.rabbitTemplate = rabbitTemplate;
        this.repository = repository;
    }

    @Override
    public void sendMsg(String message) {
        //14. Calling the config done on app, but this must be managed through Environment Variables.
        RegisterEntity registro = new RegisterEntity(message);
        repository.save(registro).subscribe();
        System.out.println("Mensaje "+ message);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
