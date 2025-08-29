package br.com.gustavobarez.desafio_itau_backend.statistic;

import java.time.OffsetDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gustavobarez.desafio_itau_backend.transaction.TransactionRepository;

@RestController
@RequestMapping("/estatistica")
public class StatisticController {

    TransactionRepository transactionRepository;

    public StatisticController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping
    public ResponseEntity estatisticas() {    
        final var initialHour = OffsetDateTime.now().minusSeconds(60);
        return ResponseEntity.ok(transactionRepository.statistics(initialHour));
    }

}
