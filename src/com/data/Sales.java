package com.data;


public class Sales
{
	private String id;
	private String imgpath;
	private String name;
	private String price;
	private String info;
	private String category;
	private String sellername;
	private String sellertel;
		
	public Sales()
	{
		
	}
	
	public Sales(String id, String imgpath, String name, String price, String info, String category, String sellername, String sellertel)
	{
		this.id = id;
		this.imgpath = imgpath;
		this.name = name;
		this.price = price;
		this.info = info;
		this.category = category;
		this.sellername = sellername;
		this.sellertel = sellertel;
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
	
	public void setSellername(String sellername)
	{
		this.sellername = sellername;
	}
	
	public void setSellertel(String sellertel)
	{
		this.sellertel = sellertel;
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
	
	public String getSellername()
	{
		return sellername;
	}
	
	public String getSellertel()
	{
		return sellertel;
	}
	
	public String toString()
	{
		return "(" + id + "," + name + "," + category + "," + price + "," + info + "," + imgpath + "," + sellername + "," + sellertel + ")";
	}

}
