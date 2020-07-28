package com.github.lsjunior.desafiootimo.web.model;

import java.io.Serializable;

import com.github.lsjunior.desafiootimo.web.Constants;

public class Cep implements Serializable {

  private static final long serialVersionUID = Constants.VERSION;

  private String cep;

  private String logradouro;

  private String complemento;

  private String bairro;

  private String localidade;

  private String uf;

  private String unidade;

  private String ibge;

  private String gia;

  public Cep() {
    super();
  }

  public String getCep() {
    return this.cep;
  }

  public void setCep(final String cep) {
    this.cep = cep;
  }

  public String getLogradouro() {
    return this.logradouro;
  }

  public void setLogradouro(final String logradouro) {
    this.logradouro = logradouro;
  }

  public String getComplemento() {
    return this.complemento;
  }

  public void setComplemento(final String complemento) {
    this.complemento = complemento;
  }

  public String getBairro() {
    return this.bairro;
  }

  public void setBairro(final String bairro) {
    this.bairro = bairro;
  }

  public String getLocalidade() {
    return this.localidade;
  }

  public void setLocalidade(final String localidade) {
    this.localidade = localidade;
  }

  public String getUf() {
    return this.uf;
  }

  public void setUf(final String uf) {
    this.uf = uf;
  }

  public String getUnidade() {
    return this.unidade;
  }

  public void setUnidade(final String unidade) {
    this.unidade = unidade;
  }

  public String getIbge() {
    return this.ibge;
  }

  public void setIbge(final String ibge) {
    this.ibge = ibge;
  }

  public String getGia() {
    return this.gia;
  }

  public void setGia(final String gia) {
    this.gia = gia;
  }

}
