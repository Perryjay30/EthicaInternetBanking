package com.payvantage.ethicainternetbanking.data.dto.response;

import lombok.Data;

@Data
public class UpdateKYCResponse {
    public int statusCode;
    public String description;
    public Object data;
}
