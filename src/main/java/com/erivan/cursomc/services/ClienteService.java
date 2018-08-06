package com.erivan.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.erivan.cursomc.domain.Cidade;
import com.erivan.cursomc.domain.Cliente;
import com.erivan.cursomc.domain.Endereco;
import com.erivan.cursomc.domain.enums.TipoCliente;
import com.erivan.cursomc.dto.ClienteDTO;
import com.erivan.cursomc.dto.ClienteNewDTO;
import com.erivan.cursomc.repositories.ClienteRepository;
import com.erivan.cursomc.repositories.EnderecoRepository;
import com.erivan.cursomc.services.exceptions.DataIntegrityException;
import com.erivan.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe; 
	
	
	public Cliente find(Integer id){
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! Id: "+id+
									 ", Tipo: "+Cliente.class.getName()));
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(obj);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public Cliente fromClienteDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(),objDTO.getNome(), objDTO.getEmail(), null, null, null);
	}
	
	public Cliente fromClienteDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), 
				objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()), pe.encode(objDTO.getSenha()));
		
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		
		Endereco end = new Endereco(null,objDTO.getLogradouro(), objDTO.getNumero(),
				 objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(),cli, cid);
	
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if(objDTO.getTelefone2()!= null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if(objDTO.getTelefone3()!= null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		
		return cli;
	}
		
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir pois há entidates relacionadas");
		}
		
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
}
