package com.payvantage.ethicainternetbanking.data.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payvantage.ethicainternetbanking.data.model.FaceData;
import com.payvantage.ethicainternetbanking.data.model.NinData;
import lombok.Data;

import java.util.Map;

@Data
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

    @JsonProperty("widget_info")
    private Map<String, Object> widgetInfo;

    @JsonProperty("endpoint_name")
    private String endpointName;

    @Data
    public static class Verification {
        private String status;
        private String reference;
    }
}