package com.maxiflexy.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TransferRequest {

    private String destinationAccountNumber;

    private String sourceAccountNumber;

    private BigDecimal amount;
}
