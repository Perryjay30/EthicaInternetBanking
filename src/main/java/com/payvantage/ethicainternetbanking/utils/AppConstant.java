/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.payvantage.ethicainternetbanking.utils;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author gol
 */
public class AppConstant {

    public static final String BVN_VALIDATION_REGEX = "^\\d{11}$";
    public static final String PIN_VALIDATION_REGEX = "^\\d{4}$";
    public static final String PASSCODE_VALIDATION_REGEX = "^\\d{6}$";
    public static final String BVN_VALIDATION_ERROR = "BVN must be 11 digits";
    
    public static final String ACCOUNTNUMBER_VALIDATION_REGEX = "^\\d{10}$";

    public static final String PIN_VALIDATION_ERROR = "PIN must be 4 digits";
    public static final String PASSCODE_VALIDATION_ERROR = "Passcode must be 6 digits";
    public static final String DATE_OF_BIRTH_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";
    public static final String DATE_OF_BIRTH_VALIDATE_ERR = "Date of birth must be in the format YYYY-DD-MM";
    public static final int DEFAULT_STATUS_CODE = 400;

    public static final String USER_CANNOT_BE_AUTHORIZE = "Unable to authorized user";
    
    
    public static final String NAME_VALIDATION_REGEX="^[a-zA-Z]{3,}(?: [a-zA-Z]+){0,2}$";
    
    public static final int VALDATION_STATUS_CODE = 400; 
    public static final String DEFAULT_STATUS_MESSAGE = "An error occurred, please try again later";

    public static final int SUCCESS_STATUS_CODE = 200;
    public static final String SUCCESS_STATUS_MESSAGE = "Successful";
    public static final String SUCCESS_STATUS_MESSAGE_FOR_PASSCODE_CHANGE = "Passcode changed successfully";
    
    public static final int INCOMPLETE_SIGNUP_STATUS_CODE = 201;
    
    
    public static final int PHONE_NUMBER_ALREADY_EXIST_CODES = 400;
    public static final String PHONE_NUMBER_ALREADY_EXIST_MESSAGE = "Phone number already exist";
    
    public static final int DOES_NOT_EXIST_STATUS_CODE=400;
    public static final String DOES_NOT_EXIST_STATUS_MSG="Record does not exist";

    public static final String PHONENUMBER_VALIDATION_REGEX = "^\\d{11}$";
    public static final String PHONENUMBER_VALIDATION_ERROR_MESSAGE = "Phonenumber must be 11 digits";
    public static final String BVN_VALIDATION_ERROR_MESSAGE = "BVN must be 11 digits";

    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{6,20}$";

    public static final String PASSWORD_VALIDATION_ERR_MESSAGE = "New password must contain at least one lowercase, one uppercase, one special character, one digit and a length of at least 6 characters.";

    public static final String GENDER_REGEX = "^[M|F]{1}$";
    
    public static final String VALUE_CANNOT_BE_EMPTY = "Value cannot be empty";

    public static final String GENDER_REGEX_ERR_MESSAGE = "Gender Must be M or F";

    public static final String OTP_VALIDATION_REGEX = "^\\d{6}$";
    public static final String OTP_VALIDATION_MSG = "OTP must be 6 digits";
    
    public static String convertDateFormat(String sourceDate, String sourceFormat, String finalFormat) {
        DateFormat originalFormat = new SimpleDateFormat(sourceFormat);
        DateFormat targetFormat = new SimpleDateFormat(finalFormat);
       // Date date;
        try {
           Date date = originalFormat.parse(sourceDate);
            return targetFormat.format(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return null;

    }
    
    
    public static String cleanPhonenumber(String phonenumber) {
        return phonenumber.substring(phonenumber.length() - 10);
    }
    
     public static String getRandomNumberInts(int length) {

        SecureRandom randomGenerator = new SecureRandom();
        String randNum = "";

        for (int idx = 1; idx <= length; ++idx) {
            int randomInt = randomGenerator.nextInt(10);
            randNum += Integer.toString(randomInt);
        }
        return randNum;

    }
}
