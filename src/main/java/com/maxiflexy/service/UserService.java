package com.maxiflexy.service;

import com.maxiflexy.payload.request.CreditAndDebitRequest;
import com.maxiflexy.payload.request.EnquiryRequest;
import com.maxiflexy.payload.respond.BankResponse;

public interface UserService {

    BankResponse balanceEnquiry(EnquiryRequest enquiryRequest);

    String nameEnquiry(EnquiryRequest enquiryRequest);

    BankResponse creditAccount(CreditAndDebitRequest creditAndDebitRequest);

    BankResponse debitAccount(CreditAndDebitRequest creditAndDebitRequest);
}
