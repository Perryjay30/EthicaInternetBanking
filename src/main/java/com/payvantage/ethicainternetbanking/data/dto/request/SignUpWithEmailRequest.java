package com.payvantage.ethicainternetbanking.data.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class SignUpWithEmailRequest {
    @Email
    private String emailAddress;
}
