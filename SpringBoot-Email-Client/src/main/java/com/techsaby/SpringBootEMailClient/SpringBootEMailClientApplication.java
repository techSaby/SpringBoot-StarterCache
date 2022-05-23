package com.techsaby.SpringBootEMailClient;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringBootEMailClientApplication {
	
	@Autowired
	private EmailSenderService service;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootEMailClientApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {
		
		//Without Attachment
		/*
		service.sendEmail("email@domain.com", 
				"Test Body", 
				"Test Subject");
		*/
		
		//With Attachment
		
		service.sendEmailWithAttachment("email@domain.com", 
				"Test Body with Attachment", "Test Subject with Attachment", 
				"Attachment.jpeg");
	}

}
