package ec.com.sofka.gateway;

public interface TransactionBusMessageListener {
    void receiveMsg(String message);
}
