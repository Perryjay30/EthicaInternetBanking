package com.payvantage.ethicainternetbanking.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payvantage.ethicainternetbanking.data.dto.request.BvnRequest;
import com.payvantage.ethicainternetbanking.data.dto.request.NinRq;
import com.payvantage.ethicainternetbanking.data.dto.response.BvnResponse;
import com.payvantage.ethicainternetbanking.data.dto.response.NinResponse;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Service
public class IdentityVerificationServiceImpl implements IdentityVerificationService {

    private final Environment environment;

    public IdentityVerificationServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public BvnResponse verifyBvn(BvnRequest bvnRequest) {
        BvnResponse bvnResponse = new BvnResponse();
        try {
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();

            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = "number=" + URLEncoder.encode(bvnRequest.getBvn(), StandardCharsets.UTF_8);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(environment.getProperty("prembly.base.url") + "identitypass/verification/bvn_validation"))
                    .header("accept", "application/json")
                    .header("x-api-key", environment.getProperty("prembly.x.api.key"))
                    .header("app-id", environment.getProperty("prembly.app.id"))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Success response body for Bvn Verification: " + response.body());
                String responseBody = response.body();
                return objectMapper.readValue(responseBody, BvnResponse.class);
            } else {
                bvnResponse.setDescription("Unable to verify bvn!!");
                bvnResponse.setResponseCode("400");
                return bvnResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            bvnResponse.setResponseCode("400");
            bvnResponse.setDescription("An error occurred while consuming the API: " + e.getMessage());
            return bvnResponse;
        }

    }

    @Override
    public NinResponse verifyNin(NinRq ninRq) {
        NinResponse ninResponse = new NinResponse();
        try {
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();

            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = "image=" + ninRq.getImage() + "&number=" + ninRq.getNumber();

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(environment.getProperty("prembly.base.url") + "identitypass/verification/nin_w_face"))
                    .header("accept", "application/json")
                    .header("x-api-key", environment.getProperty("prembly.x.api.key"))
                    .header("app-id", environment.getProperty("prembly.app.id"))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println("Success response body for Nin Verification: " + response.body());
                String responseBody = response.body();
                return objectMapper.readValue(responseBody, NinResponse.class);
            } else {
                ninResponse.setDetail("Unable to verify nin!!");
                ninResponse.setResponseCode("400");
                return ninResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ninResponse.setResponseCode("400");
            ninResponse.setDetail("An error occurred while consuming the API: " + e.getMessage());
            return ninResponse;
        }
    }
}
