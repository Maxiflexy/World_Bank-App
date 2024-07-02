package com.maxiflexy.payload.respond;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AccountInfo {

    private String accountName;

    private BigDecimal accountBalance;

    private String accountNumber;

    private String bankName;

}
