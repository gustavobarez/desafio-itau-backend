package br.com.gustavobarez.desafio_itau_backend.statistic;

import java.util.DoubleSummaryStatistics;

import lombok.Data;

@Data
public class Statistic {

    private long count;

    private double sum;

    private double avg;

    private double min;

    private double max;

    public Statistic() {
        this(new DoubleSummaryStatistics());
    }

    public Statistic(final DoubleSummaryStatistics doubleSummaryStatistics) {
        this.count = doubleSummaryStatistics.getCount();
        this.sum = doubleSummaryStatistics.getSum();
        this.avg = doubleSummaryStatistics.getAverage();
        this.min = doubleSummaryStatistics.getMin() == Double.POSITIVE_INFINITY ? 0 : doubleSummaryStatistics.getMin();
        this.max = doubleSummaryStatistics.getMax() == Double.NEGATIVE_INFINITY ? 0 : doubleSummaryStatistics.getMax();
    }

}
