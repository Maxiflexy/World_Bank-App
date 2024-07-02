package com.maxiflexy.service;

import com.maxiflexy.payload.request.EmailDetails;

public interface EmailService {

    void sendEmailAlert(EmailDetails emailDetails);
}
