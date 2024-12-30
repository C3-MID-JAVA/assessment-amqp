package ec.com.sofka;

import ec.com.sofka.applogs.PrintLogUseCase;
import ec.com.sofka.applogs.RegisterLogUseCase;
import ec.com.sofka.gateway.BusMessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

//20. Create the BusListener class
@Service
public class BusListener implements BusMessageListener{

    private final PrintLogUseCase printLogUseCase;
    private final RegisterLogUseCase registerLogUseCase;
    public BusListener(PrintLogUseCase printLogUseCase, RegisterLogUseCase registerLogUseCase) {
        this.printLogUseCase = printLogUseCase;
        this.registerLogUseCase = registerLogUseCase;
    }
    //23. Implement the receiveMsg method with the usecase
    @Override
    @RabbitListener(queues = "${rabbitmq.queueName}")
    public void receiveMsg(String message) {

        Log logMono = registerLogUseCase.apply(new Log(message, LocalDateTime.now()));
            printLogUseCase.accept("recibido : " + message);
    }
}
