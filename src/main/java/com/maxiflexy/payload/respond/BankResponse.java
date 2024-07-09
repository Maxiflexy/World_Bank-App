package com.maxiflexy.payload.respond;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
public class BankResponse<T> {

    private String responseCode;

    private String responseMessage;

    private AccountInfo accountInfo;

    public BankResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    private BankResponse(String responseCode, String responseMessage, AccountInfo accountInfo){
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.accountInfo = accountInfo;
    }

    public BankResponse(String message, String fileUrl){
        this.responseMessage = message;
    }

}
