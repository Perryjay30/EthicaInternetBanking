package com.payvantage.ethicainternetbanking.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NinData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("customerId")
    Long id;
    private String title;
    private String surname;
    private String firstname;
    private String gender;
    private String birthcountry;
    private String birthdate;
    private String birthlga;
    private String birthstate;
    private String centralID;
    private String educationallevel;
    private String email;
    private String nin;
    private String employmentstatus;
    private String heigth;
    private String maritalstatus;
    @Column(name = "message", columnDefinition = "TEXT")
    private String photo;
    private String middlename;
    private String religion;
    private String telephoneno;
    private String residence_address;
    private String residence_lga;
    private String residence_state;
    private String residence_town;
    private String residencestatus;
    private String self_origin_lga;
    private String self_origin_place;
    private String self_origin_state;
    @JsonIgnore
    private String signature;
    private String spoken_language;
    private String nok_address1;
    private String nok_address2;
    private String nok_firstname;
    private String nok_lga;
    private String nok_middlename;
    private String nok_postalcode;
    private String nok_state;
    private String nok_surname;
    private String nok_town;
    private String ospokenlang;
    private String pfirstname;
    private String pmiddlename;
    private String profession;
    private String psurname;
    private String trackingId;
    private String userid;
    private String vnin;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "face_data_id", referencedColumnName = "id")
    private FaceData face_data;

    public NinData(NinData ninData) {
        this.id = ninData.id;
        this.title = ninData.title;
        this.surname = ninData.surname;
        this.firstname = ninData.firstname;
        this.gender = ninData.gender;
        this.birthcountry = ninData.birthcountry;
        this.birthdate = ninData.birthdate;
        this.birthlga = ninData.birthlga;
        this.birthstate = ninData.birthstate;
        this.centralID = ninData.centralID;
        this.educationallevel = ninData.educationallevel;
        this.email = ninData.email;
        this.nin = ninData.nin;
        this.employmentstatus = ninData.employmentstatus;
        this.heigth = ninData.heigth;
        this.maritalstatus = ninData.maritalstatus;
        this.photo = ninData.photo;
        this.middlename = ninData.middlename;
        this.religion = ninData.religion;
        this.telephoneno = ninData.telephoneno;
        this.residence_address = ninData.residence_address;
        this.residence_lga = ninData.residence_lga;
        this.residence_state = ninData.residence_state;
        this.residence_town = ninData.residence_town;
        this.residencestatus = ninData.residencestatus;
        this.self_origin_lga = ninData.self_origin_lga;
        this.self_origin_place = ninData.self_origin_place;
        this.self_origin_state = ninData.self_origin_state;
        this.signature = ninData.signature;
        this.spoken_language = ninData.spoken_language;
        this.nok_address1 = ninData.nok_address1;
        this.nok_address2 = ninData.nok_address2;
        this.nok_firstname = ninData.nok_firstname;
        this.nok_lga = ninData.nok_lga;
        this.nok_middlename = ninData.nok_middlename;
        this.nok_postalcode = ninData.nok_postalcode;
        this.nok_state = ninData.nok_state;
        this.nok_surname = ninData.nok_surname;
        this.nok_town = ninData.nok_town;
        this.ospokenlang = ninData.ospokenlang;
        this.pfirstname = ninData.pfirstname;
        this.pmiddlename = ninData.pmiddlename;
        this.profession = ninData.profession;
        this.psurname = ninData.psurname;
        this.trackingId = ninData.trackingId;
        this.userid = ninData.userid;
        this.vnin = ninData.vnin;
        this.face_data = ninData.face_data;
    }
}
