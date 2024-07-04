package com.maxiflexy.service.impl;

import com.maxiflexy.domain.entity.UserEntity;
import com.maxiflexy.payload.request.EnquiryRequest;
import com.maxiflexy.payload.respond.AccountInfo;
import com.maxiflexy.payload.respond.BankResponse;
import com.maxiflexy.repository.UserRepository;
import com.maxiflexy.service.UserService;
import com.maxiflexy.utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.maxiflexy.utils.AccountUtils.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public BankResponse balanceEnquiry(EnquiryRequest enquiryRequest) {
        boolean isAccountExists = userRepository.existsByAccountNumber(enquiryRequest.getAccountNumber());

        if(!isAccountExists){
            return BankResponse.builder()
                    .responseCode(ACCOUNT_NUMBER_NON_EXISTS_CODE)
                    .responseMessage(ACCOUNT_NUMBER_NON_EXISTS_MESSAGE)
                    .build();
        }

        UserEntity foundUserAccount = userRepository.findByAccountNumber(enquiryRequest.getAccountNumber());

        return BankResponse.builder()
                .responseCode(ACCOUNT_EXISTS_CODE)
                .responseMessage(ACCOUNT_NUMBER_FOUND_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(foundUserAccount.getAccountBalance())
                        .accountNumber(enquiryRequest.getAccountNumber())
                        .accountName(foundUserAccount.getFirstName() + " " + foundUserAccount.getLastName())
                        .build())
                .build();
    }


    @Override
    public String nameEnquiry(EnquiryRequest enquiryRequest) {

        boolean isAccountExists = userRepository.existsByAccountNumber(enquiryRequest.getAccountNumber());

        if(!isAccountExists){
            return ACCOUNT_NUMBER_NON_EXISTS_MESSAGE;
        }

        UserEntity foundUserAccount = userRepository.findByAccountNumber(enquiryRequest.getAccountNumber());

        return foundUserAccount.getFirstName() + " " + foundUserAccount.getLastName() + " " + foundUserAccount.getOtherName();
    }
}
