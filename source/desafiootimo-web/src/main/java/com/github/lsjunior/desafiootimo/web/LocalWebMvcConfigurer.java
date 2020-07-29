package com.github.lsjunior.desafiootimo.web;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public abstract class LocalWebMvcConfigurer implements WebMvcConfigurer {

  public LocalWebMvcConfigurer() {
    super();
  }

  @Override
  public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {
    PageableHandlerMethodArgumentResolver pageableResolver = new PageableHandlerMethodArgumentResolver();
    pageableResolver.setFallbackPageable(PageRequest.of(0, 10));
    pageableResolver.setMaxPageSize(1000);
    // pageableResolver.setPageParameterName("p");
    // pageableResolver.setSizeParameterName("s");
    argumentResolvers.add(pageableResolver);
  }

}
