package com.balancer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class getlist
{
	private List<String> addresses = new ArrayList<String>();
	PriorityQueue<Servers> queue; 
	Map<String, Integer> recordconn = new HashMap<String, Integer>();
	List<Servers> servers = new ArrayList<Servers>();
	
	public getlist()
	{
		Comparator<Servers> com = new Comparator<Servers>()
				{
					public int compare(Servers s1, Servers s2)
					{
						return s1.getConnections() - s2.getConnections();
					}
				};
		
		queue = new PriorityQueue<Servers>(3, com);

	}
	
	public void setAddresses(List<String> addresses)
	{
		this.addresses = addresses;
	}
	
	public void setMap()
	{
		for(int i = 0; i < addresses.size(); i++)
		{
			recordconn.put(addresses.get(i), 0);
		}
		System.out.println(recordconn);
	}
	
	public Map<String, Integer> getMap()
	{
		return recordconn;
	}
	public List<String> get()
	{
		return addresses;
	}
	
	public List<Servers> createServers()
	{
		List<Servers> result = new ArrayList<Servers>();
		for(int i = 0; i < addresses.size(); i++)
		{
			Servers s = new Servers(addresses.get(i), 0);
			result.add(s);	
		}
		return result;
	}
	
	public void CreateQueue()
	{
		for(int i = 0; i < addresses.size(); i++)
		{
			Servers s = new Servers(addresses.get(i), 0);
			queue.add(s);	
		}

	}
	
	public PriorityQueue<Servers> getQueue()
	{
		return this.queue;
	}
	
	public void setPriorityQueue(PriorityQueue<Servers> qu)
	{
		this.queue = qu;
	}
	
	public void addconnection(String address)
	{
		int oldvalue = recordconn.get(address);
		recordconn.put(address, oldvalue+1);
		
		Servers s = new Servers(address, oldvalue+1);
//		queue.clear();
//		for(String key: recordconn.keySet())
//		{
//			Servers s = new Servers(key, recordconn.get(key));
//			queue.add(s);
//		}
		queue.add(s);
		
		System.out.println("queue context: "+ queue);
	}
	
	public void substractconn(String address)
	{
		int oldvalue = recordconn.get(address);
		recordconn.put(address, oldvalue-1);
		
		queue.clear();
		for(String key: recordconn.keySet())
		{
			Servers s = new Servers(key, recordconn.get(key));
			
			queue.add(s);
		}
//		Servers s = new Servers(address, oldvalue-1);
//		queue.add(s);
		
	}
	
	public void printqueue()
	{
		System.out.println("queue context: "+ queue);
	}
	
	public void printmap()
	{
		System.out.println(recordconn);
	}
	
	public String getMin()
	{
		System.out.println(queue);
		return queue.peek().getAddress();
	}
	
	public int getConnections()
	{
		return queue.peek().getConnections();
	}
	
	

}
