package com.github.lsjunior.desafiootimo.web.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.github.lsjunior.desafiootimo.web.Constants;
import com.sun.istack.NotNull;

@Entity
@Table(name = "empresa", schema = "public")
@SequenceGenerator(name = "seq_empresa", sequenceName = "seq_empresa", schema = "public", allocationSize = 1)
public class Empresa implements Serializable {

  private static final long serialVersionUID = Constants.VERSION;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_empresa")
  private Integer id;

  @Column(name = "cnpj", length = 20, nullable = false)
  @NotNull
  @Pattern(regexp = "(^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$)", message = "deve ser um CNPJ bem formado")
  private String cnpj;

  @Column(name = "nome", length = 50, nullable = false)
  @NotNull
  @Size(max = 50)
  private String nome;

  @Column(name = "razao_social", length = 40, nullable = false)
  @NotNull
  @Size(max = 40)
  private String razaoSocial;

  @Column(name = "contato", length = 40, nullable = false)
  @NotNull
  @Size(max = 40)
  private String contato;

  @Column(name = "email", length = 40, nullable = false)
  @NotNull
  @Email
  @Size(max = 40)
  private String email;

  @Column(name = "logradouro", length = 40, nullable = false)
  @NotNull
  @Size(max = 40)
  private String logradouro;

  @Column(name = "complemento", length = 40, nullable = true)
  @Size(max = 40)
  private String complemento;

  @Column(name = "estado", length = 40, nullable = false)
  @NotNull
  @Size(max = 40)
  private String estado;

  @Column(name = "cidade", length = 40, nullable = false)
  @NotNull
  @Size(max = 40)
  private String cidade;

  @Column(name = "bairro", length = 40, nullable = false)
  @NotNull
  @Size(max = 40)
  private String bairro;

  @Column(name = "cep", length = 10, nullable = false)
  @NotNull
  @Pattern(regexp = "(^\\d{5}-\\d{3}$)", message = "deve ser um CEP bem formado")
  @Size(max = 10)
  private String cep;

  @Column(name = "tipo", nullable = false)
  @Enumerated(EnumType.ORDINAL)
  @NotNull
  private TipoEmpresa tipo;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_matriz", referencedColumnName = "id", nullable = false)
  @NotNull
  private Empresa matriz;

  public Empresa() {
    super();
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public String getCnpj() {
    return this.cnpj;
  }

  public void setCnpj(final String cnpj) {
    this.cnpj = cnpj;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(final String nome) {
    this.nome = nome;
  }

  public String getRazaoSocial() {
    return this.razaoSocial;
  }

  public void setRazaoSocial(final String razaoSocial) {
    this.razaoSocial = razaoSocial;
  }

  public String getContato() {
    return this.contato;
  }

  public void setContato(final String contato) {
    this.contato = contato;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(final String email) {
    this.email = email;
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

  public String getEstado() {
    return this.estado;
  }

  public void setEstado(final String estado) {
    this.estado = estado;
  }

  public String getCidade() {
    return this.cidade;
  }

  public void setCidade(final String cidade) {
    this.cidade = cidade;
  }

  public String getBairro() {
    return this.bairro;
  }

  public void setBairro(final String bairro) {
    this.bairro = bairro;
  }

  public String getCep() {
    return this.cep;
  }

  public void setCep(final String cep) {
    this.cep = cep;
  }

  public TipoEmpresa getTipo() {
    return this.tipo;
  }

  public void setTipo(final TipoEmpresa tipo) {
    this.tipo = tipo;
  }

  public Empresa getMatriz() {
    return this.matriz;
  }

  public void setMatriz(final Empresa matriz) {
    this.matriz = matriz;
  }

}
