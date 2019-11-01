package com.arijit.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;

	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, enabled from user_detail where username=?")
				.authoritiesByUsernameQuery("select username, role from user_roles where username=?");
		}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		  .antMatchers("/", "/login").permitAll()
          .antMatchers("/admin").hasAuthority("ROLE_ADMIN")//access("hasRole('ROLE_ADMIN')")
          
          //.loginPage("/login")
          //.failureUrl("/403").usernameParameter("username").passwordParameter("password")
          //.and().logout().logoutSuccessUrl("/login?logout").and().csrf().disable();
		.and().formLogin().loginPage("/login")
        .usernameParameter("username").passwordParameter("password")
        .and().csrf()
        .and().exceptionHandling().accessDeniedPage("/403");
      

  }
}
