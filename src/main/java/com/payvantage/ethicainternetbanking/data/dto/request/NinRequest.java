package com.payvantage.ethicainternetbanking.data.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NinRequest {
    @NotNull
    private String nin;
//    private String dateOfBirth;
    private String image;
}
