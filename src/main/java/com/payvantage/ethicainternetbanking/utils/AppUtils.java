package com.payvantage.ethicainternetbanking.utils;

import com.payvantage.ethicainternetbanking.data.dto.response.BaseResponse;
import com.payvantage.ethicainternetbanking.data.model.OtpEntity;
import com.payvantage.ethicainternetbanking.repository.OtpRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import static com.payvantage.ethicainternetbanking.utils.AppConstant.*;

@Service
public class AppUtils {

    private final OtpRepository otpRepository;

    private final AppConfig appConfig;

    public AppUtils(OtpRepository otpRepository, AppConfig appConfig) {
        this.otpRepository = otpRepository;
        this.appConfig = appConfig;
    }

    public BaseResponse generateToken(String username, int expiryTime) {
        BaseResponse baseResponse = new BaseResponse();
        int statusCode = AppConstant.DEFAULT_STATUS_CODE;
        String statusMessage = AppConstant.DEFAULT_STATUS_MESSAGE;
        try {
            Optional<OtpEntity> optOtp = otpRepository.findByUsername(username);
            OtpEntity otpEntity = (optOtp.isPresent()) ? optOtp.get() : new OtpEntity();
            otpEntity.setDateCreate(new Date());
            otpEntity.setUsername(username);
            String otp = getRandomNumberInts(6);
//            String encOtp = appConfig.passwordEncoder().encode(otp);
            System.out.println(otp);
            otpEntity.setOtp(otp);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, expiryTime);
            Long expiry = cal.getTimeInMillis();
            otpEntity.setExpiryTime(expiry);
            Long requestId = otpRepository.save(otpEntity).getId();
            Map map = new HashMap();
            map.put("requestId", requestId);
            map.put("otp", otp);
            baseResponse.setData(map);
            baseResponse.setStatusCode(SUCCESS_STATUS_CODE);
            baseResponse.setDescription(SUCCESS_STATUS_MESSAGE);
            return baseResponse;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return baseResponse;
    }

    public String maskedPhoneNumber(String phoneNumber) {
        int i = 0;
        StringBuffer temp = new StringBuffer();
        while (i < (phoneNumber.length())) {
            if (i > phoneNumber.length() - 4) {
                temp.append(phoneNumber.charAt(i));
            } else {
                temp.append("X");
            }
            i++;
        }
        return temp.toString();
    }

    public String maskedEmail(String email) {
        int atIndex = email.indexOf('@');
        if (atIndex <= 1) {
            // If the local part is too short to mask, return as is
            return email;
        }
        StringBuffer maskedEmail = new StringBuffer();
        // Keep the first character
        maskedEmail.append(email.charAt(0));
        for (int i = 1; i < atIndex - 1; i++) {
            maskedEmail.append("X");
        }
        maskedEmail.append(email.charAt(atIndex - 1));
        maskedEmail.append(email.substring(atIndex));
        return maskedEmail.toString();
    }

    public String cleanText(String text) {
        // strips off all non-ASCII characters
        text = text.replaceAll("[^\\x00-\\x7F]", "");
        // erases all the ASCII control characters
        text = text.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");
        // removes non-printable characters from Unicode
        text = text.replaceAll("\\p{C}", "");
        return text.trim();
    }
}
