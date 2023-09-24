package com.twillio.otpdemo.service;


import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twillio.otpdemo.config.TwilioConfig;
import com.twillio.otpdemo.dto.OtpRequest;
import com.twillio.otpdemo.dto.OtpResponseDto;
import com.twillio.otpdemo.dto.OtpStatus;
import com.twillio.otpdemo.dto.OtpValidationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

@Service
public class SmsService {

    Map<String, String> otpMap = new HashMap<>();
    @Autowired
    private TwilioConfig twilioConfig;

    public OtpResponseDto sendSMS(OtpRequest otpRequest) {
        OtpResponseDto otpResponseDto = null;
        try {
            PhoneNumber to = new PhoneNumber(otpRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfig.getPhoneNumber());
            String otp = generateOtp();
            String otpMessage = "Dear Costumer , Your OTP is " + otp + " for testing Otp for RW Application via Samarth";
            Message message = Message.creator(to, from, otpMessage).create();
            otpMap.put(otpRequest.getPhoneNumber(), otp);
            otpResponseDto = new OtpResponseDto(OtpStatus.DELIVERED, otpMessage);
        } catch (Exception e) {
            e.printStackTrace();
            otpResponseDto = new OtpResponseDto(OtpStatus.FAILED, e.getMessage());
        }
        return otpResponseDto;
    }

    public String validateOtp(OtpValidationRequest otpValidationRequest) {
        Set<String> keys = otpMap.keySet();
        String phoneNumber = null;
        for (String key : keys) {
            phoneNumber = key;
        }

        if (otpValidationRequest.getPhoneNumber().equals(phoneNumber) && otpValidationRequest.getOtp().equals(otpMap.get(phoneNumber))) {
            otpMap.remove(phoneNumber, otpValidationRequest.getOtp());
            return "OTP is valid";
        } else {
            return "OTP is invalid";
        }
    }

    private String generateOtp() {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));

    }
}
