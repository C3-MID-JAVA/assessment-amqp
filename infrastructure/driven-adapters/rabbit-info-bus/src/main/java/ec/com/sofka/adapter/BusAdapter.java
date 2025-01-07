package ec.com.sofka.adapter;

import ec.com.sofka.EnvProperties;
import ec.com.sofka.log.TransactionLog;
import ec.com.sofka.gateway.BusMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class BusAdapter implements BusMessage {

    private final RabbitTemplate rabbitTemplate;
    private final EnvProperties envProperties;

    public BusAdapter(RabbitTemplate rabbitTemplate, EnvProperties envProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.envProperties = envProperties;
    }

    @Override
    public Mono<Void> sendMsg(Mono<TransactionLog> monoLog) {
        return monoLog.flatMap(log ->
                Mono.fromCallable(() -> {
                            rabbitTemplate.convertAndSend(
                                    envProperties.getTransferExchangeName(),
                                    switch (log.getType()) {
                                        case "branch_transfer" -> envProperties.getBranchTransferRoutingKey();
                                        case "another_account_deposit" -> envProperties.getAnotherAccountDepositRoutingKey();
                                        case "store_card_purchase" -> envProperties.getStoreCardPurchaseRoutingKey();
                                        case "online_card_purchase" -> envProperties.getOnlineCardPurchaseRoutingKey();
                                        case "atm_withdrawal" -> envProperties.getAtmWithdrawalRoutingKey();
                                        case "atm_deposit" -> envProperties.getAtmDepositRoutingKey();
                                        default -> throw new IllegalArgumentException("Invalid group type: " + log.getType());
                                    },
                                    log
                            );
                            return log;
                        })
                        .then()
        );
    }
}