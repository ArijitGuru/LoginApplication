package com.arijit.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.arijit.services.AuthenticationService;

@Configuration
@ComponentScan("com.arijit")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	AuthenticationService authenticationService;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/").permitAll()
			.antMatchers("/home", "/login", "/loginProcess").permitAll()
			.antMatchers("/welcome", "/updateAccount").authenticated()
			.antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
			.and().formLogin()
			.loginPage("/loginProcess").usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/welcome")
			.and()
			.logout().logoutSuccessUrl("/login?logout").clearAuthentication(true)
			.and().exceptionHandling().accessDeniedPage("/403")
			.and()
			.csrf().disable();
	}

}
