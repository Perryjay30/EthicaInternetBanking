package com.payvantage.ethicainternetbanking.data.dto.response;

import lombok.Data;

@Data
public class CableEnquiryResponse {
    private int statusCode;
    private String description;
    private CableEnquiryData data;
    private Object errors;

    @Data
    public static class CableEnquiryData {
        private String respCode;
        private String respDescription;
        private String customerMessage;
        private String customerID;
        private String thirdPartyCode;
        private String custReference;
        private String paymentType;
        private String phone;
        private String businessDistrict;
        private String receiver;
        private String minimumAmount;
        private String creditLimit;
        private String outStandingAmount;
        private String balance;
        private String customerName;
        private String accountType;
        private String email;
        private String status;
        private String accountNumber;
        private String customerAddress;
    }
}
