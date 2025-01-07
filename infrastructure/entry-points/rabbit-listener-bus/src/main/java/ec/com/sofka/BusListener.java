package ec.com.sofka;

import ec.com.sofka.UC.PrintLogUseCase;
import ec.com.sofka.log.TransactionLog;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

// Agregar implementacion de interfaz
@Service
public class BusListener {
    private final PrintLogUseCase printLogUseCase;

    public BusListener(PrintLogUseCase printLogUseCase) {
        this.printLogUseCase = printLogUseCase;
    }

    @RabbitListener(queues = "${branch.transfer.queue.name}")
    public void receiveBranchTransfer(TransactionLog message) {
        printLogUseCase.apply(Mono.just(message)).subscribe();
    }

    @RabbitListener(queues = "${another.account.deposit.queue.name}")
    public void receiveAnotherAccountDeposit(TransactionLog message) {
        printLogUseCase.apply(Mono.just(message)).subscribe();
    }

    @RabbitListener(queues = "${store.card.purchase.queue.name}")
    public void receiveStoreCardPurchase(TransactionLog message) {
        printLogUseCase.apply(Mono.just(message)).subscribe();
    }

    @RabbitListener(queues = "${online.card.purchase.queue.name}")
    public void receiveOnlineCardPurchase(TransactionLog message) {
        printLogUseCase.apply(Mono.just(message)).subscribe();
    }

    @RabbitListener(queues = "${atm.withdrawal.queue.name}")
    public void receiveAtmWithdrawal(TransactionLog message) {
        printLogUseCase.apply(Mono.just(message)).subscribe();
    }

    @RabbitListener(queues = "${atm.deposit.queue.name}")
    public void receiveAtmDeposit(TransactionLog message) {
        printLogUseCase.apply(Mono.just(message)).subscribe();
    }
}