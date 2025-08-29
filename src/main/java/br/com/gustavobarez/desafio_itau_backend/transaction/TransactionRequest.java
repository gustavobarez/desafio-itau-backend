package br.com.gustavobarez.desafio_itau_backend.transaction;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRequest {

    private BigDecimal valor;

    private OffsetDateTime dataHora;

}
