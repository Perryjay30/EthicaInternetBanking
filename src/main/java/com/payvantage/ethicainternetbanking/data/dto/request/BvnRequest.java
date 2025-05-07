package com.payvantage.ethicainternetbanking.data.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


public class BvnRequest {
    @JsonProperty("bvn")
    private String bvn;

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }
}
