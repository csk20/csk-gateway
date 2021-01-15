package com.csk.gateway.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.csk.gateway.client.CustomerService;

@Service
public class Resolver implements GraphQLQueryResolver {
	
	@Autowired
	private CustomerService customerSearch;
	
	public String testQuery()
	{
		return "Hello";
	}

}
