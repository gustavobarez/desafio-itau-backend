package br.com.gustavobarez.desafio_itau_backend.transaction;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.gustavobarez.desafio_itau_backend.statistic.Statistic;

@Repository
public class TransactionRepository {

    private final List<TransactionRequest> transactions = new ArrayList<>();

    public void insert(TransactionRequest transaction) {
        transactions.add(transaction);
    }

    public void clear() {
        transactions.clear();
    }

    public Statistic statistics(OffsetDateTime initialHour) {
        if (transactions.isEmpty()) {
            return new Statistic();
        } else {
            DoubleSummaryStatistics stats = transactions.stream()
                    .filter(transaction -> !transaction.getDataHora().isBefore(initialHour))
                    .mapToDouble(transaction -> transaction.getValor().doubleValue())
                    .summaryStatistics();

            return new Statistic(stats);
        }
    }

}
