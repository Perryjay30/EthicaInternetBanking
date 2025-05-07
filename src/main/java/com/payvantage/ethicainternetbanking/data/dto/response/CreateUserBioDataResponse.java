package com.payvantage.ethicainternetbanking.data.dto.response;

import lombok.Data;

@Data
public class CreateUserBioDataResponse {

    private int statusCode;
    private String description;
    private DataResponse data;

    @Data
    public static class DataResponse {
        private int cif;
    }
}
