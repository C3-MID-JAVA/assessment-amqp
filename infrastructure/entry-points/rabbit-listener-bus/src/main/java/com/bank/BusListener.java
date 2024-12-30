package com.bank;

import com.bank.applogs.PrintLogUseCase;
import com.bank.gateway.IBusMessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BusListener implements IBusMessageListener {
    private final PrintLogUseCase printLogUseCase;

    public BusListener(PrintLogUseCase printLogUseCase) {
        this.printLogUseCase = printLogUseCase;
    }

    @Override
    @RabbitListener(queues = "bank.queue")
    public void receiveMsg(String message) {
        printLogUseCase.accept(message);
    }
}