/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.payvantage.ethicainternetbanking.data.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

/**
 *
 * @author gol
 */
@Data
public class CommisionRpData {
    
    private BigDecimal totalAmount;
    @JsonIgnore
    private BigDecimal commission;
    @JsonIgnore
    private BigDecimal vat;
    private BigDecimal totalCommission;
    private BigDecimal amount;
}
