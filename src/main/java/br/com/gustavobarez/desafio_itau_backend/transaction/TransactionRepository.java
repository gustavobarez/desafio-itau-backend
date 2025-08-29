package br.com.gustavobarez.desafio_itau_backend.transaction;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import br.com.gustavobarez.desafio_itau_backend.statistic.Statistic;

@Repository
public class TransactionRepository {

    private static final Logger logger = LoggerFactory.getLogger(TransactionRepository.class);

    private final List<TransactionRequest> transactions = new ArrayList<>();

    public void insert(TransactionRequest transaction) {
        transactions.add(transaction);
    }

    public void clear() {
        transactions.clear();
    }

    public Statistic statistics(OffsetDateTime initialHour) {
        logger.info("Iniciando cálculo das estatísticas");

        StopWatch sw = new StopWatch();
        sw.start();

        Statistic stat;
        if (transactions.isEmpty()) {
            stat = new Statistic();
        } else {
            DoubleSummaryStatistics stats = transactions.stream()
                    .filter(t -> !t.getDataHora().isBefore(initialHour))
                    .mapToDouble(t -> t.getValor().doubleValue())
                    .summaryStatistics();
            stat = new Statistic(stats);
        }

        sw.stop();
        logger.info("Estatísticas calculadas em {} ms", sw.getTotalTimeMillis());
        return stat;
    }

}
