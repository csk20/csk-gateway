package com.csk.gateway.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import graphql.execution.instrumentation.tracing.TracingSupport.TracingContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginSuccess implements AuthenticationSuccessHandler {
	
	@Autowired
	private ObjectMapper mapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

if(authentication !=null && authentication.getDetails()!=null) {
	
	
}
log.info((String) authentication.getPrincipal());
response.setStatus(HttpServletResponse.SC_OK);
response.getWriter().write(mapper.writeValueAsString(authentication));
	}

}
