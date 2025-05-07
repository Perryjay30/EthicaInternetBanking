/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.payvantage.ethicainternetbanking.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author mac
 */
@Entity
public class UserTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Column(unique = true)
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String middleName;
    @Enumerated(EnumType.STRING)
    private UserGender gender;
    @Temporal(TemporalType.DATE)
    private String dateOfBirth;
    @JsonIgnore
    private String loginPasscode;
    @Column(unique = true)
    private String bvn;
    @Column(unique = true)
    private String nin;
    @JsonIgnore
    private String secret;
    @JsonIgnore
    private boolean phoneNumberVerified;
    @JsonIgnore
    private boolean active;
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated = new Date();
    @JsonIgnore
    private boolean bvnVerified;
    @JsonIgnore
    private boolean ninVerified;
    @JsonIgnore
    private boolean emailVerified;
    @JsonIgnore
    @Column(name="transaction_pin")
    private String tranxPin;
    @JsonIgnore
    @Column(unique = true)
    private String deviceId;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    @Column(name = "daily_limit")
    private Double dailyLimit;
    @JsonIgnore
    @Column(name = "monthly_limit")
    private Double monthlyLimit;
    @JsonIgnore
    @Column(name = "daily_cumulative")
    private Double dailyCumulative;
    @JsonIgnore
    @Column(name = "monthly_cumulative")
    private Double monthlyCumulative;
    @JsonIgnore
    @Column(name = "last_transaction_date")
    private LocalDate lastTransactionDate;
    private String profilePicture;
    private String residentialAddress;
    private boolean faceVerification;
    private String accountNumber;
    private BigDecimal balance;
    private int cif;


    public UserTable(){

    }

    public UserTable(String email){
        this.email = email;
    }


    public UserTable(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public UserGender getGender() {
        return gender;
    }

    public void setGender(UserGender gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLoginPasscode() {
        return loginPasscode;
    }

    public void setLoginPasscode(String loginPasscode) {
        this.loginPasscode = loginPasscode;
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

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public boolean isPhoneNumberVerified() {
        return phoneNumberVerified;
    }

    public void setPhoneNumberVerified(boolean phoneNumberVerified) {
        this.phoneNumberVerified = phoneNumberVerified;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isBvnVerified() {
        return bvnVerified;
    }

    public void setBvnVerified(boolean bvnVerified) {
        this.bvnVerified = bvnVerified;
    }

    public boolean isNinVerified() {
        return ninVerified;
    }

    public void setNinVerified(boolean ninVerified) {
        this.ninVerified = ninVerified;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getTranxPin() {
        return tranxPin;
    }

    public void setTranxPin(String tranxPin) {
        this.tranxPin = tranxPin;
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

    public Double getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(Double dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public Double getMonthlyLimit() {
        return monthlyLimit;
    }

    public void setMonthlyLimit(Double monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }

    public Double getDailyCumulative() {
        return dailyCumulative;
    }

    public void setDailyCumulative(Double dailyCumulative) {
        this.dailyCumulative = dailyCumulative;
    }

    public Double getMonthlyCumulative() {
        return monthlyCumulative;
    }

    public void setMonthlyCumulative(Double monthlyCumulative) {
        this.monthlyCumulative = monthlyCumulative;
    }

    public LocalDate getLastTransactionDate() {
        return lastTransactionDate;
    }

    public void setLastTransactionDate(LocalDate lastTransactionDate) {
        this.lastTransactionDate = lastTransactionDate;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public boolean isFaceVerification() {
        return faceVerification;
    }

    public void setFaceVerification(boolean faceVerification) {
        this.faceVerification = faceVerification;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getCif() {
        return cif;
    }

    public void setCif(int cif) {
        this.cif = cif;
    }

    public UserTable(UserData userData) {
        this.phoneNumber = userData.getPhoneNumber();
        this.firstName = userData.getFirstName();
        this.lastName = userData.getLastName();
        this.middleName = userData.getMiddleName();
        this.gender = userData.getGender();
        this.dateOfBirth = userData.getDateOfBirth();
        this.loginPasscode = userData.getLoginPasscode();
        //this.bvn = userData.getBvn();

    }

}
