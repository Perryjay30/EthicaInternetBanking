package com.payvantage.ethicainternetbanking.data.dto.request;

import lombok.Data;

@Data
public class CableEnquiryRequest {
    private String customerId;
    private String cableCode;
}
