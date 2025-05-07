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

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }
}
