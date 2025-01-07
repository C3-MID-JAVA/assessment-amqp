package ec.com.sofka.UC.get.card;

import ec.com.sofka.card.Card;
import ec.com.sofka.gateway.repository.CardRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class GetCardsByTypeUseCase {
    private final CardRepository repository;

    public GetCardsByTypeUseCase(CardRepository repository) {
        this.repository = repository;
    }

    public Flux<Card> apply(String s) {
        return null;
    }
}
