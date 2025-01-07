package ec.com.sofka.config;

import ec.com.sofka.EnvProperties;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private final EnvProperties envProperties;

    public RabbitConfig(EnvProperties envProperties) {
        this.envProperties = envProperties;
    }

    @Bean
    public TopicExchange transferExchange() {
        return new TopicExchange(envProperties.getTransferExchangeName());
    }

    @Bean
    public TopicExchange anotherAccountDepositExchange() {
        return new TopicExchange(envProperties.getTransferExchangeName());
    }

    @Bean
    public TopicExchange storeCardPurchaseExchange() {
        return new TopicExchange(envProperties.getTransferExchangeName());
    }

    @Bean
    public TopicExchange onlineCardPurchaseExchange() {
        return new TopicExchange(envProperties.getTransferExchangeName());
    }

    @Bean
    public TopicExchange atmWithdrawalExchange() {
        return new TopicExchange(envProperties.getTransferExchangeName());
    }

    @Bean
    public TopicExchange atmDepositExchange() {
        return new TopicExchange(envProperties.getTransferExchangeName());
    }

    @Bean
    public Queue branchTransferQueue() {
        return new Queue(envProperties.getBranchTransferQueueName(), true);
    }

    @Bean
    public Queue anotherAccountDepositQueue() {
        return new Queue(envProperties.getAnotherAccountDepositQueueName(), true);
    }

    @Bean
    public Queue storeCardPurchaseQueue() {
        return new Queue(envProperties.getStoreCardPurchaseQueueName(), true);
    }

    @Bean
    public Queue onlineCardPurchaseQueue() {
        return new Queue(envProperties.getOnlineCardPurchaseQueueName(), true);
    }

    @Bean
    public Queue atmWithdrawalQueue() {
        return new Queue(envProperties.getAtmWithdrawalQueueName(), true);
    }

    @Bean
    public Queue atmDepositQueue() {
        return new Queue(envProperties.getAtmDepositQueueName(), true);
    }

    @Bean
    public Binding branchTransferBinding(Queue branchTransferQueue, TopicExchange transferExchange) {
        return BindingBuilder.bind(branchTransferQueue).to(transferExchange)
                .with(envProperties.getBranchTransferRoutingKey());
    }

    @Bean
    public Binding anotherAccountDepositBinding(Queue anotherAccountDepositQueue, TopicExchange transferExchange) {
        return BindingBuilder.bind(anotherAccountDepositQueue).to(transferExchange)
                .with(envProperties.getAnotherAccountDepositRoutingKey());
    }

    @Bean
    public Binding storeCardPurchaseBinding(Queue storeCardPurchaseQueue, TopicExchange transferExchange) {
        return BindingBuilder.bind(storeCardPurchaseQueue).to(transferExchange)
                .with(envProperties.getStoreCardPurchaseRoutingKey());
    }

    @Bean
    public Binding onlineCardPurchaseBinding(Queue onlineCardPurchaseQueue, TopicExchange transferExchange) {
        return BindingBuilder.bind(onlineCardPurchaseQueue).to(transferExchange)
                .with(envProperties.getOnlineCardPurchaseRoutingKey());
    }

    @Bean
    public Binding atmWithdrawalBinding(Queue atmWithdrawalQueue, TopicExchange transferExchange) {
        return BindingBuilder.bind(atmWithdrawalQueue).to(transferExchange)
                .with(envProperties.getAtmWithdrawalRoutingKey());
    }

    @Bean
    public Binding atmDepositBinding(Queue atmDepositQueue, TopicExchange transferExchange) {
        return BindingBuilder.bind(atmDepositQueue).to(transferExchange)
                .with(envProperties.getAtmDepositRoutingKey());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
