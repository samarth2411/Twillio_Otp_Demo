package com.twillio.otpdemo;

import com.twilio.Twilio;
import com.twillio.otpdemo.config.TwilioConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class TwillioOtpDemoApplication {

	@Autowired
	private TwilioConfig twilioConfig;

	public static void main(String[] args) {
		SpringApplication.run(TwillioOtpDemoApplication.class, args);
	}

	@PostConstruct
	public void setup() {
		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAccountToken());
	}

}
