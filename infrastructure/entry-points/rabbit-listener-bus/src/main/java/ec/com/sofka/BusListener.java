/*package ec.com.sofka;

import ec.com.sofka.applogs.PrintLogUseCase;
import ec.com.sofka.gateway.BusMessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//20. Create the BusListener class
@Service
public class BusListener implements BusMessageListener{
    private final PrintLogUseCase printLogUseCase;

    public BusListener(PrintLogUseCase printLogUseCase) {
        this.printLogUseCase = printLogUseCase;
    }
    //23. Implement the receiveMsg method with the usecase
    @Override
    @RabbitListener(queues = "example.queue")
    public void receiveMsg(String message) {
        printLogUseCase.accept(message);
    }
}
*/

package ec.com.sofka;

import ec.com.sofka.applogs.PrintLogUseCase;
import ec.com.sofka.gateway.BusMessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class BusListener implements BusMessageListener {

    private final PrintLogUseCase printLogUseCase;

    @Value("${app.QUEUE_NAME}")
    private String queueName;

    public BusListener(PrintLogUseCase printLogUseCase) {
        this.printLogUseCase = printLogUseCase;
    }

    @Override
    @RabbitListener(queues = "${app.QUEUE_NAME}")
    public void receiveMsg(String message) {
        printLogUseCase.accept(message);
    }
}
