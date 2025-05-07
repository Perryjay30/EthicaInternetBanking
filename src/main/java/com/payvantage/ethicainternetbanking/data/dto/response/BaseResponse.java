/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.payvantage.ethicainternetbanking.data.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author taofeek
 * 
 */

@Data
public class BaseResponse implements Serializable {
    
    public int statusCode;
    public String description;
    public Object data;
    public Object errors;
    @JsonIgnore
    public String username;
    @JsonIgnore
    private String fufilmentResponseCode;
    @JsonIgnore
    private String externalRefrence;
    @JsonIgnore
    private boolean pending;
    @JsonIgnore
    private CommisionRpData commisionRpData;
    
}
