package ec.com.sofka.appservice;

import ec.com.sofka.RestAccount;
import ec.com.sofka.data.dto.accountDTO.AccountRequestDTO;
import ec.com.sofka.data.dto.accountDTO.AccountResponseDTO;
import ec.com.sofka.exceptions.GlobalExceptionHandler;
import ec.com.sofka.handlers.account.GetAccountByIdHandler;
import ec.com.sofka.handlers.account.GetAllAccountsHandler;
import ec.com.sofka.handlers.account.SaveAccountHandler;
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
@ContextConfiguration(classes = {RestAccount.class, GetAccountByIdHandler.class, SaveAccountHandler.class, GetAllAccountsHandler.class, RequestValidator.class, GlobalExceptionHandler.class})
public class RestAccountTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private GetAccountByIdHandler getAccountByIdHandler;

    @MockitoBean
    private SaveAccountHandler saveAccountHandler;

    @MockitoBean
    private GetAllAccountsHandler getAllAccountsHandler;

    @InjectMocks
    private RestAccount restAccount;

    private AccountRequestDTO validAccountRequest;
    private AccountResponseDTO accountResponse;

    @BeforeEach
    void setUp() {
        validAccountRequest = new AccountRequestDTO("1", new BigDecimal("1000.0"), "1234567890", "owner1");
        accountResponse = new AccountResponseDTO("1", new BigDecimal("1000.0"), "1234567890", "owner1");
    }

    @Test
    void testGetAccountById() {
        when(getAccountByIdHandler.getAccountById(anyString())).thenReturn(Mono.just(accountResponse));

        webTestClient.get()
                .uri("/api/cuentas/{id}", "1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.balance").isEqualTo(1000.0)
                .jsonPath("$.accountNumber").isEqualTo("1234567890")
                .jsonPath("$.owner").isEqualTo("owner1");

        verify(getAccountByIdHandler, times(1)).getAccountById("1");
    }

    @Test
    void testGetAllAccounts() {
        AccountResponseDTO account1 = new AccountResponseDTO("1", new BigDecimal("1000.0"), "123456", "owner1");
        AccountResponseDTO account2 = new AccountResponseDTO("2", new BigDecimal("2000.0"), "654321", "owner2");

        when(getAllAccountsHandler.getAccounts()).thenReturn(Flux.just(account1, account2));

        webTestClient.get()
                .uri("/api/cuentas")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").isEqualTo("1")
                .jsonPath("$[0].balance").isEqualTo(1000.0)
                .jsonPath("$[0].accountNumber").isEqualTo("123456")
                .jsonPath("$[0].owner").isEqualTo("owner1")
                .jsonPath("$[1].id").isEqualTo("2")
                .jsonPath("$[1].balance").isEqualTo(2000.0)
                .jsonPath("$[1].accountNumber").isEqualTo("654321")
                .jsonPath("$[1].owner").isEqualTo("owner2");

        verify(getAllAccountsHandler, times(1)).getAccounts();
    }

    @Test
    void testCreateAccount() {
        when(saveAccountHandler.handle(any(AccountRequestDTO.class))).thenReturn(Mono.just(accountResponse));

        webTestClient.post()
                .uri("/api/cuentas")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(validAccountRequest)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.balance").isEqualTo(1000.0)
                .jsonPath("$.accountNumber").isEqualTo("1234567890")
                .jsonPath("$.owner").isEqualTo("owner1");

        verify(saveAccountHandler, times(1)).handle(any(AccountRequestDTO.class));
    }
}