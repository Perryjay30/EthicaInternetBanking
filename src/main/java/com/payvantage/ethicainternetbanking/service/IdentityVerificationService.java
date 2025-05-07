package com.payvantage.ethicainternetbanking.service;


import com.payvantage.ethicainternetbanking.data.dto.request.BvnRequest;
import com.payvantage.ethicainternetbanking.data.dto.response.BvnResponse;

public interface IdentityVerificationService {

    BvnResponse verifyBvn(BvnRequest bvnRequest);
}
