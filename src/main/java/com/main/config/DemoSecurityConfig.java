package com.main.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource securityDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter, CsrfFilter.class);

		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/user/**").hasRole("USER")
				.antMatchers("/nhanvien/**").hasRole("EMPLOYEE").antMatchers("/veXe/**").hasAnyRole("USER,EMPLOYEE")
				.antMatchers("/quanly/**").hasAnyRole("MANAGER").and().formLogin().loginPage("/showLoginPage")
				.loginProcessingUrl("/authenticateTheUser").defaultSuccessUrl("/loginSuccessful", true).permitAll()
				.and().logout().permitAll();
//			.and()
//			.exceptionHandling()
		// .accessDeniedPage("/access-denied");
	}

}
