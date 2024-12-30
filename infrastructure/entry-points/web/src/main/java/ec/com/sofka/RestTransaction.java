package ec.com.sofka;

import ec.com.sofka.data.dto.transactionDTO.TransactionRequestDTO;
import ec.com.sofka.data.dto.transactionDTO.TransactionResponseDTO;
import ec.com.sofka.exceptions.ErrorResponse;
import ec.com.sofka.handlers.transaction.GetAllTransactionsHandler;
import ec.com.sofka.handlers.transaction.GetTransactionByIdHandler;
import ec.com.sofka.handlers.transaction.SaveTransactionHandler;
import ec.com.sofka.validator.RequestValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.validation.annotation.Validated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Validated
@Configuration
public class RestTransaction {
    private final SaveTransactionHandler saveTransactionHandler;
    private final GetAllTransactionsHandler getAllTransactionsHandler;
    private final GetTransactionByIdHandler getTransactionByIdHandler;
    private final RequestValidator requestValidator;
    private final Logger logger = LoggerFactory.getLogger(RestTransaction.class);

    public RestTransaction(SaveTransactionHandler saveTransactionHandler, GetAllTransactionsHandler getAllTransactionsHandler, GetTransactionByIdHandler getTransactionByIdHandler, RequestValidator requestValidator) {
        this.saveTransactionHandler = saveTransactionHandler;
        this.getAllTransactionsHandler = getAllTransactionsHandler;
        this.getTransactionByIdHandler = getTransactionByIdHandler;
        this.requestValidator = requestValidator;
    }

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/transacciones",
                    operation = @Operation(
                            tags = {"Transactions"},
                            operationId = "saveTransaction",
                            summary = "Create a new transaction",
                            description = "This endpoint allows the creation of a new transaction.",
                            requestBody = @RequestBody(
                                    description = "Transaction creation details",
                                    required = true,
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = TransactionRequestDTO.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "201",
                                            description = "Transaction successfully created",
                                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionResponseDTO.class))
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
                    path = "/api/transacciones/all",
                    operation = @Operation(
                            tags = {"Transactions"},
                            operationId = "getAllTransactions",
                            summary = "Get all transactions",
                            description = "Fetch all transactions.",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Successfully retrieved the list of transactions",
                                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionResponseDTO.class))
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/transacciones/{id}",
                    operation = @Operation(
                            tags = {"Transactions"},
                            operationId = "getTransactionById",
                            summary = "Get transaction by ID",
                            description = "Fetches the transaction details associated with the given transaction ID. If the transaction does not exist, it returns a 404 Not Found error.",
                            parameters = {
                                    @Parameter(
                                            name = "id",
                                            description = "The transaction ID to retrieve transaction info",
                                            required = true,
                                            in = ParameterIn.PATH
                                    )
                            },
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Successfully retrieved the transaction details",
                                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionResponseDTO.class))
                                    ),
                                    @ApiResponse(
                                            responseCode = "404",
                                            description = "Transaction not found. The transaction ID does not exist in the system.",
                                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                                    )
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> transactionRoutes() {
        return route(POST("/api/transacciones"), this::saveTransaction)
                .andRoute(POST("/api/transacciones/all"), this::getAllTransactions)
                .andRoute(POST("/api/transacciones/{id}"), this::getTransactionById)
                .andRoute(GET("/api/**"), request ->
                        ServerResponse.status(HttpStatus.NOT_FOUND)
                                .bodyValue("The requested resource does not exist transaction"));
    }

    public Mono<ServerResponse> saveTransaction(ServerRequest request) {
        return request.bodyToMono(TransactionRequestDTO.class)
                .doOnNext(requestValidator::validate)
                .flatMap(saveTransactionHandler::handle)
                .flatMap(transaction -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(transaction))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.BAD_REQUEST)
                        .bodyValue(e.getMessage()));
    }

    public Mono<ServerResponse> getAllTransactions(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllTransactionsHandler.getTransactions(), TransactionResponseDTO.class);
    }

    public Mono<ServerResponse> getTransactionById(ServerRequest request) {
        String id = request.pathVariable("id");
        return getTransactionByIdHandler.getTransactionById(id)
                .flatMap(responseDTO -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(responseDTO))
                .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)
                        .bodyValue("La transacción con el ID " + id + " no existe"));
    }

}
