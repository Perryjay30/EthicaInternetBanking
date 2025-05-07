package com.payvantage.ethicainternetbanking.controller;

import com.payvantage.ethicainternetbanking.data.dto.request.BvnVerificationRequest;
import com.payvantage.ethicainternetbanking.service.OnboardingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/onboarding")
public class OnboardingController {

    private final OnboardingService onboardingService;

    public OnboardingController(OnboardingService onboardingService) {
        this.onboardingService = onboardingService;
    }

    @PostMapping("/bvnVerification")
    public ResponseEntity<?> bvnVerification(@RequestBody BvnVerificationRequest bvnVerificationRequest) {
        return ResponseEntity.ok(onboardingService.bvnVerification(bvnVerificationRequest));
    }
}
