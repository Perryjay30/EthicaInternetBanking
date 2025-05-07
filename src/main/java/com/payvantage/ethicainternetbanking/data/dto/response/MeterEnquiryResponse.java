package com.payvantage.ethicainternetbanking.data.dto.response;

import lombok.Data;

@Data
public class MeterEnquiryResponse {
    private int statusCode;
    private String description;
    private AccountData data;
    private Object errors;

    @Data
    public static class AccountData {
        private String customerAddress;
        private String businessDistrict;
        private String custReference;
        private String accountType;
        private String minimumAmount;
        private String tariffRate;
        private String tariff;
        private String accountNumber;
        private String respCode;
        private String customerName;
        private String outStandingAmount;
    }
}
