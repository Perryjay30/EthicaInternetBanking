package com.payvantage.ethicainternetbanking.data.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payvantage.ethicainternetbanking.data.model.FaceData;
import com.payvantage.ethicainternetbanking.data.model.NinData;

import java.util.Map;

public class NinResponse {

    private boolean status;
    private String detail;

    @JsonProperty("response_code")
    private String responseCode;

    @JsonProperty("nin_data")
    private NinData nin_data;

    @JsonProperty("face_data")
    private FaceData face_data;

    private Verification verification;

    private Map<String, Object> session;

    @JsonProperty("endpoint_name")
    private String endpointName;

    // Getters and Setters

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public NinData getNinData() {
        return nin_data;
    }

    public void setNinData(NinData ninData) {
        this.nin_data = ninData;
    }

    public FaceData getFace_data() {
        return face_data;
    }

    public void setFace_data(FaceData face_data) {
        this.face_data = face_data;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getEndpointName() {
        return endpointName;
    }

    public void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
    }

    public static class Verification {
        private String status;
        private String reference;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }
    }
}