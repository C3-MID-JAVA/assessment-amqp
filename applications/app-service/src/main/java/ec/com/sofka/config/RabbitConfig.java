package ec.com.sofka.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//2. Create class to configure RabbitMQ here in app layer: To generate beans
@Configuration
public class RabbitConfig {

    //3. Configurations: Environment variables - Names must follow a pattern like this - CAMBIAR A UN PATRON PARA EL ASSESSMENT
    // Each queue must have its own name - Broker admin must create this and provide us as info to connect later

//    public static final String EXCHANGE_NAME = "example.exchange";
//    public static final String QUEUE_NAME = "example.queue";
//    public static final String ROUTING_KEY = "example.routingKey";

    // Inyectar las variables de entorno con @Value
    @Value("${rabbitmq.exchangeName}")
    public String EXCHANGE_NAME;

    @Value("${rabbitmq.queueName}")
    public String QUEUE_NAME;

    @Value("${rabbitmq.routingKey}")
    public String ROUTING_KEY;

    @Value("${amqp.exchange.transaction}")
    private String EXCHANGE_TRANSACTION;
    @Value("${amqp.queue.transaction}")
    private String QUEUE_TRANSACTION;
    @Value("${amqp.routing.key.transaction}")
    private String ROUTINGKEY_TRANSACTION;


    // Configuración del Exchange

    //4. Exchange configuration
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    //5. Queue configuration: As many queues you have - ofc you can have more than one and each one must have its proper name
    //2nd param here: durable - Queue will survive a broker restart
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    //6. Binding configuration: Connects queue with exchange - As many bindings as queues I have
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean(name = "transactionExchange")
    public TopicExchange transactionExchange() {
        return new TopicExchange(EXCHANGE_TRANSACTION);
    }

    @Bean(name = "transactionQueue")
    public Queue transactionQueue() {
        return new Queue(QUEUE_TRANSACTION, true);
    }

    @Bean
    public Binding transactionBinding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_TRANSACTION);
    }


}
