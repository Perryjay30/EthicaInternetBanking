package com.payvantage.ethicainternetbanking.service;

import com.payvantage.ethicainternetbanking.data.dto.response.NotificationResponse;

public interface NotificationService {

    NotificationResponse sendSms(String phoneNumber, String otp);
    NotificationResponse sendEmail(String email, String otp);
}
