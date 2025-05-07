package com.payvantage.ethicainternetbanking.data.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class GetPackagesForBillerResponse {
    private int statusCode;
    private String description;
    private List<PackageData> data;
    private Object errors;

    @Data
    public static class PackageData {
        private String name;
        private String description;
        private String id;
        private String amount;
        private String value;
        private String validity;
    }
}
