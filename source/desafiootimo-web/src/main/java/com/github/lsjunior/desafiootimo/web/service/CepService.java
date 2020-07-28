package com.github.lsjunior.desafiootimo.web.service;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.github.lsjunior.desafiootimo.web.Log;
import com.github.lsjunior.desafiootimo.web.model.Cep;

@Service
public class CepService {

  @Autowired
  private RestTemplate restTemplate;

  public CepService() {
    super();
  }

  public Cep get(final String cep) {
    String baseUrl = "https://viacep.com.br/ws/{cep}/json";
    Map<String, Object> params = Collections.singletonMap("cep", cep);
    Cep obj = this.getForObject(baseUrl, Cep.class, params);
    if (obj != null) {
      obj.setCep(obj.getCep());
    }
    return obj;
  }

  private <T> T getForObject(final String url, final Class<T> type, final Map<String, Object> params) {
    try {
      T value = this.restTemplate.getForObject(url, type, params);
      return value;
    } catch (HttpClientErrorException e) {
      if ((e.getStatusCode() == HttpStatus.FORBIDDEN) || (e.getStatusCode() == HttpStatus.NOT_FOUND)) {
        return null;
      }
      Log.getLogger().error(e.getMessage(), e);
      throw e;
    } catch (HttpMessageNotReadableException e) {
      Log.getLogger().debug(e.getMessage(), e);
      return null;
    }
  }

}
