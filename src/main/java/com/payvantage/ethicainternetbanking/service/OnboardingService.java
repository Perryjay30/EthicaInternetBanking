package com.payvantage.ethicainternetbanking.service;

import com.payvantage.ethicainternetbanking.data.dto.request.BvnVerificationRequest;
import com.payvantage.ethicainternetbanking.data.dto.request.NinRequest;
import com.payvantage.ethicainternetbanking.data.dto.request.PhoneAndEmailVerificationRequest;
import com.payvantage.ethicainternetbanking.data.dto.response.BaseResponse;

public interface OnboardingService {

    BaseResponse bvnVerification(BvnVerificationRequest bvnVerificationRequest);

    BaseResponse ninVerification(String userUUID, NinRequest ninRq);

    BaseResponse initializeSignUpWithPhoneNumber(String userUUID, String phoneNumber);

    BaseResponse verifyPhoneNumber(String userUUID, PhoneAndEmailVerificationRequest phoneAndEmailVerificationRequest);

    BaseResponse initializeSignUpWithEmailAddress(String userUUID, String emailAddress);

    BaseResponse verifyEmail(String userUUID, PhoneAndEmailVerificationRequest phoneAndEmailVerificationRequest);

}
