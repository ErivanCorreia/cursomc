package com.erivan.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erivan.cursomc.domain.Cidade;
import com.erivan.cursomc.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;
	
	public List<Cidade> findByEstado(Integer estado_id) {
		return repo.findCidades(estado_id);
	}
	
	
}
