package co.com.sofkau.appaccount.card;

import co.com.sofkau.Card;
import co.com.sofkau.Log;
import co.com.sofkau.appaccount.account.GetAccountByAccountNumberUseCase;
import co.com.sofkau.gateway.BusMessage;
import co.com.sofkau.gateway.ICardRepository;
import reactor.core.publisher.Flux;



public class GetCardsByAccountUseCase {
    private final ICardRepository cardRepository;
    private final GetAccountByAccountNumberUseCase getAccountByAccountNumberUseCase;
    private final BusMessage busMessage;


    public GetCardsByAccountUseCase(ICardRepository cardRepository, GetAccountByAccountNumberUseCase getAccountByAccountNumberUseCase, BusMessage busMessage) {
        this.cardRepository = cardRepository;
        this.getAccountByAccountNumberUseCase = getAccountByAccountNumberUseCase;
        this.busMessage = busMessage;
    }

    public Flux<Card> apply(String accountId) {
        Log log = new Log( "Getting all cards by account","card", "INFO", null, null);
        busMessage.sendMsg(log);
        return getAccountByAccountNumberUseCase.apply(accountId)
                .flatMapMany(accountModel -> cardRepository.findByAccount_Id(accountModel.getId()));
    }
}
