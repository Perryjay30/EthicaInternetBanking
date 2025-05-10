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
@Data
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
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
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
    private String userUUID;


    public UserTable(){

    }

    public UserTable(String email){
        this.email = email;
    }


    public UserTable(Long id) {
        this.id = id;
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
