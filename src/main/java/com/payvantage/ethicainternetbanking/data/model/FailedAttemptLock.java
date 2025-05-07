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

import static jakarta.persistence.TemporalType.TIMESTAMP;

/**
 *
 * @author gol
 */
@Entity
@Data
public class FailedAttemptLock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String username;
    private int counter;
    private Long nextActiveTime;
    @Temporal(TIMESTAMP)
    private Date lastAttempt;
    
   
    

   
    
}
