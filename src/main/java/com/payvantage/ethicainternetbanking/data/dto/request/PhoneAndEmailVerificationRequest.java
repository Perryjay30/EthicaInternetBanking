package com.payvantage.ethicainternetbanking.data.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PhoneAndEmailVerificationRequest {
    private String otp;
    private String requestId;
    private String phoneNumber;
    private String email;
}
