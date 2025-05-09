package com.payvantage.ethicainternetbanking.service;

import com.payvantage.ethicainternetbanking.data.dto.request.BvnVerificationRequest;
import com.payvantage.ethicainternetbanking.data.dto.request.NinRequest;
import com.payvantage.ethicainternetbanking.data.dto.request.PhoneAndEmailVerificationRequest;
import com.payvantage.ethicainternetbanking.data.dto.response.BaseResponse;

public interface OnboardingService {

    BaseResponse bvnVerification(BvnVerificationRequest bvnVerificationRequest);

    BaseResponse ninVerification(Long id, NinRequest ninRq);

    BaseResponse initializeSignUpWithPhoneNumber(Long id, String phoneNumber);

    BaseResponse verifyPhoneNumber(Long id, PhoneAndEmailVerificationRequest phoneAndEmailVerificationRequest);

    BaseResponse initializeSignUpWithEmailAddress(String emailAddress, Long id);

    BaseResponse verifyEmail(PhoneAndEmailVerificationRequest phoneAndEmailVerificationRequest);

}
