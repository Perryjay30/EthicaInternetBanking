package com.payvantage.ethicainternetbanking.data.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Map;

public class BvnResponse {
    @JsonProperty("status")
    private boolean status;
    @JsonProperty("detail")
    private String description;
    @JsonProperty("response_code")
    private String responseCode;
    @JsonProperty("data")
    private BvnRpData data;
    @JsonProperty("verification")
    private Verification verification;
    @JsonProperty("widget_info")
    private Map<String, Object> widgetInfo;
    @JsonProperty("session")
    private Map<String, Object> session;
    @JsonProperty("endpoint_name")
    private String endpointName;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public BvnRpData getData() {
        return data;
    }

    public void setData(BvnRpData data) {
        this.data = data;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }

    public Map<String, Object> getWidgetInfo() {
        return widgetInfo;
    }

    public void setWidgetInfo(Map<String, Object> widgetInfo) {
        this.widgetInfo = widgetInfo;
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


    public static class BvnRpData {
        private String firstName;
        private String lastName;
        private String middleName;
        private String dateOfBirth;
        private String phoneNumber;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
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
