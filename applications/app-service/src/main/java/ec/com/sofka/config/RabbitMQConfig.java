package ec.com.sofka.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//2. Create class to configure RabbitMQ here in app layer: To generate beans
@Configuration
public class RabbitMQConfig {
    //3. Configurations: Environment variables - Names must follow a pattern like this
    // Each queue must have its own name - Broker admin must create this and provide us as info to connect later
    @Value("${exchange.name}")
    private String EXCHANGE_NAME;
    @Value("${deposit.queue.name}")
    private String DEPOSIT_QUEUE;
    @Value("${withdrawal.queue.name}")
    private String WITHDRAWAL_QUEUE;
    @Value("${routing.key}")
    private String ROUTING_KEY;

    //4. Exchange configuration
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    //5. Queue configuration: As many queues you have - ofc you can have more than one and each one must have its proper name
    //2nd param here: durable - Queue will survive a broker restart

    //Modified to get 2 queues for DEPOSITS and WITHDRAWALS
    @Bean
    public Queue withdrawalQueue() {
        return new Queue(WITHDRAWAL_QUEUE, true);
    }

    @Bean
    public Queue depositQueue() {
        return new Queue(DEPOSIT_QUEUE, true);
    }

    //6. Binding configuration: Connects queue with exchange - As many bindings as queues I have
    @Bean
    public Binding bindingWithdrawalQueue(Queue withdrawalQueue, TopicExchange transactionExchange) {
        return BindingBuilder.bind(withdrawalQueue).to(transactionExchange).with(ROUTING_KEY + ".withdrawal");
    }

    @Bean
    public Binding bindingDepositQueue(Queue depositQueue, TopicExchange transactionExchange) {
        return BindingBuilder.bind(depositQueue).to(transactionExchange).with(ROUTING_KEY + ".deposit");
    }

}
