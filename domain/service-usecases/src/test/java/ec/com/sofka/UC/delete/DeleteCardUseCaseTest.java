package ec.com.sofka.UC.delete;

import ec.com.sofka.card.Card;
import ec.com.sofka.gateway.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = DeleteCardUseCaseTest.class)
public class DeleteCardUseCaseTest {
    @InjectMocks
    private DeleteCardUseCase deleteCardUseCase;

    @Mock
    private CardRepository cardRepository;

    private Card card;

    @BeforeEach
    void setUp() {
        card = new Card("1234567890123456",
                "Debit", "123", LocalDateTime.of(2025, 12, 31, 23, 59, 59), 1);
    }

    @Test
    void testApply_shouldReturnCard() {
        when(cardRepository.deleteById(Mockito.any(Mono.class)))
                .thenReturn(Mono.empty());
        Mono<Void> result = deleteCardUseCase.apply(card.getId());
        StepVerifier.create(result)
                .verifyComplete();
        Mockito.verify(cardRepository, Mockito.times(1)).deleteById(Mockito.any(Mono.class));
    }

    @Test
    void testApply_ShouldReturnError_WhenRepositoryFails() {
        when(cardRepository.deleteById(Mockito.any(Mono.class)))
                .thenReturn(Mono.error(new RuntimeException("Error saving branch")));
        Mono<Void> result = deleteCardUseCase.apply(card.getId());
        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();
        verify(cardRepository, times(1)).deleteById(Mockito.any(Mono.class));
    }
}
