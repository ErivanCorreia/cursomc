package com.erivan.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erivan.cursomc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
