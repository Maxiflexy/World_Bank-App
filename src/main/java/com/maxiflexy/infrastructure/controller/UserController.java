package com.maxiflexy.infrastructure.controller;

import com.maxiflexy.payload.request.CreditAndDebitRequest;
import com.maxiflexy.payload.request.EnquiryRequest;
import com.maxiflexy.payload.request.TransferRequest;
import com.maxiflexy.payload.respond.BankResponse;
import com.maxiflexy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/balance-enquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquiryRequest enquiryRequest){
        return userService.balanceEnquiry(enquiryRequest);
    }

    @GetMapping("/name-enquiry")
    public String nameEnquiry(@RequestBody EnquiryRequest enquiryRequest){
        return userService.nameEnquiry(enquiryRequest);
    }

    @PostMapping("/credit-account")
    public BankResponse creditAccount(@RequestBody CreditAndDebitRequest creditAndDebitRequest){
        return userService.creditAccount(creditAndDebitRequest);
    }

    @PostMapping("/debit-account")
    public BankResponse debitAccount(@RequestBody CreditAndDebitRequest creditAndDebitRequest){
        return userService.debitAccount(creditAndDebitRequest);
    }

    @PostMapping("/transfer")
    public BankResponse transferAmount(@RequestBody TransferRequest transferRequest){
        return userService.transfer(transferRequest);
    }


}
