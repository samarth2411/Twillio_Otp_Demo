package com.twillio.otpdemo.controller;

import com.twillio.otpdemo.dto.OtpRequest;
import com.twillio.otpdemo.dto.OtpResponseDto;
import com.twillio.otpdemo.dto.OtpValidationRequest;
import com.twillio.otpdemo.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
@Slf4j
public class OtpController {

    @Autowired
    private SmsService smsService;

    @PostMapping("/send-otp")
    public OtpResponseDto sendOtp(@RequestBody OtpRequest otpRequest) {
        log.info("inside sendOtp :: " + otpRequest.getPhoneNumber());
        return smsService.sendSMS(otpRequest);
    }

    @PostMapping("validate-otp")
    public String validateOtp(@RequestBody OtpValidationRequest otpValidationRequest) {
        log.info("inside validateOtp :: " + otpValidationRequest.getPhoneNumber() + " " + otpValidationRequest.getOtp());
        return smsService.validateOtp(otpValidationRequest);
    }


}
