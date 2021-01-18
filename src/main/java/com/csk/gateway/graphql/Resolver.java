package com.csk.gateway.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.csk.gateway.client.CustomerSearch;
import com.csk.gateway.client.CustomerService;
import com.csk.gateway.client.UserService;

@Service
public class Resolver implements GraphQLQueryResolver {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerSearch  customer;
	
	@Autowired
	private UserService  user;
	
	public String testQuery()
	{
		return "Hello";
	}

}
