package com.emailsender.sendemail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class SendemailApplication{
@Autowired
	private sendEmail senderservice;
	public static void main(String[] args) {
		SpringApplication.run(SendemailApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
		public void sendEmail(){
			senderservice.send(VerifyUser.emailadd,"Email verification",Integer.toString(VerifyUser.generatedOTP));
		}
}

