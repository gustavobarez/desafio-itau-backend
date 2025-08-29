package br.com.gustavobarez.desafio_itau_backend.transaction;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void insert(TransactionRequest request) {

        if (request.getDataHora() == null) {
            throw new IllegalArgumentException("dataHora cannot be null");
        }

        if (request.getValor() == null) {
            throw new IllegalArgumentException("value cannot be null");
        }

        if (request.getDataHora().isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException("dataHora must be in the past");
        }

        if (request.getValor().intValue() < 0) {
            throw new IllegalArgumentException("value must be higher than 0");
        }

        TransactionRequest transaction = TransactionRequest.builder()
                .valor(request.getValor())
                .dataHora(request.getDataHora())
                .build();

        repository.insert(transaction);
    }

    public void clear() {
        repository.clear();
    }

}
