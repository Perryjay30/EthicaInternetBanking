package com.payvantage.ethicainternetbanking.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.payvantage.ethicainternetbanking.data.dto.response.NotificationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.logging.Logger;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Value("${ethica.cba.channelId}")
    private String channelID;

    @Value("${ethica.cba.xApiKey}")
    private String x_api_key;

    @Value("${ethica.cba.notification}")
    private String notification_url;


    private static final Logger LOG = Logger.getLogger(NotificationServiceImpl.class.getName());

    @Override
    public NotificationResponse sendSms(String receiverPhone, String otp) {
        try {
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();

            JsonObject requestJson = new JsonObject();
            requestJson.addProperty("emailActive", false);
            JsonObject emailDto = new JsonObject();
            emailDto.addProperty("attachment", "");
            emailDto.addProperty("attachmentName", "");
            JsonObject emailPlaceholders = new JsonObject();
            emailPlaceholders.addProperty("otp", "86043");
            emailDto.add("placeholders", emailPlaceholders);
            emailDto.addProperty("receiverEmail", "otaiwo@payvantage.com.ng");
            emailDto.addProperty("templateName", "MobileEmail");
            requestJson.add("emailDto", emailDto);

            requestJson.addProperty("pushNotificationActive", false);
            JsonObject pushNotificationDto = new JsonObject();
            pushNotificationDto.addProperty("body", "<string>");
            pushNotificationDto.addProperty("provider", "<string>");
            pushNotificationDto.addProperty("title", "<string>");
            requestJson.add("pushNotificationDto", pushNotificationDto);

            JsonObject sms = new JsonObject();
            sms.addProperty("receiverPhone", receiverPhone);
            sms.addProperty("smsTemplateId", "WELCOME");
            JsonObject smsPlaceholders = new JsonObject();
            smsPlaceholders.addProperty("otp", otp);
            sms.add("placeholders", smsPlaceholders);
            requestJson.add("sms", sms);

            requestJson.addProperty("smsActive", true);

            String jsonBody = requestJson.toString();

            java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                    .uri(URI.create(notification_url))
                    .timeout(Duration.ofSeconds(10))
                    .header("Content-Type", "application/json")
                    .header("channel-id", channelID)
                    .header("x-api-key", x_api_key)
                    .POST(java.net.http.HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            LOG.info(response.body());
            return gson.fromJson(response.body(), NotificationResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public NotificationResponse sendEmail(String emailAddress, String otp) {
        try {
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();

            JsonObject requestJson = new JsonObject();
            requestJson.addProperty("emailActive", true);
            JsonObject emailDto = new JsonObject();
            emailDto.addProperty("attachment", "");
            emailDto.addProperty("attachmentName", "");
            JsonObject emailPlaceholders = new JsonObject();
            emailPlaceholders.addProperty("otp", otp);
            emailDto.add("placeholders", emailPlaceholders);
            emailDto.addProperty("receiverEmail", emailAddress);
            emailDto.addProperty("templateName", "MobileEmail");
            requestJson.add("emailDto", emailDto);

            requestJson.addProperty("pushNotificationActive", false);
            JsonObject pushNotificationDto = new JsonObject();
            pushNotificationDto.addProperty("body", "<string>");
            pushNotificationDto.addProperty("provider", "<string>");
            pushNotificationDto.addProperty("title", "<string>");
            requestJson.add("pushNotificationDto", pushNotificationDto);

            JsonObject sms = new JsonObject();
            sms.addProperty("receiverPhone", "");
            sms.addProperty("smsTemplateId", "WELCOME");
            JsonObject smsPlaceholders = new JsonObject();
            smsPlaceholders.addProperty("otp", "");
            sms.add("placeholders", smsPlaceholders);
            requestJson.add("sms", sms);

            requestJson.addProperty("smsActive", false);

            String jsonBody = requestJson.toString();

            java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                    .uri(URI.create(notification_url))
                    .timeout(Duration.ofSeconds(10))
                    .header("Content-Type", "application/json")
                    .header("channel-id", channelID)
                    .header("x-api-key", x_api_key)
                    .POST(java.net.http.HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            LOG.info(response.body());
            return gson.fromJson(response.body(), NotificationResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
