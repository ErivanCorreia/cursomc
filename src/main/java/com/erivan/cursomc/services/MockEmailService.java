package com.erivan.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService{

	//objeto log para mostrar o email no log do servidor
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulação - envio de email");
		LOG.info(msg.toString());
		LOG.info("email enviado");
	}

}
