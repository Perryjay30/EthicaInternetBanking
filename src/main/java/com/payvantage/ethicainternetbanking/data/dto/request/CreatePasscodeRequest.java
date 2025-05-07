package com.payvantage.ethicainternetbanking.data.dto.request;

import lombok.Data;

@Data
public class CreatePasscodeRequest {
    private String passcode;
    private String confirmPasscode;

}
