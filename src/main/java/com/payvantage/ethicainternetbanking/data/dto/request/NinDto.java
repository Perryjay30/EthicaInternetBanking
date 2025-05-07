package com.payvantage.ethicainternetbanking.data.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NinDto {
    @NotNull

    private String nin;
    @NotNull
    private String deviceId;
    private String dateOfBirth;
    private String image;
}
