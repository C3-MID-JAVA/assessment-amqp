package ec.com.sofka.appservice;

import ec.com.sofka.RestTransaction;
import ec.com.sofka.data.dto.transactionDTO.TransactionRequestDTO;
import ec.com.sofka.data.dto.transactionDTO.TransactionResponseDTO;
import ec.com.sofka.exceptions.GlobalExceptionHandler;
import ec.com.sofka.handlers.transaction.GetAllTransactionsHandler;
import ec.com.sofka.handlers.transaction.GetTransactionByIdHandler;
import ec.com.sofka.handlers.transaction.SaveTransactionHandler;
import ec.com.sofka.validator.RequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@WebFluxTest
@ContextConfiguration(classes = {RestTransaction.class, GetTransactionByIdHandler.class, SaveTransactionHandler.class, GetAllTransactionsHandler.class, RequestValidator.class, GlobalExceptionHandler.class})
public class RestTransactionTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private GetTransactionByIdHandler getTransactionByIdHandler;

    @MockitoBean
    private SaveTransactionHandler saveTransactionHandler;

    @MockitoBean
    private GetAllTransactionsHandler getAllTransactionsHandler;

    @InjectMocks
    private RestTransaction restTransaction;

    private TransactionRequestDTO validTransactionRequest;
    private TransactionResponseDTO transactionResponse;
    @BeforeEach
    void setUp() {
        validTransactionRequest = new TransactionRequestDTO(new BigDecimal("100.0"), "deposit", new BigDecimal("1.0"), "1");
        transactionResponse = new TransactionResponseDTO("1", new BigDecimal("100.0"), "deposit", new BigDecimal("1.0"), "1");
    }

    @Test
    void testGetTransactionById() {
        String transactionId = "1";
        when(getTransactionByIdHandler.getTransactionById(transactionId)).thenReturn(Mono.just(transactionResponse));

        webTestClient.post()
                .uri("/api/transacciones/{id}", transactionId)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(transactionId)
                .jsonPath("$.amount").isEqualTo(100.0)
                .jsonPath("$.type").isEqualTo("deposit")
                .jsonPath("$.cost").isEqualTo(1.0)
                .jsonPath("$.idAccount").isEqualTo("1");

        verify(getTransactionByIdHandler, times(1)).getTransactionById(transactionId);
    }

    @Test
    void testGetAllTransactions() {
        TransactionResponseDTO transaction1 = new TransactionResponseDTO("1", new BigDecimal("100.0"), "deposit", new BigDecimal("1.0"), "1");
        TransactionResponseDTO transaction2 = new TransactionResponseDTO("2", new BigDecimal("200.0"), "withdraw", new BigDecimal("2.0"), "2");

        when(getAllTransactionsHandler.getTransactions()).thenReturn(Flux.just(transaction1, transaction2));

        webTestClient.post()
                .uri("/api/transacciones/all")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").isEqualTo("1")
                .jsonPath("$[0].amount").isEqualTo(100.0)
                .jsonPath("$[0].type").isEqualTo("deposit")
                .jsonPath("$[0].cost").isEqualTo(1.0)
                .jsonPath("$[0].idAccount").isEqualTo("1")
                .jsonPath("$[1].id").isEqualTo("2")
                .jsonPath("$[1].amount").isEqualTo(200.0)
                .jsonPath("$[1].type").isEqualTo("withdraw")
                .jsonPath("$[1].cost").isEqualTo(2.0)
                .jsonPath("$[1].idAccount").isEqualTo("2");

        verify(getAllTransactionsHandler, times(1)).getTransactions();
    }

    @Test
    void testCreateTransaction() {
        when(saveTransactionHandler.handle(any(TransactionRequestDTO.class))).thenReturn(Mono.just(transactionResponse));

        webTestClient.post()
                .uri("/api/transacciones")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(validTransactionRequest)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.amount").isEqualTo(100.0)
                .jsonPath("$.type").isEqualTo("deposit")
                .jsonPath("$.cost").isEqualTo(1.0)
                .jsonPath("$.idAccount").isEqualTo("1");

        verify(saveTransactionHandler, times(1)).handle(any(TransactionRequestDTO.class));
    }
}