package com.github.lsjunior.desafiootimo.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableResourceServer
public class LocalResourceServerConfigurer extends ResourceServerConfigurerAdapter {

  public LocalResourceServerConfigurer() {
    super();
  }

  @Override
  public void configure(final HttpSecurity http) throws Exception {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowCredentials(Boolean.TRUE);
    corsConfiguration.addAllowedOrigin("*");
    corsConfiguration.addAllowedHeader("*");
    corsConfiguration.addAllowedMethod("*");

    UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
    corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

    // @formatter:off
    http
      .cors()
        .configurationSource(corsConfigurationSource)
    .and()
      .headers().frameOptions().disable()
    .and()
      .requestMatchers()
        .antMatchers(
            "/api/**")
    .and()
      .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS, "/api/**")
          .permitAll()
        .antMatchers("/api/**") // Public API
          .permitAll()
        .anyRequest()
          .authenticated()
    .and()
      .csrf()
        // .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        .disable();
    // @formatter:on
  }

  @Override
  public void configure(final ResourceServerSecurityConfigurer resources) throws Exception {
    resources.resourceId(null);
  }

}
