package br.com.gustavobarez.desafio_itau_backend.transaction;

import java.time.OffsetDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void insert(TransactionRequest request) {

        logger.info("Recebendo transação: valor={}, dataHora={}", request.getValor(), request.getDataHora());

        if (request.getDataHora() == null) {
            logger.warn("Transação rejeitada: dataHora null");
            throw new IllegalArgumentException("dataHora cannot be null");
        }

        if (request.getValor() == null) {
            logger.warn("Transação rejeitada: valor null");
            throw new IllegalArgumentException("value cannot be null");
        }

        if (request.getDataHora().isAfter(OffsetDateTime.now())) {
            logger.warn("Transação rejeitada: dataHora no futuro");
            throw new IllegalArgumentException("dataHora must be in the past");
        }

        if (request.getValor().intValue() < 0) {
            logger.warn("Transação rejeitada: valor negativo");
            throw new IllegalArgumentException("value must be higher than 0");
        }

        TransactionRequest transaction = TransactionRequest.builder()
                .valor(request.getValor())
                .dataHora(request.getDataHora())
                .build();

        repository.insert(transaction);
        logger.info("Transação inserida com sucesso: valor={}, dataHora={}", transaction.getValor(),
                transaction.getDataHora());
    }

    public void clear() {
        logger.info("Limpando todas as transações");
        repository.clear();
        logger.info("Transações limpas");
    }

}
