/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.payvantage.ethicainternetbanking.data.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * @author mac
 */
public class UserData {

    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;
    private String loginPasscode;
    private String dateOfBirth;
    
    private UserGender gender;
    private String deviceId;
    
    private String email;
    private String bvn;
    private String nin;
    private String residentialAddress;

    private String contactPhoneNumber;
    private String contactEmail;
    private boolean faceVerification;

    public UserData() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLoginPasscode() {
        return loginPasscode;
    }

    public void setLoginPasscode(String loginPasscode) {
        this.loginPasscode = loginPasscode;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public UserGender getGender() {
        return gender;
    }

    public void setGender(UserGender gender) {
        this.gender = gender;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public boolean isFaceVerification() {
        return faceVerification;
    }

    public void setFaceVerification(boolean faceVerification) {
        this.faceVerification = faceVerification;
    }

    public UserData(UserTable userTable) {
        this.firstName = userTable.getFirstName();
        this.lastName = userTable.getLastName();
        this.middleName = userTable.getMiddleName();
        this.phoneNumber = userTable.getPhoneNumber();
        this.dateOfBirth = userTable.getDateOfBirth();
        this.gender = UserGender.MALE;
        this.deviceId = userTable.getDeviceId();
        this.email = userTable.getEmail();
        this.residentialAddress = userTable.getResidentialAddress();
        // this.bvn = userTable.getBvn();
    }

}
