package com.payvantage.ethicainternetbanking.data.dto.response;


import lombok.Data;

import java.util.List;

@Data
public class BillerResponse {
    private int statusCode;
    private String description;
    private List<BillerData> data;
    private String errors;

    @Data
    public static class BillerData {
        private int id;
        private boolean status;
        private String billerName;
        private String billerId;
        private String type;
        private String beanName;
        private boolean hasPackages;
        private double commission;
        private double vat;
        private boolean hasLookUp;
        private double billerMinAmountLimit;
        private double billerMaxAmountLimit;
    }
}
