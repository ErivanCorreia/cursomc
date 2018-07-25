package com.erivan.cursomc.services;


import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.erivan.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pgto, Date instante) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instante);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pgto.setDataVencimento(cal.getTime());
	}
	
}
