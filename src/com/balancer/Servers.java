package com.balancer;

public class Servers
{
	private String address;
	private int connections;
	
	Servers(String address, int connections)
	{
		this.address = address;
		this.connections = connections;
	}
	
	public String getAddress()
	{
		return this.address;
	}
	
	public int getConnections()
	{
		return this.connections;
	}
	
	public String toString()
	{
	     return getAddress() + " - " + getConnections();
	}

}