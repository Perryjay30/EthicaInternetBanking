package com.payvantage.ethicainternetbanking.data.dto.response;

import lombok.Data;

@Data
public class NotificationResponse {
    private int statusCode;
    private String description;
    private Data data;
    private Object errors;


    @lombok.Data
    static class Data {
        private String smsId;

    }
}

