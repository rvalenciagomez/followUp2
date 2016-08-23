package com.crmfollowup.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
  @Autowired
  private UserDetailsService userDetailsService;
  
  @Autowired
  public void globalSecurity (AuthenticationManagerBuilder auth) throws Exception
  {
    auth.userDetailsService(userDetailsService)
    
    .passwordEncoder(new BCryptPasswordEncoder());
    
//    auth.inMemoryAuthentication()
//      .withUser("user")
//      .password("1234")
//      .roles("USER")
//    .and()
//      .withUser("admin")
//      .password("admin")
//      .roles("USER,ADMIN");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception 
  {
    http
    .csrf().disable()
    .authorizeRequests()
      .antMatchers("/register").permitAll()
      .anyRequest().authenticated()
      .and()
    .formLogin()
      .loginPage("/login")
      .permitAll()
      .and()
    .logout()
      .permitAll();
    
  }
  
//  @Bean()
//    public SecurityEvaluationContextExtension securityEvaluationContextExtension () {
//    return new SecurityEvaluationContextExtension();
//  } 
  
  @Bean
  EvaluationContextExtension securityExtension() {
      return new SecurityEvaluationContextExtension();
  }
}
