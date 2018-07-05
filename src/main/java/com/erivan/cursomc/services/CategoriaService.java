package com.erivan.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.erivan.cursomc.domain.Categoria;
import com.erivan.cursomc.repositories.CategoriaRepository;
import com.erivan.cursomc.services.exceptions.DataIntegrityException;
import com.erivan.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id){
		
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " +id+ ", Tipo: "+Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		
			find(obj.getId());
			return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui Produtos");
		}
	}
	
	public List<Categoria> findAll() {
		
		return repo.findAll(); 
	}

}
