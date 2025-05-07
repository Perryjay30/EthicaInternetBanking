package com.payvantage.ethicainternetbanking.service;

import com.payvantage.ethicainternetbanking.data.dto.request.BvnRequest;
import com.payvantage.ethicainternetbanking.data.dto.request.BvnVerificationRequest;
import com.payvantage.ethicainternetbanking.data.dto.response.BaseResponse;
import com.payvantage.ethicainternetbanking.data.dto.response.BvnResponse;
import com.payvantage.ethicainternetbanking.data.model.BvnData;
import com.payvantage.ethicainternetbanking.data.model.UserTable;
import com.payvantage.ethicainternetbanking.repository.BvnDataRepository;
import com.payvantage.ethicainternetbanking.repository.UserRepository;
import com.payvantage.ethicainternetbanking.security.JWTHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class OnboardingServiceImpl implements OnboardingService {

    private final BvnDataRepository bvnDataRepository;

    private final UserRepository userRepository;

    private final JWTHelper jwtHelper;
    private final IdentityVerificationService identityVerificationService;

    public OnboardingServiceImpl(BvnDataRepository bvnDataRepository, UserRepository userRepository, JWTHelper jwtHelper, IdentityVerificationService identityVerificationService) {
        this.bvnDataRepository = bvnDataRepository;
        this.userRepository = userRepository;
        this.jwtHelper = jwtHelper;
        this.identityVerificationService = identityVerificationService;
    }

    @Override
    public BaseResponse bvnVerification(BvnVerificationRequest bvnVerificationRequest) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            String bvn = bvnVerificationRequest.getBvn();

            if (bvn == null || bvn.isEmpty()) {
                baseResponse.setStatusCode(400);
                baseResponse.setDescription("BVN cannot be empty");
                return baseResponse;
            }

            LocalDate inputDob = null;
            if (bvnVerificationRequest.getDateOfBirth() != null && !bvnVerificationRequest.getDateOfBirth().isBlank()) {
                try {
                    inputDob = LocalDate.parse(
                            bvnVerificationRequest.getDateOfBirth(),
                            DateTimeFormatter.ISO_LOCAL_DATE
                    );
                } catch (DateTimeParseException e) {
                    baseResponse.setStatusCode(400);
                    baseResponse.setDescription("Date of birth must be in yyyy-MM-dd format");
                    return baseResponse;
                }
            }

            BvnRequest bvnRequest = new BvnRequest();
            bvnRequest.setBvn(bvn);

            BvnData bvnData = new BvnData();
            Optional<BvnData> optionalBvnData = bvnDataRepository.findByBvn(bvn);
            Optional<UserTable> existingUserTable = userRepository.findByBvn(bvn);
            if (existingUserTable.isPresent() && existingUserTable.get().getTranxPin() != null) {
                baseResponse.setDescription("BVN already linked to a user, kindly proceed to login if you're the user!!");
                baseResponse.setStatusCode(200);
                return baseResponse;
            } else if (existingUserTable.isPresent() && existingUserTable.get().getTranxPin() == null) {
                baseResponse.setDescription("BVN already verified, Verify your NIN!!");
                Map<String, String> resp = new HashMap<>();
                String auth = jwtHelper.createShortLiveToken(String.valueOf(existingUserTable.get().getId()), existingUserTable.get().getBvn(), 30);
                resp.put("jwt", auth);
                baseResponse.setData(resp);
                baseResponse.setStatusCode(200);
                return baseResponse;
            }

            if(optionalBvnData.isEmpty()) {
                BvnResponse bvnResponse = identityVerificationService.verifyBvn(bvnRequest);
                if(bvnResponse != null && bvnResponse.isStatus()) {
                    LocalDate returnedDob = LocalDate.parse(
                            bvnResponse.getData().getDateOfBirth(),
                            DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH)
                    );

                    if(inputDob != null && !inputDob.equals(returnedDob)) {
                        baseResponse.setStatusCode(400);
                        baseResponse.setDescription("Your date of birth doesn't match the one on record");
                        return baseResponse;
                    }

                    bvnData.setBvn(bvnRequest.getBvn());
                    bvnData.setFirstName(bvnResponse.getData().getFirstName());
                    bvnData.setLastName(bvnResponse.getData().getLastName());
                    bvnData.setMiddleName(bvnResponse.getData().getMiddleName());
                    bvnData.setPhoneNumber(bvnResponse.getData().getPhoneNumber());
                    bvnData.setDateOfBirth(bvnResponse.getData().getDateOfBirth());
                    bvnDataRepository.save(bvnData);
                } else {
                    baseResponse.setStatusCode(400);
                    baseResponse.setDescription("BVN verification failed");
                    return baseResponse;
                }
            } else {
                baseResponse.setStatusCode(400);
                baseResponse.setDescription("BVN already verified, Verify your NIN!!");
                return baseResponse;
            }

            UserTable newUserTable = new UserTable();
            newUserTable.setBvn(bvnRequest.getBvn());
            newUserTable.setFirstName(bvnData.getFirstName());
            newUserTable.setLastName(bvnData.getLastName());
            newUserTable.setMiddleName(bvnData.getMiddleName());
            newUserTable.setBvnVerified(true);
            newUserTable.setDateOfBirth(bvnData.getDateOfBirth());
            userRepository.save(newUserTable);

            Map<String, String> resp = new HashMap<>();
            String auth = jwtHelper.createShortLiveToken(String.valueOf(newUserTable.getId()), newUserTable.getBvn(), 30);
            baseResponse.setDescription("BVN verified successfully");
            resp.put("jwt", auth);
            baseResponse.setData(resp);
            baseResponse.setStatusCode(200);
            return baseResponse;
        } catch (Exception ex) {
            ex.printStackTrace();
//            log.info("An error occurred while verifying bvn {}", ex.getMessage());
            baseResponse.setDescription("Unable to verify BVN, try again later");
            baseResponse.setStatusCode(400);
        }
        return baseResponse;
    }
}
