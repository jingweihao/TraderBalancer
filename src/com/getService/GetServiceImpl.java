package com.getService;

import java.util.ArrayList;

import javax.jws.WebService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.balancerLL.LCLoadBalanceStrategy;
import com.balancerLL.getlistsingleton;
import com.balancerLL.trueNameNum;
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
//	private ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"com/balancer/RandomFunction.xml"});
	private ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"com/balancer/LCFunction.xml"});
//	private ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"com/balancerLL/LCFunction.xml"});
	
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
		
//		getlistsingleton estimate = getlistsingleton.getInstance();
//		String itemname = sales.getName();
//		String category = sales.getCategory();
//		int num = estimate.additem(itemname, category);		
//		LCLoadBalanceStrategy strategy = (LCLoadBalanceStrategy) context.getBean("RandomAddressesAD");
//		strategy.setestimatenum(num);		
//		System.out.println("balance AddItemService~~~~~~");
//		return ai.addItem(sales);
	}
	
	public boolean DeleteItemService(String category, String itemname, String itemid, String sellername)
	{
		DeleteItem di = (DeleteItem)context.getBean("balancer4");
		System.out.println("balance DeleteItemService~~~~~~");
		return di.deleteItem(category, itemname, itemid, sellername);
		
//		getlistsingleton estimate = getlistsingleton.getInstance();
//		int num = estimate.deleteitem(category, itemname);		
//		LCLoadBalanceStrategy strategy = (LCLoadBalanceStrategy) context.getBean("RandomAddressesDEL");
//		strategy.setestimatenum(num);		
//		return di.deleteItem(category, itemname, itemid, sellername);
		
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

	public String Hello1Service(String person, int ith) 
	{
		String balancer = "balancer" + (6 + ith);
		Hello1 h = (Hello1)context.getBean(balancer);
		System.out.println("balance Hello1Service~~~~~~~~~");
		return h.sayHello1(person);
	}
	
	public ArrayList<SearchResult> TestSearch(String keyword, int ith)
	{
		String balancer = "balancer" + (16 + ith);
		Search search = (Search)context.getBean(balancer);
		System.out.println("balance TestSearch~~~~~~");
		return search.doSearch(keyword);
		
//		getlistsingleton estimate = getlistsingleton.getInstance();
//		trueNameNum trueitemname = estimate.finditem(keyword);
//		int num = trueitemname.getNum();
//		String itemname = trueitemname.getTruename();	
//		String addresses = "RandomS" + ith;
//		LCLoadBalanceStrategy strategy = (LCLoadBalanceStrategy)context.getBean(addresses);
//		strategy.setestimatenum(num);	
//		
//		System.out.println("balance TestSearch~~~~~~");
//		
//		ArrayList<SearchResult> result = search.doSearch(itemname);
//		int returnsize = result.size();
//		estimate.addtotalnum(returnsize);
//		estimate.addcount();
//		
//		return result;

	}

	public ArrayList<Sales> TestSales(String username, int ith)
	{
		String balancer = "balancer" + (26 + ith);
		PersonalSales ps = (PersonalSales)context.getBean(balancer);
		System.out.println("balance TestSales~~~~~~");
		return ps.getSales(username);
		
//		String addresses = "RandomPS" + ith;
//		LCLoadBalanceStrategy strategy = (LCLoadBalanceStrategy) context.getBean(addresses);
//		strategy.setestimatenum(2); // default login 2
//		
//		System.out.println("balance TestSales~~~~~~");
//		return ps.getSales(username);

	}
	
}
