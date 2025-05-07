package com.payvantage.ethicainternetbanking.data.dto.request;

import com.payvantage.ethicainternetbanking.data.model.UserTable;
import lombok.Data;

@Data
public class BvnRq {

    private String number;
    private String image;


    public BvnRq() {
    }

    public BvnRq(UserTable userTable) {

        this.number = userTable.getBvn();

    }
}
