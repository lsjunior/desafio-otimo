package com.github.lsjunior.desafiootimo.web.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.lsjunior.desafiootimo.web.entity.Empresa;
import com.github.lsjunior.desafiootimo.web.entity.TipoEmpresa;
import com.github.lsjunior.desafiootimo.web.repository.EmpresaJpaRepository;
import com.google.common.base.MoreObjects;

@Service
@Transactional
public class EmpresaService {

  @Autowired
  private EmpresaJpaRepository empresaJpaRepository;

  public EmpresaService() {
    super();
  }

  public Empresa get(final Integer id) {
    return this.empresaJpaRepository.findById(id).orElse(null);
  }

  public Empresa save(final Empresa obj) {
    if (obj.getMatriz() != null) {
      Empresa matriz = this.empresaJpaRepository.getOne(obj.getMatriz().getId());
      obj.setMatriz(matriz);
    }
    Empresa e = this.empresaJpaRepository.save(obj);
    return e;
  }

  public Empresa update(final Empresa obj) {
    Optional<Empresa> opt = this.empresaJpaRepository.findById(obj.getId());
    if (opt.isPresent()) {
      Empresa e = opt.get();
      if (obj.getMatriz() != null) {
        Empresa matriz = this.empresaJpaRepository.getOne(obj.getMatriz().getId());
        obj.setMatriz(matriz);
      }
      BeanUtils.copyProperties(obj, e);
      e = this.empresaJpaRepository.save(e);
      return e;
    }
    return null;
  }

  public boolean delete(final Integer id) {
    Optional<Empresa> opt = this.empresaJpaRepository.findById(id);
    if (opt.isPresent()) {
      Empresa e = opt.get();
      if (e.getTipo() == TipoEmpresa.MATRIZ) {
        throw new IllegalArgumentException("Não é possível excluir uma matriz");
      }
      this.empresaJpaRepository.delete(opt.get());
      return true;
    }
    return false;
  }

  public Page<Empresa> list(final String query, final Pageable pageable) {
    String str = MoreObjects.firstNonNull(query, "");
    Pageable pageableToUse = pageable;
    if (pageableToUse.getSort() == null) {
      pageableToUse = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("nome"));
    }
    Page<Empresa> page = this.empresaJpaRepository.findByNomeContaining(str, pageableToUse);
    return page;
  }

}
