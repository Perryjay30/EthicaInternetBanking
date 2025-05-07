package com.payvantage.ethicainternetbanking.controller;

import com.payvantage.ethicainternetbanking.data.dto.request.BvnRequest;
import com.payvantage.ethicainternetbanking.service.IdentityVerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/onboarding")
public class OnboardingController {

    private final IdentityVerificationService identityVerificationService;

    public OnboardingController(IdentityVerificationService identityVerificationService) {
        this.identityVerificationService = identityVerificationService;
    }

    @PostMapping("/bvnVerification")
    public ResponseEntity<?> bvnVerification(@RequestBody BvnRequest bvnRequest) {
        return ResponseEntity.ok(identityVerificationService.verifyBvn(bvnRequest));
    }
}
