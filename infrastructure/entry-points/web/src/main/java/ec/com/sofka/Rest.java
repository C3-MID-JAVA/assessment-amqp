package ec.com.sofka;

import ec.com.sofka.data.RequestDTO;
import ec.com.sofka.data.RequestTransactionDTO;
import ec.com.sofka.data.ResponseDTO;
import ec.com.sofka.data.ResponseTransactionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class Rest {

    private final Handler handler;

    public Rest(Handler handler) {
        this.handler = handler;
    }

    @GetMapping("/account/{id}")
    public Mono<ResponseEntity<ResponseDTO>> getAccountById(@PathVariable String id) {
        return handler.getAccountById(id)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Maneja el caso en que no se encuentre
    }

    @PostMapping("/account")
    public Mono<ResponseEntity<ResponseDTO>> createAccount(@RequestBody RequestDTO requestDTO) {
        return handler.createAccount(requestDTO)
                .map(response -> new ResponseEntity<>(response, HttpStatus.CREATED));
    }

    @GetMapping("/transaction")
    public Flux<ResponseEntity<ResponseTransactionDTO>> getAllTransactions() {
        return handler.getAllTransactions()
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @PostMapping("/transaction")
    public Flux<ResponseEntity<ResponseTransactionDTO>> createTransaction(@RequestBody RequestTransactionDTO requestTransactionDTO) {
        return handler.getAllTransactions()
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }


}
