package com.github.lsjunior.desafiootimo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.lsjunior.desafiootimo.web.model.Cep;
import com.github.lsjunior.desafiootimo.web.service.CepService;

@RestController
@RequestMapping("/api/cep")
@CrossOrigin
public class CepResource {

  @Autowired
  private CepService cepService;

  @RequestMapping(path = "/{cep}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> get(@PathVariable("cep") final String cep) {
    try {
      Cep c = this.cepService.get(cep);
      return ResponseEntity.ok(c);
    } catch (Exception e) {
      return ResponseUtils.toError(e);
    }
  }

}
