package com.payvantage.ethicainternetbanking.data.dto.request;

public class BvnVerificationRequest {
    private String bvn;
    private String dateOfBirth;

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
