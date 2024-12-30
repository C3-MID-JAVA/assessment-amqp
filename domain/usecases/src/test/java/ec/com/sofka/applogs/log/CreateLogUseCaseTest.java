package ec.com.sofka.applogs.log;

import ec.com.sofka.Log;
import ec.com.sofka.applogs.CreateLogUseCase;
import ec.com.sofka.gateway.LogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateLogUseCaseTest {

    @Mock
    private LogRepository logRepository;

    @InjectMocks
    private CreateLogUseCase createLogUseCase;

    @Test
    void shouldCreateLogSuccessfully() {
        String entity = "TestEntity";
        String message = "Test message";

        when(logRepository.create(any(Log.class))).thenReturn(Mono.empty());

        createLogUseCase.accept(entity, message);

        ArgumentCaptor<Log> logCaptor = ArgumentCaptor.forClass(Log.class);
        verify(logRepository, times(1)).create(logCaptor.capture());

        Log capturedLog = logCaptor.getValue();
        assertNotNull(capturedLog);
        assertEquals(entity, capturedLog.getEntity());
        assertEquals(message, capturedLog.getMessage());
        assertNotNull(capturedLog.getTimestamp());
    }
}
