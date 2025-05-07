package com.payvantage.ethicainternetbanking.service;


import com.payvantage.ethicainternetbanking.data.dto.request.BvnRequest;
import com.payvantage.ethicainternetbanking.data.dto.request.NinRq;
import com.payvantage.ethicainternetbanking.data.dto.response.BvnResponse;
import com.payvantage.ethicainternetbanking.data.dto.response.NinResponse;

public interface IdentityVerificationService {

    BvnResponse verifyBvn(BvnRequest bvnRequest);

    NinResponse verifyNin(NinRq ninRq);
}
