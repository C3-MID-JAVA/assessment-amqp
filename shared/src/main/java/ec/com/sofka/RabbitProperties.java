package ec.com.sofka;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:rabbit-config.properties")
public class RabbitProperties {

    @Value("${user.exchange.name}")
    private String userExchange;

    @Value("${user.queue.name}")
    private String userQueue;

    @Value("${user.routing.key}")
    private String userRoutingKey;

    @Value("${account.exchange.name}")
    private String accountExchange;

    @Value("${account.queue.name}")
    private String accountQueue;

    @Value("${account.routing.key}")
    private String accountRoutingKey;

    @Value("${transaction.exchange.name}")
    private String transactionExchange;

    @Value("${transaction.queue.name}")
    private String transactionQueue;

    @Value("${transaction.routing.key}")
    private String transactionRoutingKey;

    public String getUserExchange() {
        return userExchange;
    }

    public String getUserQueue() {
        return userQueue;
    }

    public String getUserRoutingKey() {
        return userRoutingKey;
    }

    public String getAccountExchange() {
        return accountExchange;
    }

    public String getAccountQueue() {
        return accountQueue;
    }

    public String getAccountRoutingKey() {
        return accountRoutingKey;
    }

    public String getTransactionExchange() {
        return transactionExchange;
    }

    public String getTransactionQueue() {
        return transactionQueue;
    }

    public String getTransactionRoutingKey() {
        return transactionRoutingKey;
    }

    public String[] getAllQueues(){
        return new String[] {getUserQueue(), getAccountQueue(), getTransactionQueue()};
    }
}

