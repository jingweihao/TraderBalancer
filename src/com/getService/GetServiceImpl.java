package com.getService;

import java.util.ArrayList;

import javax.jws.WebService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.data.Sales;
import com.data.SearchResult;
import com.data.User;
import com.service.AddItem;
import com.service.DeleteItem;
import com.service.Hello1;
import com.service.PersonalSales;
import com.service.Register;
import com.service.Search;
import com.service.Verify;

@WebService(endpointInterface = "com.getService.GetService", serviceName = "GetService")
public class GetServiceImpl implements GetService
{
//	private ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"com/balancer/Function.xml"});
	private ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"com/balancer/LCFunction.xml"});
	
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
	
	public boolean DeleteItemService(String category, String itemname, String itemid, String sellername)
	{
		DeleteItem di = (DeleteItem)context.getBean("balancer4");
		System.out.println("balance DeleteItemService~~~~~~");
		return di.deleteItem(category, itemname, itemid, sellername);
	}
	
	public User VerifyService(User user)
	{
		Verify vr = (Verify)context.getBean("balancer5");
		System.out.println("balance VerifyService~~~~~~~~~");
		return vr.verfiyUser(user);
	}
	
	public boolean RegisterService(User user)
	{
		Register rg = (Register)context.getBean("balancer6");
		System.out.println("balance RegisterService~~~~~~~~");
		return rg.registerUser(user);
	}

	public String Hello1Service(String person) 
	{
		Hello1 h = (Hello1)context.getBean("balancer7");
		System.out.println("balance Hello1Service~~~~~~~~~");
		return h.sayHello1(person);
	}

	public String Hello2Service(String person) 
	{
		Hello1 h = (Hello1)context.getBean("balancer7");
		System.out.println("balance Hello2Service~~~~~~~~~");
		return h.sayHello2(person);
	}

}
