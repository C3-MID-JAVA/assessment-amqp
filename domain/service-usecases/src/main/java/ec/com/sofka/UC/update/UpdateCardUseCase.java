package ec.com.sofka.UC.update;

import ec.com.sofka.card.Card;
import ec.com.sofka.gateway.repository.CardRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UpdateCardUseCase {
    private final CardRepository repository;

    public UpdateCardUseCase(CardRepository repository) {
        this.repository = repository;
    }

    public Mono<Card> apply(Mono<Card> apply) {
        return null;
    }
}
