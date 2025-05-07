package com.payvantage.ethicainternetbanking.data.dto.request;

import com.payvantage.ethicainternetbanking.data.model.UserTable;
import lombok.Data;

@Data
public class NinRq {

    private String number;
    private String image;


    public NinRq() {
    }

    public NinRq(UserTable userTable) {

        this.number = userTable.getBvn();

    }
}
