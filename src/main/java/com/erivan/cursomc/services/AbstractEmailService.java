package com.erivan.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.erivan.cursomc.domain.Cliente;
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
	
	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage sm = pepareNewPassword(cliente, newPass);
		sendEmail(sm);
	}


	protected SimpleMailMessage pepareNewPassword(Cliente cliente, String newPass) {
		
		SimpleMailMessage sm = new SimpleMailMessage();
		//recebe o email
		sm.setTo(cliente.getEmail());
		
		//envia a o email
		sm.setFrom(sender);
		
		//Título do email
		sm.setSubject("Auteração de senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		
		//Corpo do email
		sm.setText("Nova senha: "+newPass);
		return sm;
	}
	
	
}
