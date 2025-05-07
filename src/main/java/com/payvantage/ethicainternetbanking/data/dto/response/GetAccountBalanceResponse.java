package com.payvantage.ethicainternetbanking.data.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class GetAccountBalanceResponse {
    private int statusCode;
    private String description;
    private List<AccountsData> data;

    @Data
    public static class AccountsData {
        private double availableBalance;
        private double ledgerBalance;
        private String accountNumber;
        private String accountClass;
        private boolean hasSweep;
        private String customerAccountNumber;
        private String accountName;
        private String phoneNumberAccount;
        private String comment;
        private String productName;
        private boolean pnd;
        private boolean pnc;
        private boolean dormant;
        private boolean flag;
    }
}

