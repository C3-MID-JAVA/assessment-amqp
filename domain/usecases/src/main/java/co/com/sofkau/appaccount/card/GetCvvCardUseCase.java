package co.com.sofkau.appaccount.card;

import co.com.sofkau.Utils;
import co.com.sofkau.gateway.ICardRepository;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;


public class GetCvvCardUseCase {

    private final ICardRepository cardRepository;

    public GetCvvCardUseCase(ICardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Mono<String> apply() {
        Supplier<String> cvvGenerator = Utils::generateCvvCode;

        return Mono.defer(() -> Mono.just(cvvGenerator.get()))
                .flatMap(cvv -> existsCvv(cvv)
                        .flatMap(exists -> exists ? Mono.empty() : Mono.just(cvv)))
                .repeat()
                .next();
    }

    private Mono<Boolean> existsCvv(String cvv) {
        return cardRepository.existsByCardCVV(cvv);
    }
}
