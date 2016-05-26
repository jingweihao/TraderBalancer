package com.service;

import javax.jws.WebService;

@WebService
public interface DeleteItem 
{
	public boolean deleteItem(String itemid);
	
}
