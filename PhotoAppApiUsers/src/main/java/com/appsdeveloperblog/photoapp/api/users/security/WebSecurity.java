package com.appsdeveloperblog.photoapp.api.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.appsdeveloperblog.photoapp.api.users.business.abstracts.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	

	private Environment environment;//env ile yaml dosyasındaki konfigürasyonu okutuyoruz.i p adresini
	private UserService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
	public WebSecurity(Environment environment,UserService userService,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.environment = environment;
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {//adapterden geliyoır
		http.csrf().disable(); //cors: başka bir tarayıcıdan istek geldiğind eou onyalamaz
		//csrf : sadece izin verdiğimiz  clientların ilgili bölümlerindn istek yaptırır
		//misal web tabanlı uygulama yaptığımızda
		http.authorizeRequests().antMatchers("/users/**")
		.hasIpAddress(this.environment.getProperty("gateway.ip")).and().addFilter(getAuthenticationFilter());  //.permitAll();//user altındaki her şeye herke s erişebilsin
		http.headers().frameOptions().disable();	
	}

	private AuthenticationFilter getAuthenticationFilter() throws Exception {//dependincy injection autyman()
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(this.userService,environment,authenticationManager());
		//authenticationFilter.setAuthenticationManager(authenticationManager());
		authenticationFilter.setFilterProcessesUrl(this.environment.getProperty("login.url.path"));
		return authenticationFilter;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userService).passwordEncoder(bCryptPasswordEncoder);
		
	}
	
	
}
