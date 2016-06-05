package com.balancer;
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
	
	private static getlistsingleton a = new getlistsingleton();
	public getlistsingleton()
	{
		//context = new ClassPathXmlApplicationContext(new String[]{"com/balancer/Function.xml"});
		context = new ClassPathXmlApplicationContext(new String[]{"com/balancer/LCFunction.xml"});
		serverlist = (getlist)context.getBean("ServerAddress");
		serverlist.setMap();
		serverlist.CreateQueue();
	}
	
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
	
	public void addconnection(String address)
	{
		serverlist.addconnection(address);
	}
	
	public void substractconn(String address)
	{
		serverlist.substractconn(address);
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

