package com.payvantage.ethicainternetbanking.service;

import com.payvantage.ethicainternetbanking.data.dto.request.BvnRequest;
import com.payvantage.ethicainternetbanking.data.dto.request.BvnVerificationRequest;
import com.payvantage.ethicainternetbanking.data.dto.request.NinRequest;
import com.payvantage.ethicainternetbanking.data.dto.request.NinRq;
import com.payvantage.ethicainternetbanking.data.dto.response.BaseResponse;
import com.payvantage.ethicainternetbanking.data.dto.response.BvnResponse;
import com.payvantage.ethicainternetbanking.data.dto.response.NinResponse;
import com.payvantage.ethicainternetbanking.data.model.*;
import com.payvantage.ethicainternetbanking.repository.BvnDataRepository;
import com.payvantage.ethicainternetbanking.repository.FaceDataRepository;
import com.payvantage.ethicainternetbanking.repository.NinDataRepository;
import com.payvantage.ethicainternetbanking.repository.UserRepository;
import com.payvantage.ethicainternetbanking.security.JWTHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Service
@Slf4j
public class OnboardingServiceImpl implements OnboardingService {

    private final BvnDataRepository bvnDataRepository;

    private final UserRepository userRepository;

    private final JWTHelper jwtHelper;

    private final NinDataRepository ninDataRepository;

    private final FaceDataRepository faceDataRepository;

    private final IdentityVerificationService identityVerificationService;

    public OnboardingServiceImpl(BvnDataRepository bvnDataRepository, UserRepository userRepository, JWTHelper jwtHelper, NinDataRepository ninDataRepository, FaceDataRepository faceDataRepository, IdentityVerificationService identityVerificationService) {
        this.bvnDataRepository = bvnDataRepository;
        this.userRepository = userRepository;
        this.jwtHelper = jwtHelper;
        this.ninDataRepository = ninDataRepository;
        this.faceDataRepository = faceDataRepository;
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
            log.info("An error occurred while verifying bvn {}", ex.getMessage());
            baseResponse.setDescription("Unable to verify BVN, try again later");
            baseResponse.setStatusCode(400);
        }
        return baseResponse;
    }

    @Override
    public BaseResponse ninVerification(Long id, NinRequest ninRq) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(500);
        baseResponse.setDescription("An error occurred");
        try {
            String nin = ninRq.getNin();
            System.out.println(nin);

            if (nin.isEmpty()) {
                baseResponse.setStatusCode(400);
                baseResponse.setDescription("nin cannot be empty");
                return baseResponse;
            }

            NinRq ninRq1 = new NinRq();
            ninRq1.setNumber(ninRq.getNin());
            ninRq1.setImage(ninRq.getImage());

            NinData ninRpData = null;
            Optional<NinData> optNinRpData = ninDataRepository.findByNin(ninRq1.getNumber());
            Optional<UserTable> optionalUserTable = userRepository.findById(id);
            if (optionalUserTable.isPresent() && optionalUserTable.get().getNin() != null && optionalUserTable.get().getTranxPin() != null) {
                baseResponse.setDescription("NIN already linked to a user, kindly proceed to login if you're the user!!");
                baseResponse.setStatusCode(200);
                return baseResponse;
            } else if (optionalUserTable.isPresent() && optionalUserTable.get().getNin() != null && optionalUserTable.get().getTranxPin() == null) {
                baseResponse.setDescription("NIN already verified, Verify your phone number!!");
                Map<String, String> resp = new HashMap<>();
                String auth = jwtHelper.createShortLiveToken(String.valueOf(optionalUserTable.get().getId()), optionalUserTable.get().getNin(), 30);
                resp.put("jwt", auth);
                baseResponse.setData(resp);
                baseResponse.setStatusCode(200);
                return baseResponse;
            }

            boolean faceVerification;
            if (optNinRpData.isEmpty()) {
                NinResponse ninRp = identityVerificationService.verifyNin(ninRq1);
                System.out.println(ninRp.toString());
                ninRpData = new NinData(ninRp.getNin_data());
                ninDataRepository.save(ninRpData);
                FaceData faceData = new FaceData();
                faceData.setNin(nin);
                faceVerification = faceData.isStatus();
                faceData.setConfidence(ninRp.getFace_data().getConfidence());
                faceData.setMessage(ninRp.getFace_data().getMessage());
                faceDataRepository.save(faceData);
                baseResponse.setDescription("NIN " + ninRp.getVerification().getStatus());
            } else {
                baseResponse.setStatusCode(400);
                baseResponse.setDescription("NIN already verified, Verify your phone number!!");
                return baseResponse;
            }

            UserTable existingUser = optionalUserTable.get();
            UserGender gender = Objects.equals(ninRpData.getGender(), "m") ? UserGender.MALE : UserGender.FEMALE;
            existingUser.setNin(nin);
            existingUser.setFaceVerification(faceVerification);
            existingUser.setNinVerified(true);
            existingUser.setGender(gender);
            existingUser.setResidentialAddress(ninRpData.getResidence_address());
            userRepository.save(existingUser);

            Map<String, String> resp = new HashMap<>();
            String auth = jwtHelper.createShortLiveToken(String.valueOf(existingUser.getId()), existingUser.getNin(), 30);
            baseResponse.setDescription("NIN verified successfully");
            resp.put("jwt", auth);
            baseResponse.setData(resp);
            baseResponse.setStatusCode(200);
            return baseResponse;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("An error occurred while verifying nin {}", ex.getMessage());
            baseResponse.setDescription("Unable to verify NIN, try again later");
        }

        return baseResponse;
    }
}
