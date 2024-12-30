package ec.com.sofka.applogs;

import org.springframework.stereotype.Component;

//18. UseCase for printing logs
@Component
public class PrintLogUseCase{

    public void accept(String message){
        //Print the message
        System.out.println("Message received: " + message);
    }
}
