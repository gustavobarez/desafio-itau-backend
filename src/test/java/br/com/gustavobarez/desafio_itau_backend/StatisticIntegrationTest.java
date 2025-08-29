package br.com.gustavobarez.desafio_itau_backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import br.com.gustavobarez.desafio_itau_backend.statistic.Statistic;
import br.com.gustavobarez.desafio_itau_backend.transaction.TransactionRepository;
import br.com.gustavobarez.desafio_itau_backend.transaction.TransactionRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StatisticIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TransactionRepository repository;

    @BeforeEach
    void setup() {
        repository.clear();
    }

    @Test
    void testStatistics_NoTransactions() {
        ResponseEntity<Statistic> response = restTemplate.getForEntity("/estatistica", Statistic.class);

        assertEquals(0, response.getBody().getCount());
        assertEquals(0, response.getBody().getSum());
        assertEquals(0, response.getBody().getAvg());
        assertEquals(0, response.getBody().getMin());
        assertEquals(0, response.getBody().getMax());
    }

    @Test
    void testStatistics_WithTransactionsInLast60Seconds() {
        OffsetDateTime now = OffsetDateTime.now();

        repository.insert(new TransactionRequest(BigDecimal.valueOf(100), now.minusSeconds(30)));
        repository.insert(new TransactionRequest(BigDecimal.valueOf(200), now.minusSeconds(10)));

        ResponseEntity<Statistic> response = restTemplate.getForEntity("/estatistica", Statistic.class);

        Statistic stats = response.getBody();

        assertEquals(2, stats.getCount());
        assertEquals(300, stats.getSum());
        assertEquals(150, stats.getAvg());
        assertEquals(100, stats.getMin());
        assertEquals(200, stats.getMax());
    }

    @Test
    void testStatistics_TransactionsOutsideLast60Seconds() {
        OffsetDateTime now = OffsetDateTime.now();

        repository.insert(new TransactionRequest(BigDecimal.valueOf(50), now.minusSeconds(120)));
        repository.insert(new TransactionRequest(BigDecimal.valueOf(75), now.minusSeconds(90)));

        ResponseEntity<Statistic> response = restTemplate.getForEntity("/estatistica", Statistic.class);

        Statistic stats = response.getBody();

        assertEquals(0, stats.getCount());
        assertEquals(0, stats.getSum());
        assertEquals(0, stats.getAvg());
        assertEquals(0, stats.getMin());
        assertEquals(0, stats.getMax());
    }

    @Test
    void testStatistics_MixedTransactions() {
        OffsetDateTime now = OffsetDateTime.now();

        repository.insert(new TransactionRequest(BigDecimal.valueOf(100), now.minusSeconds(30)));
        repository.insert(new TransactionRequest(BigDecimal.valueOf(200), now.minusSeconds(10)));

        repository.insert(new TransactionRequest(BigDecimal.valueOf(50), now.minusSeconds(120)));

        ResponseEntity<Statistic> response = restTemplate.getForEntity("/estatistica", Statistic.class);

        Statistic stats = response.getBody();

        assertEquals(2, stats.getCount());
        assertEquals(300, stats.getSum());
        assertEquals(150, stats.getAvg());
        assertEquals(100, stats.getMin());
        assertEquals(200, stats.getMax());
    }

}
