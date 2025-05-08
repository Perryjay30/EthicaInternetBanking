package com.payvantage.ethicainternetbanking.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class FaceData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private boolean status;
    private String response_code;
    private String message;
    private Double confidence;
    @JsonIgnore
    private String bvn;
    @JsonIgnore
    private String nin;

}
