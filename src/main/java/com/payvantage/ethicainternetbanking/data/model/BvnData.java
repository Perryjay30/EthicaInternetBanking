package com.payvantage.ethicainternetbanking.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class BvnData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("customerId")
    Long id;
    private String bvn;
    private String firstName;
    private String lastName;
    private String middleName;
    private String dateOfBirth;
    private String phoneNumber;
}
