package com.erivan.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.erivan.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = pepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	
	protected SimpleMailMessage pepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		//recebe o email
		sm.setTo(obj.getCliente().getEmail());
		
		//envia a o email
		sm.setFrom(sender);
		
		//Título do email
		sm.setSubject("Pedido confirmado! Código: "+obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		
		//Corpo do email
		sm.setText(obj.toString());
		return sm;
	}
	
}
