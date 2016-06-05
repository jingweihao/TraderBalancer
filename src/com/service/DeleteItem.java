package com.service;

import javax.jws.WebService;

@WebService
public interface DeleteItem 
{
	public boolean deleteItem(String category, String itemname, String itemid);
	
}
