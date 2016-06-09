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
	
	public boolean DeleteItemService(String category, String itemname, String itemid, String sellername);
	
	public User VerifyService(User user);
	
	public boolean RegisterService(User user);
	
	public String Hello1Service(String person, int ith);
	
	public ArrayList<SearchResult> TestSearch(String keyword, int ith);
	
	public ArrayList<Sales> TestSales(String username, int ith);
			
}