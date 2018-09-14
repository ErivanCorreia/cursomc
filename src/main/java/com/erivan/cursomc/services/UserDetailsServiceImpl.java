package com.erivan.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.erivan.cursomc.domain.Cliente;
import com.erivan.cursomc.repositories.ClienteRepository;
import com.erivan.cursomc.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private ClienteRepository clienteRepository; 
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Cliente c1 = clienteRepository.findByEmail(email);
		if(c1 == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(c1.getId(), c1.getEmail(), c1.getSenha(), c1.getPerfis());
	}

}
