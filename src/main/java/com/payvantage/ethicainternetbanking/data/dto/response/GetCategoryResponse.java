package com.payvantage.ethicainternetbanking.data.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class GetCategoryResponse {
    private int statusCode;
    private String description;
    private List<CategoryData> data;
    private Object errors;

    @Data
    public static class CategoryData {
        private int id;
        private String categoryName;
        private String description;
        private int sortOrder;
        private boolean status;
        private String vasType;
    }
}
