package com.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface Hello1 
{
	public String sayHello1(@WebParam(name="person") String person);
	
	public String sayHello2(String person);
}
