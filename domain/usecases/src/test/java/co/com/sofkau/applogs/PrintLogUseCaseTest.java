package co.com.sofkau.applogs;

import co.com.sofkau.Log;
import co.com.sofkau.gateway.ILogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrintLogUseCaseTest {

    @Mock
    private ILogRepository logRepository;

    @InjectMocks
    private PrintLogUseCase printLogUseCase;

    private Log log;

    @BeforeEach
    public void setUp() {
        log = new Log("TestGroup", "TestMessage", "INFO", null, LocalDateTime.now());
    }

    @Test
    @DisplayName("Should save the log")
    void apply() {
        when(logRepository.save(log)).thenReturn(Mono.empty());


        StepVerifier.create(printLogUseCase.apply(log))
                .expectSubscription()
                .verifyComplete();


        verify(logRepository, times(1)).save(log);
    }
}