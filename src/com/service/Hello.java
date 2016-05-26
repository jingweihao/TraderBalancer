package com.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface Hello 
{
	public String sayHello(@WebParam(name="person") String person);
}
