package com.maxiflexy.service;

import com.maxiflexy.payload.request.UserRequest;
import com.maxiflexy.payload.respond.BankResponse;

public interface AuthService {

    BankResponse registerUser(UserRequest userRequest);
}
