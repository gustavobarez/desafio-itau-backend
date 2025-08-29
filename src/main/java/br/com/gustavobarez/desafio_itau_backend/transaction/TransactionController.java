package br.com.gustavobarez.desafio_itau_backend.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Transações", description = "Endpoints para criação e limpeza de transações")
@RestController
@RequestMapping("/transacao")
public class TransactionController {

    TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @Operation(summary = "Recebe uma nova transação", description = "Cria uma nova transação a partir do valor e dataHora fornecidos", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request body com valor e dataHora da transação", required = true, content = @Content(schema = @Schema(implementation = TransactionRequest.class))), responses = {
            @ApiResponse(responseCode = "201", description = "Transação criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "JSON inválido"),
            @ApiResponse(responseCode = "422", description = "Campos inválidos")
    })
    @PostMapping
    public ResponseEntity<Void> receiveTransaction(@RequestBody TransactionRequest request) {
        try {
            service.insert(request);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Limpa todas as transações", description = "Remove todas as transações armazenadas em memória", responses = {
            @ApiResponse(responseCode = "200", description = "Transações removidas com sucesso")
    })
    @DeleteMapping
    public ResponseEntity<Void> clearTransactions() {
        service.clear();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
