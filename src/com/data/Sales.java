package com.data;


public class Sales
{
	private String id;
	private String img;
	private String name;
	private String price;
	private String info;
		
	public Sales()
	{
		
	}
	
	public Sales(String id, String img, String name, String price, String info)
	{
		this.id = id;
		this.img = img;
		this.name = name;
		this.price = price;
		this.info = info;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public void setImg(String img)
	{
		this.img = img;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setPrice(String price)
	{
		this.price = price;
	}
	
	public void setInfo(String info)
	{
		this.info = info;
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getImg()
	{
		return img;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getPrice()
	{
		return price;
	}
	
	public String getInfo()
	{
		return info;
	}


}
