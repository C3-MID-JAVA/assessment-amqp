package co.com.sofkau.appaccount.card;

import co.com.sofkau.Card;
import co.com.sofkau.gateway.ICardRepository;
import reactor.core.publisher.Mono;


public class GetCardByCardNumberUseCase {
    private final ICardRepository cardRepository;

    public GetCardByCardNumberUseCase(ICardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Mono<Card> apply(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber);

    }
}
