package com.payvantage.ethicainternetbanking.data.dto.request;

import lombok.Data;

@Data
public class CreateUserBioDataRequest {

    private int birthCountryId;
    private int branchId;
    private String bvn;
    private String customerDomiciledState;
    private String dateOfBirth;
    private String email;
    private int employStatusId;
    private String firstName;
    private String fullname;
    private int genderId;
    private String homeTelno;
    private int kycTierId;
    private String language;
    private String lastName;
    private String lgaOfOrigin;
    private String maidenName;
    private int maritalStatusId;
    private String middleName;
    private String mobileNumber;
    private String mobileNumber2;
    private String mothersMaidenName;
    private int nationalityId;
    private String nickname;
    private String permanentAddressLine1;
    private String permanentAddressLine2;
    private String permanentAddressLine3;
    private String permanentAddressLine4;
    private String picture;
    private String placeOfBirth;
    private String residenceStatus;
    private String residentialAddressLine1;
    private String residentialAddressLine2;
    private String residentialAddressLine3;
    private String residentialAddressLine4;
    private String signature;
    private String spouseEmploymentstatus;
    private String spouseName;
    private int stateOfOriginId;
    private String telephoneNo;
    private int titleId;
}

