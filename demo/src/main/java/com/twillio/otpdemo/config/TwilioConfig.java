package com.twillio.otpdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@ConfigurationProperties(prefix = "twilio")
@Data
public class TwilioConfig {

    private String accountSid;
    private String accountToken;
    private String phoneNumber;
}
