package com.payvantage.ethicainternetbanking.service;

import com.payvantage.ethicainternetbanking.data.dto.request.BvnVerificationRequest;
import com.payvantage.ethicainternetbanking.data.dto.request.NinRequest;
import com.payvantage.ethicainternetbanking.data.dto.response.BaseResponse;

public interface OnboardingService {

    BaseResponse bvnVerification(BvnVerificationRequest bvnVerificationRequest);

    BaseResponse ninVerification(Long id, NinRequest ninRq);
}
