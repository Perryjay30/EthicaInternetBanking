package com.payvantage.ethicainternetbanking.data.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class GetCablePackagesResponse {
    private int statusCode;
    private String description;
    private List<GetCablePackagesData> data;
    private Object errors;

    @Data
    public static class GetCablePackagesData {
        private String name;
        private String amount;
        private String productCode;
        private String service;
    }
}
