package com.payvantage.ethicainternetbanking.controller;

import com.payvantage.ethicainternetbanking.data.dto.request.*;
import com.payvantage.ethicainternetbanking.data.dto.response.BaseResponse;
import com.payvantage.ethicainternetbanking.security.JWTHelper;
import com.payvantage.ethicainternetbanking.service.OnboardingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/ninVerification/{userUUID}")
    public ResponseEntity<?> ninVerification(@PathVariable String userUUID, @RequestBody NinRequest ninRequest) {
        return ResponseEntity.ok(onboardingService.ninVerification(userUUID, ninRequest));
    }

    @PostMapping("/signUpWithPhoneNumber/{userUUID}")
    public ResponseEntity<?> signUpPhoneNumber(@PathVariable String userUUID, @RequestBody SignUpWithPhoneNumberRequest initSignupPhoneNumber) {
        BaseResponse responseData = onboardingService.initializeSignUpWithPhoneNumber(userUUID, initSignupPhoneNumber.getPhoneNumber());
        HttpStatus httpStatus = (responseData.getStatusCode() == 200) ? HttpStatus.OK : HttpStatus.CREATED;
        return new ResponseEntity<>(responseData, httpStatus);
    }

    @PostMapping("/verifyPhoneNumber/{userUUID}")
    public ResponseEntity<?> verifyPhoneNumber(@PathVariable String userUUID, @RequestBody PhoneAndEmailVerificationRequest phoneAndEmailVerificationRequest) {
        BaseResponse responseData = onboardingService.verifyPhoneNumber(userUUID, phoneAndEmailVerificationRequest);
        HttpStatus httpStatus = (responseData.getStatusCode() == 200) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(responseData, httpStatus);
    }

    @PostMapping("/signUpWithEmailAddress/{userUUID}")
    public ResponseEntity<?> signUpWithEmailAddress(@PathVariable String userUUID, @RequestBody SignUpWithEmailRequest signUpWithEmailRequest) {
        BaseResponse responseData = onboardingService.initializeSignUpWithEmailAddress(userUUID, signUpWithEmailRequest.getEmailAddress());
        HttpStatus httpStatus = (responseData.getStatusCode() == 200) ? HttpStatus.OK : HttpStatus.CREATED;
        return new ResponseEntity<>(responseData, httpStatus);
    }

    @PostMapping("/verifyEmailAddress/{userUUID}")
    public ResponseEntity<?> verifyEmailAddress(@PathVariable String userUUID, @RequestBody PhoneAndEmailVerificationRequest phoneAndEmailVerificationRequest) {
        BaseResponse responseData = onboardingService.verifyEmail(userUUID, phoneAndEmailVerificationRequest);
        HttpStatus httpStatus = (responseData.getStatusCode() == 200) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(responseData, httpStatus);
    }

//    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
//    public ResponseEntity<Void> handleOptionsRequest() {
//        return ResponseEntity.ok().build();
//    }
}
