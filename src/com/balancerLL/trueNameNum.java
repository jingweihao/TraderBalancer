package com.balancerLL;

public class trueNameNum 
{
	private String truename;
	private int num;
	
	public trueNameNum()
	{
		
	}
	public trueNameNum(String truename, int num)
	{
		this.truename = truename;
		this.num = num;
	}
	
	public int getNum()
	{
		return this.num;
	}
	
	public String getTruename()
	{
		return this.truename;
	}
	
	public void setTruename(String truename)
	{
		this.truename = truename;
	}
	
	public void setNum(int num)
	{
		this.num = num;
	}
}
