package com.luv2code.springboot.thymeleafdemo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource securityDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// add jdbc authentication

		auth.jdbcAuthentication().dataSource(securityDataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/employees/showForm*").hasAnyRole("MANAGER", "ADMIN")
				.antMatchers("/employees/save*").hasAnyRole("MANAGER", "ADMIN").antMatchers("/employees/delete")
				.hasRole("ADMIN").antMatchers("/employees/**").hasRole("EMPLOYEE").antMatchers("/resources/**")
				.permitAll().and().formLogin().permitAll().and().logout().permitAll().and().exceptionHandling()
				.accessDeniedPage("/access-denied");
	}

}
