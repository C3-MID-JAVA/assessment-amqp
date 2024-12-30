package co.com.sofkau;


import co.com.sofkau.applogs.PrintLogUseCase;
import co.com.sofkau.gateway.BusMessageListener;
import co.com.sofkau.transaction.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;


@Service
public class BusListener implements BusMessageListener {
    private final PrintLogUseCase printLogUseCase;
    private final RabbitEnvProperties envProperties;

    public BusListener(PrintLogUseCase printLogUseCase, RabbitEnvProperties envProperties) {
        this.printLogUseCase = printLogUseCase;
        this.envProperties = envProperties;
    }

    @Override
    @RabbitListener(queues = "#{T(java.util.Arrays).asList(@rabbitEnvProperties.getAllQueues())}")
    public void receiveMsg(String payload) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.registerSubtypes(AtmTransaction.class, AccountDeposit.class, BranchDeposit.class,
                PaymentStoreTransaction.class, PaymentWebTransaction.class);
        try {
            Map<String, Object> messageMap = mapper.readValue(payload, new TypeReference<Map<String, Object>>() {});
            String group = (String) messageMap.get("group");
            String message = (String) messageMap.get("message");
            String level = (String) messageMap.get("level");
            Object transactionData = messageMap.get("transaction");

            Transaction transaction = null;

            if (transactionData != null && !(transactionData instanceof Map && ((Map<?, ?>) transactionData).isEmpty())) {
                JsonNode transactionNode = mapper.valueToTree(transactionData);
                String transactionType = transactionNode.get("transactionType") != null ? transactionNode.get("transactionType").asText() : null;

                if (transactionType != null) {
                    switch (transactionType) {
                        case ConstansTrType.ATM:
                            transaction = mapper.treeToValue(transactionNode, AtmTransaction.class);
                            break;
                        case ConstansTrType.BETWEEN_ACCOUNT:
                            transaction = mapper.treeToValue(transactionNode, AccountDeposit.class);
                            break;
                        case ConstansTrType.STORE_PURCHASE:
                            transaction = mapper.treeToValue(transactionNode, PaymentStoreTransaction.class);
                            break;
                        case ConstansTrType.WEB_PURCHASE:
                            transaction = mapper.treeToValue(transactionNode, PaymentWebTransaction.class);
                            break;
                        case ConstansTrType.BRANCH_DEPOSIT:
                            transaction = mapper.treeToValue(transactionNode, BranchDeposit.class);
                            break;
                        default:
                            System.err.println("Unknown transaction type: " + transactionType);
                    }
                }
            }


            Log log = new Log(message,group, level,transaction, LocalDateTime.now());

            printLogUseCase.apply(log).subscribe();
        } catch (JsonProcessingException e) {
            System.err.println("ERROR processing message: " + e.getMessage());
        }
    }
}
