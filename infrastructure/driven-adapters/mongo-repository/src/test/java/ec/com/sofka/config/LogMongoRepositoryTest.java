package ec.com.sofka.config;

import ec.com.sofka.TestMongoConfig;
import ec.com.sofka.data.LogEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

@ActiveProfiles("test")
@DataMongoTest
@AutoConfigureDataMongo
@ContextConfiguration(classes = TestMongoConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogMongoRepositoryTest {

    @Autowired
    LogMongoRepository logRepository;

    private LogEntity log1;
    private LogEntity log2;

    @BeforeAll
    void setup() {
        log1 = new LogEntity("1", "Test message 1", "Entity1", LocalDateTime.now());
        log2 = new LogEntity("2", "Test message 2", "Entity2", LocalDateTime.now());
    }

    @BeforeEach
    void init() {
        logRepository.deleteAll().block();
        logRepository.saveAll(Flux.just(log1, log2)).blockLast();
    }

    @Test
    void findById_shouldReturnLog_whenLogExists() {
        StepVerifier.create(logRepository.findById("1"))
                .expectNextMatches(log -> log.getMessage().equals("Test message 1")
                        && log.getEntity().equals("Entity1"))
                .verifyComplete();
    }

    @Test
    void findById_shouldReturnEmpty_whenLogDoesNotExist() {
        StepVerifier.create(logRepository.findById("99"))
                .verifyComplete();
    }

    @Test
    void save_shouldPersistLog() {
        LogEntity newLog = new LogEntity(
                "3",
                "New test message",
                "Entity3",
                LocalDateTime.now());

        StepVerifier.create(logRepository.save(newLog))
                .expectNextMatches(log -> log.getId().equals("3")
                        && log.getMessage().equals("New test message"))
                .verifyComplete();

        StepVerifier.create(logRepository.findById("3"))
                .expectNextMatches(log -> log.getEntity().equals("Entity3"))
                .verifyComplete();
    }

    @Test
    void delete_shouldRemoveLog() {
        StepVerifier.create(logRepository.deleteById("1"))
                .verifyComplete();

        StepVerifier.create(logRepository.findById("1"))
                .verifyComplete();
    }
}
