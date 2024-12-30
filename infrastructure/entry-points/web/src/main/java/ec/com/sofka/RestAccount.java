package ec.com.sofka;

import ec.com.sofka.data.dto.accountDTO.AccountRequestDTO;
import ec.com.sofka.data.dto.accountDTO.AccountResponseDTO;

import ec.com.sofka.exceptions.ErrorResponse;
import ec.com.sofka.handlers.account.GetAccountByIdHandler;
import ec.com.sofka.handlers.account.GetAllAccountsHandler;
import ec.com.sofka.handlers.account.SaveAccountHandler;
import ec.com.sofka.validator.RequestValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Validated
@Configuration
public class RestAccount {
    private final GetAccountByIdHandler getAccountByIdHandler;
    private final SaveAccountHandler saveAccountHandler;
    private final GetAllAccountsHandler getAllAccountsHandler;
    private final RequestValidator requestValidator;
    private final Logger logger = LoggerFactory.getLogger(RestAccount.class);

    public RestAccount(GetAccountByIdHandler getAccountByIdHandler, SaveAccountHandler saveAccountHandler, GetAllAccountsHandler getAllAccountsHandler, RequestValidator requestValidator) {
        this.getAccountByIdHandler = getAccountByIdHandler;
        this.saveAccountHandler = saveAccountHandler;
        this.getAllAccountsHandler = getAllAccountsHandler;
        this.requestValidator = requestValidator;
    }


    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/cuentas",
                    operation = @Operation(
                            tags = {"Accounts"},
                            operationId = "createAccount",
                            summary = "Create a new account",
                            description = "This endpoint allows the creation of a new bank account.",
                            requestBody = @RequestBody(
                                    description = "Account creation details",
                                    required = true,
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = AccountRequestDTO.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "201",
                                            description = "Account successfully created",
                                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountResponseDTO.class))
                                    ),
                                    @ApiResponse(
                                            responseCode = "400",
                                            description = "Bad request, validation error or missing required fields",
                                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/cuentas/{id}",
                    operation = @Operation(
                            tags = {"Accounts"},
                            operationId = "getAccountById",
                            summary = "Get account by ID",
                            description = "Fetches the account details associated with the given account ID. If the account does not exist, it returns a 404 Not Found error.",
                            parameters = {
                                    @Parameter(
                                            name = "id",
                                            description = "The account ID to retrieve account info",
                                            required = true,
                                            in = ParameterIn.PATH
                                    )
                            },
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Successfully retrieved the account details",
                                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountResponseDTO.class))
                                    ),
                                    @ApiResponse(
                                            responseCode = "404",
                                            description = "Account not found. The account ID does not exist in the system.",
                                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/cuentas",
                    operation = @Operation(
                            tags = {"Accounts"},
                            operationId = "getAllAccounts",
                            summary = "Get all accounts",
                            description = "Fetch all bank accounts.",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Successfully retrieved the list of accounts",
                                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountResponseDTO.class))
                                    )
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> accountRoutes() {
        return route(GET("/api/cuentas/{id}"), this::getAccountById)
                .andRoute(GET("/api/cuentas"), this::getAllAccounts)
                .andRoute(POST("/api/cuentas"), this::createAccount)
                .andRoute(GET("/api/**"), request ->
                        ServerResponse.status(HttpStatus.NOT_FOUND)
                                .bodyValue("The requested resource does not exist"));
    }
    public Mono<ServerResponse> getAccountById(ServerRequest request) {
        String id = request.pathVariable("id");
        logger.info("Received request for account ID: {}", id);

        return getAccountByIdHandler.getAccountById(id)
                .flatMap(responseDTO -> {
                    logger.info("Account found: {}", responseDTO);
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(responseDTO);
                })
                .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)
                        .bodyValue("La cuenta con el ID " + id + " no existe"));
    }

    public Mono<ServerResponse> getAllAccounts(ServerRequest request) {
        logger.info("Received request for all accounts");

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllAccountsHandler.getAccounts(), AccountResponseDTO.class);
    }

    public Mono<ServerResponse> createAccount(ServerRequest request) {
        return request.bodyToMono(AccountRequestDTO.class)
                .doOnNext(requestValidator::validate)
                .flatMap(saveAccountHandler::handle)
                .flatMap(account -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(account))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.BAD_REQUEST)
                        .bodyValue(e.getMessage()));
    }

}

