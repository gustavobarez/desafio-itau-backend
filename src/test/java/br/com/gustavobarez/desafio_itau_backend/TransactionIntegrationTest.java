package br.com.gustavobarez.desafio_itau_backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.gustavobarez.desafio_itau_backend.transaction.TransactionRequest;
import br.com.gustavobarez.desafio_itau_backend.transaction.TransactionService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TransactionService service;

    @BeforeEach
    void setup() {
        service.clear();
    }

    @Test
    void testInsertTransaction_Valid() {
        TransactionRequest request = new TransactionRequest(BigDecimal.valueOf(100),
                OffsetDateTime.now().minusSeconds(10));

        ResponseEntity<Void> response = restTemplate.postForEntity("/transacao", request, Void.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, service.statistics(OffsetDateTime.now().minusSeconds(60)).getCount());
    }

    @Test
    void testInsertTransaction_NullValue() {
        TransactionRequest request = new TransactionRequest(null, OffsetDateTime.now().minusSeconds(10));

        ResponseEntity<Void> response = restTemplate.postForEntity("/transacao", request, Void.class);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    void testInsertTransaction_NullDate() {
        TransactionRequest request = new TransactionRequest(BigDecimal.valueOf(50), null);

        ResponseEntity<Void> response = restTemplate.postForEntity("/transacao", request, Void.class);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    void testInsertTransaction_FutureDate() {
        TransactionRequest request = new TransactionRequest(BigDecimal.valueOf(50),
                OffsetDateTime.now().plusMinutes(10));

        ResponseEntity<Void> response = restTemplate.postForEntity("/transacao", request, Void.class);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    void testInsertTransaction_NegativeValue() {
        TransactionRequest request = new TransactionRequest(BigDecimal.valueOf(-10),
                OffsetDateTime.now().minusSeconds(10));

        ResponseEntity<Void> response = restTemplate.postForEntity("/transacao", request, Void.class);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    void testInsertTransaction_InvalidJson() {
        String invalidJson = "{ \"valor\": 100, \"dataHora\": \"invalid-date\" }";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(invalidJson, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity("/transacao", request, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testClearTransactions() {
        service.insert(new TransactionRequest(BigDecimal.valueOf(100), OffsetDateTime.now().minusSeconds(10)));

        ResponseEntity<Void> response = restTemplate.exchange("/transacao", HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, service.statistics(OffsetDateTime.now().minusSeconds(60)).getCount());
    }
}
