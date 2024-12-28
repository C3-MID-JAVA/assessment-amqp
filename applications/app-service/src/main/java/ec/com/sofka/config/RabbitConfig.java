package ec.com.sofka.config;

import ec.com.sofka.RabbitProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private final RabbitProperties rabbitProperties;

    public RabbitConfig(RabbitProperties rabbitProperties) {
        this.rabbitProperties = rabbitProperties;
    }


    @Bean
    public TopicExchange userExchange() {
        return new TopicExchange(rabbitProperties.getUserExchange());
    }

    @Bean
    public Queue userQueue() {
        return new Queue(rabbitProperties.getUserQueue(), true);
    }

    @Bean
    public Binding userBinding() {
        return BindingBuilder.bind(userQueue())
                .to(userExchange())
                .with(rabbitProperties.getUserRoutingKey());
    }

    @Bean
    public TopicExchange accountExchange() {
        return new TopicExchange(rabbitProperties.getAccountExchange());
    }

    @Bean
    public Queue accountQueue() {
        return new Queue(rabbitProperties.getAccountQueue(), true);
    }

    @Bean
    public Binding accountBinding() {
        return BindingBuilder.bind(accountQueue())
                .to(accountExchange())
                .with(rabbitProperties.getAccountRoutingKey());
    }

    @Bean
    public TopicExchange transactionExchange() {
        return new TopicExchange(rabbitProperties.getTransactionExchange());
    }

    @Bean
    public Queue transactionQueue() {
        return new Queue(rabbitProperties.getTransactionQueue(), true);
    }

    @Bean
    public Binding transactionBinding() {
        return BindingBuilder.bind(transactionQueue())
                .to(transactionExchange())
                .with(rabbitProperties.getTransactionRoutingKey());
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializeBeans(AmqpAdmin amqpAdmin) {
        return event -> {
            amqpAdmin.declareExchange(userExchange());
            amqpAdmin.declareQueue(userQueue());
            amqpAdmin.declareBinding(userBinding());

            amqpAdmin.declareExchange(accountExchange());
            amqpAdmin.declareQueue(accountQueue());
            amqpAdmin.declareBinding(accountBinding());

            amqpAdmin.declareExchange(transactionExchange());
            amqpAdmin.declareQueue(transactionQueue());
            amqpAdmin.declareBinding(transactionBinding());
        };
    }
}
