package com.payvantage.ethicainternetbanking.controller;

import com.payvantage.ethicainternetbanking.data.dto.request.*;
import com.payvantage.ethicainternetbanking.data.dto.response.BaseResponse;
import com.payvantage.ethicainternetbanking.security.JWTHelper;
import com.payvantage.ethicainternetbanking.service.OnboardingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/onboarding")
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

    @PostMapping("/signUpWithPhoneNumber")
    public ResponseEntity<?> signUpPhoneNumber(@Valid @RequestHeader("authorization") String authorization, @RequestBody SignUpWithPhoneNumberRequest initSignupPhoneNumber) {
        Long id = Long.valueOf(jwtHelper.getClaim(authorization, "userId"));
        BaseResponse responseData = onboardingService.initializeSignUpWithPhoneNumber(id, initSignupPhoneNumber.getPhoneNumber());
        HttpStatus httpStatus = (responseData.getStatusCode() == 200) ? HttpStatus.OK : HttpStatus.CREATED;
        return new ResponseEntity<>(responseData, httpStatus);
    }

    @PostMapping("/verifyPhoneNumber")
    public ResponseEntity<?> verifyPhoneNumber(@Valid @RequestHeader("authorization") String authorization, @RequestBody PhoneAndEmailVerificationRequest phoneAndEmailVerificationRequest) {
        Long id = Long.valueOf(jwtHelper.getClaim(authorization, "userId"));
        BaseResponse responseData = onboardingService.verifyPhoneNumber(id, phoneAndEmailVerificationRequest);
        HttpStatus httpStatus = (responseData.getStatusCode() == 200) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(responseData, httpStatus);
    }

    @PostMapping("/signUpWithEmailAddress")
    public ResponseEntity<?> signUpWithEmailAddress(@Valid @RequestHeader("authorization") String authorization, @RequestBody SignUpWithEmailRequest signUpWithEmailRequest) {
        Long id = Long.valueOf(jwtHelper.getClaim(authorization, "userId"));
        BaseResponse responseData = onboardingService.initializeSignUpWithEmailAddress(signUpWithEmailRequest.getEmailAddress(), id);
        HttpStatus httpStatus = (responseData.getStatusCode() == 200) ? HttpStatus.OK : HttpStatus.CREATED;
        return new ResponseEntity<>(responseData, httpStatus);
    }

    @PostMapping("/verifyEmailAddress")
    public ResponseEntity<?> verifyEmailAddress(@Valid @RequestBody PhoneAndEmailVerificationRequest phoneAndEmailVerificationRequest) {
        BaseResponse responseData = onboardingService.verifyEmail(phoneAndEmailVerificationRequest);
        HttpStatus httpStatus = (responseData.getStatusCode() == 200) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(responseData, httpStatus);
    }

//    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
//    public ResponseEntity<Void> handleOptionsRequest() {
//        return ResponseEntity.ok().build();
//    }
}
