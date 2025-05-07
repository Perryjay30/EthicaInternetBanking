package com.payvantage.ethicainternetbanking.data.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BvnDto {
    @NotNull
    private String bvn;
    @NotNull
    private String deviceId;
    private String dateOfBirth;
    private String image;
}
