package com.github.lsjunior.desafiootimo.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.lsjunior.desafiootimo.web.entity.Empresa;
import com.github.lsjunior.desafiootimo.web.entity.TipoEmpresa;

public interface EmpresaJpaRepository extends JpaRepository<Empresa, Integer> {

  Page<Empresa> findByNomeContaining(String nome, Pageable pageable);
  
  Page<Empresa> findByTipoAndNomeContaining(TipoEmpresa tipo, String nome, Pageable pageable);
  
  @Query(value = "SELECT o FROM Empresa AS o WHERE (:tipo IS NULL OR o.tipo = :tipo) AND o.cnpj LIKE :cnpj AND o.nome LIKE :nome ORDER BY o.nome",
         countQuery = "SELECT COUNT(o) FROM Empresa AS o WHERE (:tipo IS NULL OR o.tipo = :tipo) AND o.cnpj LIKE :cnpj AND o.nome LIKE :nome")
  Page<Empresa> findByTipoAndCnpjAndNome(@Param("tipo") TipoEmpresa tipo, @Param("cnpj") String cnpj, @Param("nome") String nome, Pageable pageable);

}
