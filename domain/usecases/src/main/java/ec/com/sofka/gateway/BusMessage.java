package ec.com.sofka.gateway;

public interface BusMessage {
    void sendMsg(String entity, String message);
}
