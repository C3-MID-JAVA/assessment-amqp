package co.com.sofkau.gateway;

import co.com.sofkau.Log;
import co.com.sofkau.transaction.Transaction;

public interface BusMessage {

    void sendMsg(Log log);
}
