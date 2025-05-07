package com.payvantage.ethicainternetbanking.data.dto.request;

import lombok.Data;

@Data
public class MeterEnquiryRequest {
    private String customerId;
    private int discoIdentifier;
    private int accountType;
}
