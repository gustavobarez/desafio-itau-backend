package br.com.gustavobarez.desafio_itau_backend.statistic;

import java.time.OffsetDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gustavobarez.desafio_itau_backend.transaction.TransactionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Estatísticas", description = "Endpoints para consulta de estatísticas de transações")
@RestController
@RequestMapping("/estatistica")
public class StatisticController {

    TransactionRepository transactionRepository;

    public StatisticController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Operation(summary = "Consulta estatísticas das transações", description = "Retorna estatísticas (count, sum, avg, min, max) das transações ocorridas nos últimos 60 segundos", responses = {
            @ApiResponse(responseCode = "200", description = "Estatísticas retornadas com sucesso", content = @Content(schema = @Schema(implementation = Statistic.class)))
    })
    @GetMapping
    public ResponseEntity<Statistic> estatisticas() {
        final var initialHour = OffsetDateTime.now().minusSeconds(60);
        return ResponseEntity.ok(transactionRepository.statistics(initialHour));
    }
}
