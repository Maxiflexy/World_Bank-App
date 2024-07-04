package com.maxiflexy.service.impl;

import com.maxiflexy.domain.entity.UserEntity;
import com.maxiflexy.domain.enums.Role;
import com.maxiflexy.payload.request.EmailDetails;
import com.maxiflexy.payload.request.LoginRequest;
import com.maxiflexy.payload.request.UserRequest;
import com.maxiflexy.payload.respond.AccountInfo;
import com.maxiflexy.payload.respond.ApiResponse;
import com.maxiflexy.payload.respond.BankResponse;
import com.maxiflexy.payload.respond.JwtAuthResponse;
import com.maxiflexy.repository.UserRepository;
import com.maxiflexy.service.AuthService;
import com.maxiflexy.service.EmailService;
import com.maxiflexy.utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    public BankResponse registerUser(UserRequest userRequest) {

        if(userRepository.existsByEmail(userRequest.getEmail())){
            BankResponse response = BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                    .build();
            return response;
        }

        UserEntity newUser = UserEntity.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .otherName(userRequest.getOtherName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .gender(userRequest.getGender())
                .phoneNumber(userRequest.getPhoneNumber())
                .address(userRequest.getAddress())
                .BVN(userRequest.getBvn())
                .pin(userRequest.getPin())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .bankName("World Bank Limited")
                .accountBalance(BigDecimal.ZERO)
                .status("ACTIVE")
                .profilePicture("https://res.cloudinary.com/dpfqbb9pl/image/upload/v1701260428/maleprofile_ffeep9.png")
                .role(Role.USER)
                .build();

        UserEntity savedUser = userRepository.save(newUser);

        //Add email alert here
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(savedUser.getEmail())
                .subject("ACCOUNT CREATION")
                .messageBody("CONGRATULATIONS!! Your Account Has Been Successfully Created.\n" + "Your Account Details: \n" +
                        "Account Name: " + savedUser.getFirstName() + " " + savedUser.getLastName() + " " + savedUser.getOtherName() +
                        "\nAccount Number : " + savedUser.getAccountNumber() +
                        "\nAccount Type : Savings Account")
                .build();

        emailService.sendEmailAlert(emailDetails);


        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_SUCCESS_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(savedUser.getAccountBalance())
                        .accountNumber(savedUser.getAccountNumber())
                        .bankName(savedUser.getBankName())
                        .accountName(savedUser.getBankName() + " " + savedUser.getLastName() + " " + savedUser.getOtherName())
                        .build())
                .build();
    }

    @Override
    public ResponseEntity<ApiResponse<JwtAuthResponse>> loginUser(LoginRequest loginRequest) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(loginRequest.getEmail());

        EmailDetails loginAlert = EmailDetails.builder()
                .subject("Your are logged in")
                .recipient(loginRequest.getEmail())
                .messageBody("Your logged into your account. If you did not initiate this request, Contact support desk")
                .build();

        emailService.sendEmailAlert(loginAlert);

        UserEntity user = userEntityOptional.get();

        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        new ApiResponse<>(
                                "LoginSuccessfully",
                                JwtAuthResponse.builder()
                                        .accessToken("generate token")
                                        .tokenType("Bearer")
                                        .id(user.getId())
                                        .email(user.getEmail())
                                        .gender(user.getGender())
                                        .firstName(user.getFirstName())
                                        .lastName(user.getLastName())
                                        .profilePicture(user.getProfilePicture())
                                        .build()
                        )
                );

    }
}
