package com.payvantage.ethicainternetbanking.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.payvantage.ethicainternetbanking.data.dto.request.*;
import com.payvantage.ethicainternetbanking.data.dto.response.BaseResponse;
import com.payvantage.ethicainternetbanking.data.dto.response.BvnResponse;
import com.payvantage.ethicainternetbanking.data.dto.response.NinResponse;
import com.payvantage.ethicainternetbanking.data.model.*;
import com.payvantage.ethicainternetbanking.repository.*;
import com.payvantage.ethicainternetbanking.security.JWTHelper;
import com.payvantage.ethicainternetbanking.security.JwtService;
import com.payvantage.ethicainternetbanking.utils.AppConfig;
import com.payvantage.ethicainternetbanking.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static com.payvantage.ethicainternetbanking.utils.AppConstant.*;

@Service
@Slf4j
public class OnboardingServiceImpl implements OnboardingService {

    private final BvnDataRepository bvnDataRepository;

    private final UserRepository userRepository;

    private final JWTHelper jwtHelper;

    private final JwtService jwtService;

    private final NinDataRepository ninDataRepository;

    private final FaceDataRepository faceDataRepository;

    private final IdentityVerificationService identityVerificationService;

    private final AppUtils appUtils;

    private final NotificationService notificationService;

    private final OtpRepository otpRepository;

    private final AppConfig appConfig;

    public OnboardingServiceImpl(BvnDataRepository bvnDataRepository, UserRepository userRepository, JWTHelper jwtHelper, JwtService jwtService, NinDataRepository ninDataRepository, FaceDataRepository faceDataRepository, IdentityVerificationService identityVerificationService, AppUtils appUtils, NotificationService notificationService, OtpRepository otpRepository, AppConfig appConfig) {
        this.bvnDataRepository = bvnDataRepository;
        this.userRepository = userRepository;
        this.jwtHelper = jwtHelper;
        this.jwtService = jwtService;
        this.ninDataRepository = ninDataRepository;
        this.faceDataRepository = faceDataRepository;
        this.identityVerificationService = identityVerificationService;
        this.appUtils = appUtils;
        this.notificationService = notificationService;
        this.otpRepository = otpRepository;
        this.appConfig = appConfig;
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
//                String auth = jwtHelper.createShortLiveToken(String.valueOf(existingUserTable.get().getId()), existingUserTable.get().getBvn(), 30);
                resp.put("userToken", String.valueOf(existingUserTable.get().getUserUUID()));
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
            newUserTable.setUserUUID(UUID.randomUUID().toString());
            newUserTable.setBvnVerified(true);
            newUserTable.setDateOfBirth(bvnData.getDateOfBirth());
            newUserTable.setUserRole(UserRole.USER);
            userRepository.save(newUserTable);

            Map<String, String> resp = new HashMap<>();
//            String auth = jwtHelper.createShortLiveToken(String.valueOf(newUserTable.getId()), newUserTable.getBvn(), 30);
//            String auth = jwtService.generateToken(newUserTable.getBvn());
            baseResponse.setDescription("BVN verified successfully");
            resp.put("userToken", String.valueOf(newUserTable.getUserUUID()));
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
    public BaseResponse ninVerification(String userUUID, NinRequest ninRq) {
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
            Optional<UserTable> optionalUserTable = userRepository.findByUserUUID(userUUID);
            if (optionalUserTable.isPresent() && optionalUserTable.get().getNin() != null && optionalUserTable.get().getTranxPin() != null) {
                baseResponse.setDescription("NIN already linked to a user, kindly proceed to login if you're the user!!");
                baseResponse.setStatusCode(200);
                return baseResponse;
            } else if (optionalUserTable.isPresent() && optionalUserTable.get().getNin() != null && optionalUserTable.get().getTranxPin() == null) {
                baseResponse.setDescription("NIN already verified, Verify your phone number!!");
                Map<String, String> resp = new HashMap<>();
                resp.put("userToken", String.valueOf(optionalUserTable.get().getUserUUID()));
//                String auth = jwtHelper.createShortLiveToken(String.valueOf(optionalUserTable.get().getId()), optionalUserTable.get().getNin(), 30);
                baseResponse.setData(resp);
                baseResponse.setStatusCode(200);
                return baseResponse;
            } else if (optionalUserTable.isEmpty()) {
                baseResponse.setStatusCode(400);
                baseResponse.setDescription("NIN verification failed, User not found");
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

//            Map<String, String> resp = new HashMap<>();
//            String auth = jwtService.generateToken(optionalUserTable.get().getBvn());
//            String auth = jwtHelper.createShortLiveToken(String.valueOf(existingUser.getId()), existingUser.getNin(), 30);
            baseResponse.setDescription("NIN verified successfully");
//            resp.put("userId", String.valueOf(existingUser.getId()));
//            baseResponse.setData(resp);
            baseResponse.setStatusCode(200);
            return baseResponse;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("An error occurred while verifying nin {}", ex.getMessage());
            baseResponse.setDescription("Unable to verify NIN, try again later");
        }

        return baseResponse;
    }

    @Override
    public BaseResponse initializeSignUpWithPhoneNumber(String userUUID, String phoneNumber) {
        BaseResponse responseData = new BaseResponse();
        responseData.setStatusCode(200);
        responseData.setDescription("An error occurred, try again later");
        try{
            Optional<UserTable> userTableOptional = userRepository.findByUserUUID(userUUID);
            if (userTableOptional.isPresent()) {
                String cleanedPhoneNumber = phoneNumber
                        .substring(phoneNumber.length() - 10);
                Map<Object, Object> resp = new HashMap<>();

                String msg = "Otp has been sent to the ";
                String maskePhonenumber = "";
                maskePhonenumber = appUtils.maskedPhoneNumber(cleanedPhoneNumber);
                msg += "phonenumber " + maskePhonenumber;
                userTableOptional = userRepository.findByPhoneNumber(phoneNumber);
                if(userTableOptional.isPresent()) {
                    BaseResponse otpResponse = appUtils.generateToken(phoneNumber, 30);
                    JsonObject otpResponseData = new Gson().fromJson(new Gson()
                                    .toJson(otpResponse.getData()),
                            JsonObject.class);
                    String otp = otpResponseData.get("otp").getAsString();
                    String requestId = otpResponseData.get("requestId").getAsString();

                    resp.put("msg", msg);
                    resp.put("requestId", requestId);

                    String optMsg = "Your otp is " + otp;
                    System.out.println(optMsg);
                    responseData.setStatusCode(SUCCESS_STATUS_CODE);
                    responseData.setDescription(SUCCESS_STATUS_MESSAGE);
                    responseData.setData(resp);
                    return responseData;
                }

                Optional<UserTable> optionalUserTable = userRepository.findByPhoneNumberAndActiveTrue(cleanedPhoneNumber);
                if(optionalUserTable.isPresent() && optionalUserTable.get().getTranxPin() != null) {
                    responseData.setDescription("account already created, kindly login");
                    responseData.setStatusCode(201);
                    return responseData;
                }

                Optional<UserTable> optionalUser = userRepository.findByPhoneNumberAndPhoneNumberVerifiedTrue(cleanedPhoneNumber);
                if(optionalUser.isPresent()){
                    UserTable existingUser = optionalUser.get();
//                    String requestId = existingUser.getId().toString();
//                    boolean phoneNumberVerified = existingUser.isPhoneNumberVerified();
//
//                    String jwToken = jwtHelper.createShortLiveToken(requestId, cleanedPhoneNumber, 30);
                    Map<Object, Object> map = new HashMap<Object, Object>();
                    map.put("userToken", existingUser.getUserUUID());
                    responseData.setDescription("PhoneNumber already verified, proceed to email verification");
                    responseData.setStatusCode(210);
                    responseData.setData(map);
                    return responseData;
                } else {
                    BaseResponse otpResponse = appUtils.generateToken(cleanedPhoneNumber,30);
                    JsonObject otpResponseData = new Gson().fromJson(new Gson()
                                    .toJson(otpResponse.getData()),
                            JsonObject.class);
                    String otp = otpResponseData.get("otp").getAsString();
                    String requestId = otpResponseData.get("requestId").getAsString();
                    String optMsg = "Your otp is " + otp;
                    notificationService.sendSms(phoneNumber, otp);

                    resp.put("msg", msg);
                    resp.put("requestId", requestId);

                    System.out.println(optMsg);
                    responseData.setData(resp);
                    responseData.setDescription("OTP token sent successfully!!");
                    responseData.setStatusCode(SUCCESS_STATUS_CODE);
                    return responseData;
                }
            } else {
                responseData.setDescription("User not found!!");
                responseData.setStatusCode(VALDATION_STATUS_CODE);
                return responseData;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseData;
    }

    @Override
    public BaseResponse verifyPhoneNumber(String userUUID, PhoneAndEmailVerificationRequest phoneAndEmailVerificationRequest) {
        BaseResponse responseData = new BaseResponse();
        responseData.setStatusCode(VALDATION_STATUS_CODE);
        responseData.setDescription(DEFAULT_STATUS_MESSAGE);
        try {
            Optional<UserTable> userTableOptional = userRepository.findByUserUUID(userUUID);
            if (userTableOptional.isPresent()) {
                String phonenumber = phoneAndEmailVerificationRequest.getPhoneNumber()
                        .substring(phoneAndEmailVerificationRequest.getPhoneNumber().length() - 10);

                UserTable userTable = userTableOptional.get();

                Optional<OtpEntity> optOtpEntity = otpRepository.findByUsername(phonenumber);

                if (optOtpEntity.isEmpty()) {
                    responseData.setDescription("Phone Number is not valid");
                    return responseData;
                }

                OtpEntity otpEntity = optOtpEntity.get();
                String encOtp = otpEntity.getOtp();

                if (!phoneAndEmailVerificationRequest.getOtp().equals(encOtp)) {
                    responseData.setDescription("Please enter a valid code");
                    return responseData;
                }

                Calendar cal = Calendar.getInstance();
                Long now = cal.getTimeInMillis();
                if (now > otpEntity.getExpiryTime()) {
                    responseData.setDescription("Sorry. the code you entered has expired");
                    return responseData;
                }

                String username = otpEntity.getUsername();
                userTable.setPhoneNumber(username);
                userTable.setPhoneNumberVerified(true);
                userRepository.save(userTable);
//                String jwToken = jwtHelper.createShortLiveToken(String.valueOf(requestId), username, 30);
//                Map map = new HashMap();
//                map.put("phoneNumber", username);
//                map.put("jwtToken", jwToken);
//                map.put("requestId", requestId);

                otpRepository.delete(otpEntity);

                responseData.setStatusCode(SUCCESS_STATUS_CODE);
                responseData.setDescription("Phone number verified successfully");
            } else {
                responseData.setDescription("User not found!!");
                responseData.setStatusCode(VALDATION_STATUS_CODE);
                return responseData;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return responseData;
    }

    @Override
    public BaseResponse initializeSignUpWithEmailAddress(String userUUID, String emailAddress) {
        BaseResponse responseData = new BaseResponse();
        responseData.setStatusCode(VALDATION_STATUS_CODE);
        responseData.setDescription(DEFAULT_STATUS_MESSAGE);

        try{
            Optional<UserTable> optionalUserTable = userRepository.findByEmailAndActiveTrue(emailAddress);
            if(optionalUserTable.isPresent()  && optionalUserTable.get().getTranxPin() != null) {
                responseData.setDescription("account already created, kindly login");
                responseData.setStatusCode(201);
                return responseData;
            }

            Optional<UserTable> optionalUser = userRepository.findByEmailAndEmailVerifiedTrue(emailAddress);
            if(optionalUser.isPresent()) {
                UserTable existingUser = optionalUser.get();
                Map<String, Object> map = new HashMap<>();
                map.put("userToken", existingUser.getUserUUID());
                responseData.setDescription("Email already verified, proceed to next stage");
                responseData.setStatusCode(202);
                responseData.setData(map);
                return responseData;
            }
            String msg = "Otp has been sent to the ";
            String maskeEmail = "";
            if (emailAddress != null) {
                maskeEmail = appUtils.maskedEmail(emailAddress);
                msg += "email " + maskeEmail;
            }
            Map<String, Object> resp = new HashMap<>();

            Optional<UserTable> findUser = userRepository.findByUserUUID(userUUID);
            UserTable userTable = findUser.get();


            Optional<UserTable> optionUser = userRepository.findByEmailAndEmailVerifiedFalse(emailAddress);
            if (optionUser.isPresent()) {
                UserTable existingUser = optionUser.get();
                existingUser.setEmail(emailAddress);
                userRepository.save(existingUser);
            } else {
                userTable.setEmail(emailAddress);
                userRepository.save(userTable);
            }

            if(findUser.isEmpty()) {
                responseData.setDescription("user cannot be found");
                responseData.setStatusCode(DEFAULT_STATUS_CODE);
                return responseData;
            }

            BaseResponse otpResponse = appUtils.generateToken(emailAddress, 30);
            JsonObject otpResponseData = new Gson().fromJson(new Gson()
                            .toJson(otpResponse.getData()),
                    JsonObject.class);
            String otp = otpResponseData.get("otp").getAsString();
            String requestId = otpResponseData.get("requestId").getAsString();

            resp.put("msg", msg);
            resp.put("requestId", userTable.getId());
            responseData.setData(resp);
            String optMsg = "Your otp is " + otp;
            notificationService.sendEmail(emailAddress, otp);
            System.out.println(optMsg);
            responseData.setDescription("OTP token sent successfully!!");
            responseData.setStatusCode(SUCCESS_STATUS_CODE);
            return responseData;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseData;
    }

    public BaseResponse verifyEmail(String userUUID, PhoneAndEmailVerificationRequest phoneAndEmailVerificationRequest) {
        BaseResponse responseData = new BaseResponse();
        responseData.setStatusCode(VALDATION_STATUS_CODE);
        responseData.setDescription(DEFAULT_STATUS_MESSAGE);
        try {
            String email = appUtils.cleanText(phoneAndEmailVerificationRequest.getEmail());

            Optional<OtpEntity> optOtpEntity = otpRepository.findByUsername(phoneAndEmailVerificationRequest.getEmail());

            if (optOtpEntity.isEmpty()) {
                responseData.setDescription("Email is not valid");
                return responseData;
            }
            OtpEntity otpEntity = optOtpEntity.get();
            String encOtp = otpEntity.getOtp();


            if (!phoneAndEmailVerificationRequest.getOtp().equals(encOtp)) {
                responseData.setDescription("Please enter a valid code");
                return responseData;
            }

            Calendar cal = Calendar.getInstance();
            Long now = cal.getTimeInMillis();
            if (now > otpEntity.getExpiryTime()) {
                responseData.setDescription("Sorry. the code you entered has expired");
                return responseData;
            }

            Optional<UserTable> optionalUserTable= userRepository.findByUserUUID(userUUID);
            if(optionalUserTable.isEmpty()){
                responseData.setDescription("User cannot be validated");
                responseData.setStatusCode(DEFAULT_STATUS_CODE);
                return responseData;
            }
            UserTable userTable = optionalUserTable.get();
            userTable.setEmailVerified(true);
            userTable.setEmail(email);
            Long userId = userRepository.save(userTable).getId();
            String username = otpEntity.getUsername();
            String jwToken = jwtService.generateToken(username);
            Map<String, Object> map = new HashMap<>();
            map.put("jwtToken", jwToken);
            otpRepository.delete(otpEntity);

            responseData.setStatusCode(SUCCESS_STATUS_CODE);
            responseData.setDescription("Your email has been verified successfully!!");
            responseData.setData(map);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return responseData;

    }
}
