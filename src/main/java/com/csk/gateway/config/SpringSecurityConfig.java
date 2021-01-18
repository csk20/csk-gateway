package com.csk.gateway.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.csk.gateway.auth.CustomLogout;
import com.csk.gateway.auth.LoginFail;
import com.csk.gateway.auth.LoginSuccess;

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomLogout cuLogout;
	@Autowired
	LoginSuccess lSuccess;
	@Autowired
	LoginFail lFail;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/","*.html","/*.js","/csk/login","/csk/logout","/sessionVerify")
		.permitAll().anyRequest().hasAuthority("Access_Proj");
		
	http.exceptionHandling()
	.defaultAuthenticationEntryPointFor(this :: unauthorized, new AntPathRequestMatcher("/api/v1/*"));
	
	http.csrf().disable();
	http.cors().disable();
	http.formLogin().loginProcessingUrl("/csk/login")
	.successHandler(lSuccess).failureHandler(lFail);
	http.logout().logoutUrl("/csk/logout").logoutSuccessHandler(cuLogout);
	
	}
	
	
	private void unauthorized(HttpServletRequest req,HttpServletResponse res,AuthenticationException ex) throws IOException {
		res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		
	}

}
