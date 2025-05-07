/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.payvantage.ethicainternetbanking.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author HRH
 */
@Entity
@Table(name = "otp_tmp_password")
@Data//golad_appconfig
public class OtpEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String otp;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    private Long expiryTime;
    private String rqData;
    private String username;
    
    
    public OtpEntity(){
        
    }

    
}
