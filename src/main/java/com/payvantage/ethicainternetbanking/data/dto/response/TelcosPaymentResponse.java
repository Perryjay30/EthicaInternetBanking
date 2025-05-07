package com.payvantage.ethicainternetbanking.data.dto.response;

import lombok.Data;

@Data
public class TelcosPaymentResponse {
    private int statusCode;
    private String description;
    private Object data;
    private Object errors;
}
