package com.example.tutorial8;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/course/**").hasRole("ADMIN")
		.antMatchers("/student/**").hasAnyRole("USER", "ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.permitAll();
	}
	
//	@Autowired
//	public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception
//	{
//		auth.inMemoryAuthentication()
//		.withUser("user").password("password")
//		.roles("USER");
//	}
	
	@Autowired
	javax.sql.DataSource dataSource;
	
	@Autowired
	public void configAuthentication (AuthenticationManagerBuilder auth) throws Exception
	{
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
				"select username,password,enabled from users where username=?")
				.authoritiesByUsernameQuery(
				"select username, role from user_roles where username=?");
	}
	
	
	@Autowired
	public void configureAdmin (AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication()
		.withUser("admin").password("admin")
		.roles("Administrator");
	}
}
