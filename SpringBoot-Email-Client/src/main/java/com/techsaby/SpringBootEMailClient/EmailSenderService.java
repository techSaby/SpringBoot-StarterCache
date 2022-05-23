package com.techsaby.SpringBootEMailClient;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailSenderService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail(String receipientEmail, 
			String emailBody, String emailSubject) {
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
		simpleMailMessage.setFrom("email1@domain.com");
		simpleMailMessage.setTo(receipientEmail);
		simpleMailMessage.setText(emailBody);
		simpleMailMessage.setSubject(emailSubject);
		
		javaMailSender.send(simpleMailMessage);
		System.out.println("Mail Sent Successfully to.... " + receipientEmail);
		
	}
	
	
	public void sendEmailWithAttachment(String receipientEmail, 
			String emailBody, String emailSubject,String attachement) throws MessagingException {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		
		mimeMessageHelper.setFrom("email1@domain.com");
		mimeMessageHelper.setTo(receipientEmail);
		mimeMessageHelper.setText(emailBody);
		mimeMessageHelper.setSubject(emailSubject);
		
		FileSystemResource fileSystemResource = new FileSystemResource(new File(attachement));
		
		mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
		
		javaMailSender.send(mimeMessage);
		
		System.out.println("Mail Sent Successfully with Attachement to.... " + receipientEmail);
		
	}
}
