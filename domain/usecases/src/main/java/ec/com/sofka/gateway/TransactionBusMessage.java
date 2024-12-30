package ec.com.sofka.gateway;

import ec.com.sofka.TransactionLog;

public interface TransactionBusMessage {
    void sendMsg(TransactionLog log);
}
