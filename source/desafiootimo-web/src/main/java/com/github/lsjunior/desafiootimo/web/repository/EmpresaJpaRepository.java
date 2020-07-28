package com.github.lsjunior.desafiootimo.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.github.lsjunior.desafiootimo.web.entity.Empresa;

public interface EmpresaJpaRepository extends JpaRepository<Empresa, Integer> {

  Page<Empresa> findByNomeContaining(String nome, Pageable pageable);

}
