package com.example.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.crud.model.User;

@Service
public class EmailService {
	 @Autowired
	   private JavaMailSender mailSender;

	   private String adminEmail = "mohamedazizmrabet2234@gmail.com"; 

	    public void sendNotificationToAdmin(User user) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(adminEmail);
	        message.setFrom("mohamedaziz2234@gmail.com");
	        message.setSubject("New User Registered");
	        message.setText("A new user has been registered with the following details:\n\n" +
	                "Name: " + user.getName() + "\n" +
	                "Surname: " + user.getSurname() + "\n" +
	                "Age: " + user.getAge() +"\n"+
	                "Date:"+user.getDate());

	        mailSender.send(message);
	        System.out.println("Mail has been sended successfully");
	    }

}
