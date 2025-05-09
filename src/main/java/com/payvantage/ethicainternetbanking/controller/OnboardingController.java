package com.payvantage.ethicainternetbanking.controller;

import com.payvantage.ethicainternetbanking.data.dto.request.BvnVerificationRequest;
import com.payvantage.ethicainternetbanking.data.dto.request.NinRequest;
import com.payvantage.ethicainternetbanking.security.JWTHelper;
import com.payvantage.ethicainternetbanking.service.OnboardingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/onboarding")
@CrossOrigin(origins = "https://internetbanking.payvantageapi.com", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class OnboardingController {

    private final OnboardingService onboardingService;

    private final JWTHelper jwtHelper;

    public OnboardingController(OnboardingService onboardingService, JWTHelper jwtHelper) {
        this.onboardingService = onboardingService;
        this.jwtHelper = jwtHelper;
    }

    @PostMapping("/bvnVerification")
    public ResponseEntity<?> bvnVerification(@RequestBody BvnVerificationRequest bvnVerificationRequest) {
        return ResponseEntity.ok(onboardingService.bvnVerification(bvnVerificationRequest));
    }

    @PostMapping("/ninVerification")
    public ResponseEntity<?> ninVerification(@Valid @RequestHeader String authorization, @RequestBody NinRequest ninRequest) {
        Long id = Long.valueOf(jwtHelper.getClaim(authorization, "userId"));
        return ResponseEntity.ok(onboardingService.ninVerification(id, ninRequest));
    }
}
