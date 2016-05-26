package com.data;


public class Sales
{
	private String id;
	private String imgpath;
	private String name;
	private String price;
	private String info;
	private String category;
		
	public Sales()
	{
		
	}
	
	public Sales(String id, String imgpath, String name, String price, String info)
	{
		this.id = id;
		this.imgpath = imgpath;
		this.name = name;
		this.price = price;
		this.info = info;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public void setImgpath(String imgpath)
	{
		this.imgpath = imgpath;
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
	
	public void setCategory(String category)
	{
		this.category = category;
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getImgpath()
	{
		return imgpath;
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

	public String getCategory()
	{
		return category;
	}
	
	public String toString()
	{
		return "(" + id + "," + name + "," + category + "," + price + "," + info + "," + imgpath + ")";
	}

}
