package com.erivan.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erivan.cursomc.domain.Categoria;
import com.erivan.cursomc.repositories.CategoriaRepository;

import java.util.Optional;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id){
		
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
		
	}

}
