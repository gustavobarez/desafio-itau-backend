package br.com.gustavobarez.desafio_itau_backend.transaction;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepository {

    private final List<TransactionRequest> transactions = new ArrayList<>();

    public void insert(TransactionRequest transaction) {
        transactions.add(transaction);
    }

    public void clear() {
        transactions.clear();
    }

    public List<TransactionRequest> list() {
        return transactions;
    }

}
