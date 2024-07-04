package com.maxiflexy.service;

import com.maxiflexy.payload.request.LoginRequest;
import com.maxiflexy.payload.request.UserRequest;
import com.maxiflexy.payload.respond.ApiResponse;
import com.maxiflexy.payload.respond.BankResponse;
import com.maxiflexy.payload.respond.JwtAuthResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    BankResponse registerUser(UserRequest userRequest);

    ResponseEntity<ApiResponse<JwtAuthResponse>> loginUser(LoginRequest loginRequest);
}
