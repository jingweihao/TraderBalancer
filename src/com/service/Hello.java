package com.service;

import javax.jws.WebService;

@WebService
public interface Hello 
{
	public String sayHello(String person);
}
