package co.com.sofkau.appaccount.card;


import co.com.sofkau.Card;
import co.com.sofkau.Log;
import co.com.sofkau.appaccount.account.GetAccountByAccountNumberUseCase;
import co.com.sofkau.gateway.BusMessage;
import co.com.sofkau.gateway.ICardRepository;
import reactor.core.publisher.Mono;


public class CreateCardUseCase {
    private final ICardRepository cardRepository;
    private final GetAccountByAccountNumberUseCase getAccountByAccountNumberUseCase;
    private final GetCvvCardUseCase getCvvCardUseCase;
    private final GetCardByCardNumberUseCase getCardByCardNumberUseCase;

    private  final BusMessage busMessage;

    public CreateCardUseCase(ICardRepository cardRepository, GetAccountByAccountNumberUseCase getAccountByAccountNumberUseCase, GetCvvCardUseCase getCvvCardUseCase, GetCardByCardNumberUseCase getCardByCardNumberUseCase, BusMessage busMessage) {
        this.cardRepository = cardRepository;
        this.getAccountByAccountNumberUseCase = getAccountByAccountNumberUseCase;
        this.getCvvCardUseCase = getCvvCardUseCase;
        this.getCardByCardNumberUseCase = getCardByCardNumberUseCase;
        this.busMessage = busMessage;
    }

    public Mono<Card> apply(Card card) {
        return getCardByCardNumberUseCase.apply(card.getCardNumber())
                .hasElement()
                .flatMap(element -> {
                    if (element) {
                        return Mono.error(new RuntimeException("Card already exists"));
                    }
                    return getAccountByAccountNumberUseCase.apply(card.getAccount().getAccountNumber())
                            .flatMap(account -> getCvvCardUseCase.apply()
                                    .flatMap(cvv -> {
                                        card.setCardCVV(cvv);
                                        card.setAccount(account);
                                        Log log = new Log( "Card Created: " + card.getCardNumber(),"card", "INFO", null, null);
                                        busMessage.sendMsg(log);
                                        return cardRepository.save(card);
                                    }))
                            .switchIfEmpty(Mono.error(new RuntimeException("Account does not exist")));
                });

    }
}
