package com.getService;

import java.util.*;

import javax.jws.WebService;

import com.data.Sales;
import com.data.SearchResult;
import com.data.User;

@WebService
public interface GetService 
{
	public ArrayList<SearchResult> SearchService(String keyword);
	
	public ArrayList<Sales> SalesService(String username);
	
	public String AddItemService(Sales sales);
	
	public boolean DeleteItemService(String itemid);
	
	public User VerifyService(User user);
	
	public boolean RegisterService(User user);
		
}