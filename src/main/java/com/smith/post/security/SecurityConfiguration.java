package com.smith.post.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
//	@Autowired
//	DataSource dataSource;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
//		auth.jdbcAuthentication().dataSource(dataSource)
//		.withDefaultSchema()
		
		
		auth.inMemoryAuthentication()
		.withUser("myuser")
		.password("pass")
		.roles("USER")
		.and()
		.withUser("dan")
		.password("pass")
		.roles("USER")
		.and()
		.withUser("managerUser")
		.password("pass")
		.roles("ADMIN")
		.and()
		.withUser("Navatha")
		.password("pass")
		.roles("ADMIN")
		.and()
		.withUser("Daniel")
		.password("pass")
		.roles("ADMIN");
		

	
		
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		.antMatchers("/posts/new").hasRole("ADMIN")
		.antMatchers("/users/new").hasRole("ADMIN")
		.antMatchers("/").authenticated()
		.and().formLogin();
	
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.cors();
	}
}
