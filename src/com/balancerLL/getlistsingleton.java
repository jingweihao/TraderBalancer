package com.balancerLL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.getService.GetService;




public class getlistsingleton 
{
	private getlist serverlist;
	private ClassPathXmlApplicationContext context;
	private Optimizer smart;
	
	private static getlistsingleton a = new getlistsingleton();
	public getlistsingleton()
	{
		context = new ClassPathXmlApplicationContext(new String[]{"com/balancer/LCFunction.xml"});
		serverlist = (getlist)context.getBean("ServerAddress");
		serverlist.setMap();
		serverlist.CreateQueue();
		
		// smart initialize
		smart = new Optimizer();
	}
	// for service
	
	
	public trueNameNum finditem(String itemname)
	{
		return smart.finditem(itemname);
	}
	
	public int deleteitem(String category, String itemname)
	{
		return smart.deleteitem(itemname, itemname);
	}
	
	public int additem(String itemname, String category)
	{
		return smart.additem(itemname, category);
	}
	public void addtotalnum(int realvalue)
	{
		smart.addtotalnum(realvalue);
	}
	public void addcount()
	{
		smart.addcount();
	}
	
	
	
	
	//for queue
	public Map<String, Integer> getMap()
	{
		return serverlist.getMap();
	}
	
	public static final getlistsingleton getInstance()
	{

		return a;
	}

	public PriorityQueue<Servers> getQueue()
	{
		return serverlist.getQueue();
	}
	
	public void setQueue(PriorityQueue<Servers> qu)
	{
		serverlist.setPriorityQueue(qu);
	}
	
	public void printqueue()
	{
		serverlist.printqueue();
	}
	
	public void addconnection(String address, int num)
	{
		serverlist.addconnection(address, num);
	}
	
	public void substractconn(String address, int num)
	{
		serverlist.substractconn(address, num);
	}
	
	public String getMin()
	{
		return serverlist.getMin();
	}
	
	public void printconn()
	{
		serverlist.printmap();;
	}
	
	public int getConnections()
	{
		return serverlist.getConnections();
	}
}

