package com.engine.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private String transactionId;
    private String userId;
    private BigDecimal amount;
    private String currency;
    private String status;
    private Instant timestamp;
}
