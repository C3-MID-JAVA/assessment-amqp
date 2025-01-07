package ec.com.sofka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application-global.properties")
public class EnvProperties {

    @Value("${transfer.exchange.name}")
    private String transferExchangeName;

    @Value("${branch.transfer.queue.name}")
    private String branchTransferQueueName;
    @Value("${another.account.deposit.queue.name}")
    private String anotherAccountDepositQueueName;
    @Value("${store.card.purchase.queue.name}")
    private String storeCardPurchaseQueueName;
    @Value("${online.card.purchase.queue.name}")
    private String onlineCardPurchaseQueueName;
    @Value("${atm.withdrawal.queue.name}")
    private String atmWithdrawalQueueName;
    @Value("${atm.deposit.queue.name}")
    private String atmDepositQueueName;

    @Value("${branch.transfer.routing.key}")
    private String branchTransferRoutingKey;
    @Value("${another.account.deposit.routing.key}")
    private String anotherAccountDepositRoutingKey;
    @Value("${store.card.purchase.routing.key}")
    private String storeCardPurchaseRoutingKey;
    @Value("${online.card.purchase.routing.key}")
    private String onlineCardPurchaseRoutingKey;
    @Value("${atm.withdrawal.routing.key}")
    private String atmWithdrawalRoutingKey;
    @Value("${atm.deposit.routing.key}")
    private String atmDepositRoutingKey;

    public String getAnotherAccountDepositQueueName() {
        return anotherAccountDepositQueueName;
    }

    public void setAnotherAccountDepositQueueName(String anotherAccountDepositQueueName) {
        this.anotherAccountDepositQueueName = anotherAccountDepositQueueName;
    }

    public String getAnotherAccountDepositRoutingKey() {
        return anotherAccountDepositRoutingKey;
    }

    public void setAnotherAccountDepositRoutingKey(String anotherAccountDepositRoutingKey) {
        this.anotherAccountDepositRoutingKey = anotherAccountDepositRoutingKey;
    }

    public String getAtmDepositQueueName() {
        return atmDepositQueueName;
    }

    public void setAtmDepositQueueName(String atmDepositQueueName) {
        this.atmDepositQueueName = atmDepositQueueName;
    }

    public String getAtmDepositRoutingKey() {
        return atmDepositRoutingKey;
    }

    public void setAtmDepositRoutingKey(String atmDepositRoutingKey) {
        this.atmDepositRoutingKey = atmDepositRoutingKey;
    }

    public String getAtmWithdrawalQueueName() {
        return atmWithdrawalQueueName;
    }

    public void setAtmWithdrawalQueueName(String atmWithdrawalQueueName) {
        this.atmWithdrawalQueueName = atmWithdrawalQueueName;
    }

    public String getAtmWithdrawalRoutingKey() {
        return atmWithdrawalRoutingKey;
    }

    public void setAtmWithdrawalRoutingKey(String atmWithdrawalRoutingKey) {
        this.atmWithdrawalRoutingKey = atmWithdrawalRoutingKey;
    }

    public String getBranchTransferQueueName() {
        return branchTransferQueueName;
    }

    public void setBranchTransferQueueName(String branchTransferQueueName) {
        this.branchTransferQueueName = branchTransferQueueName;
    }

    public String getBranchTransferRoutingKey() {
        return branchTransferRoutingKey;
    }

    public void setBranchTransferRoutingKey(String branchTransferRoutingKey) {
        this.branchTransferRoutingKey = branchTransferRoutingKey;
    }

    public String getOnlineCardPurchaseQueueName() {
        return onlineCardPurchaseQueueName;
    }

    public void setOnlineCardPurchaseQueueName(String onlineCardPurchaseQueueName) {
        this.onlineCardPurchaseQueueName = onlineCardPurchaseQueueName;
    }

    public String getOnlineCardPurchaseRoutingKey() {
        return onlineCardPurchaseRoutingKey;
    }

    public void setOnlineCardPurchaseRoutingKey(String onlineCardPurchaseRoutingKey) {
        this.onlineCardPurchaseRoutingKey = onlineCardPurchaseRoutingKey;
    }

    public String getStoreCardPurchaseQueueName() {
        return storeCardPurchaseQueueName;
    }

    public void setStoreCardPurchaseQueueName(String storeCardPurchaseQueueName) {
        this.storeCardPurchaseQueueName = storeCardPurchaseQueueName;
    }

    public String getStoreCardPurchaseRoutingKey() {
        return storeCardPurchaseRoutingKey;
    }

    public void setStoreCardPurchaseRoutingKey(String storeCardPurchaseRoutingKey) {
        this.storeCardPurchaseRoutingKey = storeCardPurchaseRoutingKey;
    }

    public String getTransferExchangeName() {
        return transferExchangeName;
    }

    public void setTransferExchangeName(String transferExchangeName) {
        this.transferExchangeName = transferExchangeName;
    }
}