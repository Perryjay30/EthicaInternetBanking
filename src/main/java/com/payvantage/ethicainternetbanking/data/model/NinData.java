package com.payvantage.ethicainternetbanking.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
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
//    private String signature;
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

    public NinData() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthcountry() {
        return birthcountry;
    }

    public void setBirthcountry(String birthcountry) {
        this.birthcountry = birthcountry;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthlga() {
        return birthlga;
    }

    public void setBirthlga(String birthlga) {
        this.birthlga = birthlga;
    }

    public String getBirthstate() {
        return birthstate;
    }

    public void setBirthstate(String birthstate) {
        this.birthstate = birthstate;
    }

    public String getCentralID() {
        return centralID;
    }

    public void setCentralID(String centralID) {
        this.centralID = centralID;
    }

    public String getEducationallevel() {
        return educationallevel;
    }

    public void setEducationallevel(String educationallevel) {
        this.educationallevel = educationallevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public String getEmploymentstatus() {
        return employmentstatus;
    }

    public void setEmploymentstatus(String employmentstatus) {
        this.employmentstatus = employmentstatus;
    }

    public String getHeigth() {
        return heigth;
    }

    public void setHeigth(String heigth) {
        this.heigth = heigth;
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getTelephoneno() {
        return telephoneno;
    }

    public void setTelephoneno(String telephoneno) {
        this.telephoneno = telephoneno;
    }

    public String getResidence_address() {
        return residence_address;
    }

    public void setResidence_address(String residence_address) {
        this.residence_address = residence_address;
    }

    public String getResidence_lga() {
        return residence_lga;
    }

    public void setResidence_lga(String residence_lga) {
        this.residence_lga = residence_lga;
    }

    public String getResidence_state() {
        return residence_state;
    }

    public void setResidence_state(String residence_state) {
        this.residence_state = residence_state;
    }

    public String getResidence_town() {
        return residence_town;
    }

    public void setResidence_town(String residence_town) {
        this.residence_town = residence_town;
    }

    public String getResidencestatus() {
        return residencestatus;
    }

    public void setResidencestatus(String residencestatus) {
        this.residencestatus = residencestatus;
    }

    public String getSelf_origin_lga() {
        return self_origin_lga;
    }

    public void setSelf_origin_lga(String self_origin_lga) {
        this.self_origin_lga = self_origin_lga;
    }

    public String getSelf_origin_place() {
        return self_origin_place;
    }

    public void setSelf_origin_place(String self_origin_place) {
        this.self_origin_place = self_origin_place;
    }

    public String getSelf_origin_state() {
        return self_origin_state;
    }

    public void setSelf_origin_state(String self_origin_state) {
        this.self_origin_state = self_origin_state;
    }

    public String getSpoken_language() {
        return spoken_language;
    }

    public void setSpoken_language(String spoken_language) {
        this.spoken_language = spoken_language;
    }

    public String getNok_address1() {
        return nok_address1;
    }

    public void setNok_address1(String nok_address1) {
        this.nok_address1 = nok_address1;
    }

    public String getNok_address2() {
        return nok_address2;
    }

    public void setNok_address2(String nok_address2) {
        this.nok_address2 = nok_address2;
    }

    public String getNok_firstname() {
        return nok_firstname;
    }

    public void setNok_firstname(String nok_firstname) {
        this.nok_firstname = nok_firstname;
    }

    public String getNok_lga() {
        return nok_lga;
    }

    public void setNok_lga(String nok_lga) {
        this.nok_lga = nok_lga;
    }

    public String getNok_middlename() {
        return nok_middlename;
    }

    public void setNok_middlename(String nok_middlename) {
        this.nok_middlename = nok_middlename;
    }

    public String getNok_postalcode() {
        return nok_postalcode;
    }

    public void setNok_postalcode(String nok_postalcode) {
        this.nok_postalcode = nok_postalcode;
    }

    public String getNok_state() {
        return nok_state;
    }

    public void setNok_state(String nok_state) {
        this.nok_state = nok_state;
    }

    public String getNok_surname() {
        return nok_surname;
    }

    public void setNok_surname(String nok_surname) {
        this.nok_surname = nok_surname;
    }

    public String getNok_town() {
        return nok_town;
    }

    public void setNok_town(String nok_town) {
        this.nok_town = nok_town;
    }

    public String getOspokenlang() {
        return ospokenlang;
    }

    public void setOspokenlang(String ospokenlang) {
        this.ospokenlang = ospokenlang;
    }

    public String getPfirstname() {
        return pfirstname;
    }

    public void setPfirstname(String pfirstname) {
        this.pfirstname = pfirstname;
    }

    public String getPmiddlename() {
        return pmiddlename;
    }

    public void setPmiddlename(String pmiddlename) {
        this.pmiddlename = pmiddlename;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPsurname() {
        return psurname;
    }

    public void setPsurname(String psurname) {
        this.psurname = psurname;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getVnin() {
        return vnin;
    }

    public void setVnin(String vnin) {
        this.vnin = vnin;
    }

    public FaceData getFace_data() {
        return face_data;
    }

    public void setFace_data(FaceData face_data) {
        this.face_data = face_data;
    }

//    public String getSignature() {
//        return signature;
//    }
//
//    public void setSignature(String signature) {
//        this.signature = signature;
//    }

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
//        this.signature = ninData.signature;
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
