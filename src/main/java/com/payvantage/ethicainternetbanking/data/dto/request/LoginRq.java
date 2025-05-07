/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.payvantage.ethicainternetbanking.data.dto.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.payvantage.ethicainternetbanking.data.model.UserTable;
import jakarta.persistence.Temporal;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import static jakarta.persistence.TemporalType.TIMESTAMP;

/**
 *
 * @author mac
 */

@Data
public class LoginRq implements Serializable {
    
    private String phoneNumber;
    private String loginPasscode;
    private String deviceId;
    @JsonIgnore
    @Temporal(TIMESTAMP)
    private Date lastLogin = new Date();
    
    
    public LoginRq(){
        
    }
    
    public LoginRq(UserTable users){
        this.phoneNumber = users.getPhoneNumber();
        this.loginPasscode = users.getLoginPasscode();
    }
    
}
