package com.tocersoft.email.service;

import org.springframework.mail.javamail.JavaMailSenderImpl;

public interface MailSenderProvider {
	
	public JavaMailSenderImpl getSender();
	
}
