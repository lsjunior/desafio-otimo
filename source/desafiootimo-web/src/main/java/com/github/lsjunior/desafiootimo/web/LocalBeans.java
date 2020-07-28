package com.github.lsjunior.desafiootimo.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class LocalBeans {

  public LocalBeans() {
    super();
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

}
