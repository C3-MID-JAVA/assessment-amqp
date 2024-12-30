package co.com.sofkau.config;

import co.com.sofkau.RabbitEnvProperties;
import org.springframework.amqp.core.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    private final RabbitEnvProperties envProperties;

    public RabbitConfig(RabbitEnvProperties envProperties) {
        this.envProperties = envProperties;
    }

    @Bean
    public TopicExchange accountExchange() {
        return new TopicExchange(envProperties.getAccountExchange(), true, false);
    }


    @Bean
    public Queue accountQueue() {
        return new Queue(envProperties.getAccountQueue(), true);
    }

    @Bean
    public Binding accountBinding() {
        return BindingBuilder.bind(accountQueue())
                .to(accountExchange())
                .with(envProperties.getAccountRoutingKey());
    }

    @Bean
    public TopicExchange cardExchange() {
        return new TopicExchange(envProperties.getCardExchange(), true, false);
    }


    @Bean
    public Queue cardQueue() {
        return new Queue(envProperties.getCardQueue(), true);
    }

    @Bean
    public Binding cardBinding() {
        return BindingBuilder.bind(cardQueue())
                .to(cardExchange())
                .with(envProperties.getCardRoutingKey());
    }

    @Bean
    public TopicExchange transactionExchange() {
        return new TopicExchange(envProperties.getTransactionExchange(), true, false);
    }


    @Bean
    public Queue transactionQueue() {
        return new Queue(envProperties.getTransactionQueue(), true);
    }

    @Bean
    public Binding transactionBinding() {
        return BindingBuilder.bind(transactionQueue())
                .to(transactionExchange())
                .with(envProperties.getTransactionRoutingKey());
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializeBeans(AmqpAdmin amqpAdmin) {
        return event -> {


            amqpAdmin.declareExchange(accountExchange());
            amqpAdmin.declareQueue(accountQueue());
            amqpAdmin.declareBinding(accountBinding());

            amqpAdmin.declareExchange(cardExchange());
            amqpAdmin.declareQueue(cardQueue());
            amqpAdmin.declareBinding(cardBinding());

            amqpAdmin.declareExchange(transactionExchange());
            amqpAdmin.declareQueue(transactionQueue());
            amqpAdmin.declareBinding(transactionBinding());
        };
    }

}
