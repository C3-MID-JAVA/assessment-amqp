package ec.com.sofka.gateway.repository;

import ec.com.sofka.request.CardRequestDTO;
import ec.com.sofka.response.CardResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CardRepository {

    Mono<CardResponseDTO> createCard(Mono<CardRequestDTO> card);

    Mono<CardResponseDTO> findCardById(Mono<Integer> id);

    Mono<CardResponseDTO> findCardByCardNumber(Mono<String> card_number);

    Flux<CardResponseDTO> findAll();

    Flux<CardResponseDTO> findCardsByCardType(Mono<String> card_type);

    Mono<Void> deleteById(Mono<Integer> id);
}
