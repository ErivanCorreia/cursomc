package com.erivan.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.erivan.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
