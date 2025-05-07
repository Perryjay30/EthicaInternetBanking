package com.payvantage.ethicainternetbanking.data.dto.response;

import lombok.Data;

@Data
public class CablePaymentResponse {
    private int statusCode;
    private String description;
    private CablePaymentData data;
    private Object errors;

    @Data
    public static class CablePaymentData {
        private String transRef;
    }
}
