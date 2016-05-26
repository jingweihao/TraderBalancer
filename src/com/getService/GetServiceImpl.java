package com.getService;

import java.util.ArrayList;

import javax.jws.WebService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.data.Sales;
import com.data.SearchResult;
import com.service.AddItem;
import com.service.DeleteItem;
import com.service.PersonalSales;
import com.service.Search;

@WebService(endpointInterface = "com.getService.GetService", serviceName = "GetService")
public class GetServiceImpl implements GetService
{
	private ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"com/balancer/Function.xml"});
	
	public ArrayList<SearchResult> SearchService(String keyword) 
	{
		Search search = (Search)context.getBean("balancer1");
		System.out.println("balance SearchService~~~~~~");
		return search.doSearch(keyword);
	}
	
	public ArrayList<Sales> SalesService(String username)
	{
		PersonalSales ps = (PersonalSales)context.getBean("balancer2");
		System.out.println("balance SalesService~~~~~~");
		return ps.getSales(username);
	}
	
	public String AddItemService(Sales sales)
	{
		AddItem ai = (AddItem)context.getBean("balancer3");
		System.out.println("balance AddItemService~~~~~~");
		return ai.addItem(sales);
	}
	
	public boolean DeleteItemService(String itemid)
	{
		DeleteItem di = (DeleteItem)context.getBean("balancer4");
		System.out.println("balance DeleteItemService~~~~~~");
		return di.deleteItem(itemid);
	}

}
