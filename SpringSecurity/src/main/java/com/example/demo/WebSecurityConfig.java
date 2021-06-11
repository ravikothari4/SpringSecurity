package com.example.demo;

import org.springframework.context.annotation.*;  
import org.springframework.security.config.annotation.web.builders.HttpSecurity;  
import org.springframework.security.config.annotation.web.configuration.*;  
import org.springframework.security.core.userdetails.User;  
import org.springframework.security.core.userdetails.UserDetailsService;  
import org.springframework.security.provisioning.InMemoryUserDetailsManager;  
@EnableWebSecurity  
@ComponentScan("com.example.demo")  
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {  
    
  @Bean  
  public UserDetailsService userDetailsService() {  
      InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();  
      manager.createUser(User.withDefaultPasswordEncoder()  
      .username("admin").password("admin").roles("ADMIN").build());  
      return manager;  
  }  
    
  @Override  
  protected void configure(HttpSecurity http) throws Exception {  
                
      http                              
      .authorizeRequests()  
          .anyRequest().hasRole("ADMIN")  
          .and().formLogin().and()  
      .httpBasic()  
      .and()  
      .logout()  
      .logoutUrl("/j_spring_security_logout")  
      .logoutSuccessUrl("/")  
      ;  
  }  
} 