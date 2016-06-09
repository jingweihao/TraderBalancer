package com.balancerLL;

import java.util.HashMap;
import java.util.Map;

public class Optimizer 
{
	//TODO 
	//1. if category name is the same as an itemname
	//2. different categories contain the same itemname. fruit/apple  phone/apple
	//2-> Map<String, List<recordCategory>>
	
	//TODO personal sale
	// use a hashmap to record the person information
	// hashmap<person, number of items>
	
	private Map<String, Integer> categoryList;// key: category value: number
	private Map<String, recordCategory> item; // key: item name    value: category+num
	private int randomnum;
	private int totalnum;  // total search return the number of satisfied records
	private int count;   //  total search numbers so far
	
	public Optimizer()
	{
		categoryList = new HashMap<String, Integer>();
		item = new HashMap<String, recordCategory>(); 
		randomnum = 5;
		totalnum = 0;
		count = 0;
	}
	
	public Map<String, Integer> getCategorylist()
	{
		return this.categoryList;
	}
	
	public int additem(String itemname, String category)  // add category return optimized steps number -constant time??
	{
		// add category or add the item number in this category
		if(categoryList.containsKey(category))
		{
			int oldvalue = categoryList.get(category);
			categoryList.put(category, oldvalue+1);
		}
		else
		{
			categoryList.put(category, 1);
		}
		
		//if this itemname is also in the search record, then number +1
		if(item.containsKey(itemname))
		{
			int oldvalue = item.get(itemname).getNum();
			recordCategory newrecord = new recordCategory(category, oldvalue+1);
			item.put(itemname, newrecord);
		}

		return 2; // add item + add user(assume this user not exist before)
	}
	
	public int deleteitem(String category, String itemname) // category/itemname/itemid
	{
		if(categoryList.containsKey(category))  // delete specified category number
		{
			int oldvalue = categoryList.get(category);
			if(oldvalue == 1)
			{
				categoryList.remove(category);
			}
			else categoryList.put(category, oldvalue-1);
		}
		
		if(item.containsKey(itemname))
		{
			int oldvalue = item.get(itemname).getNum();
			if(oldvalue == 1)
			{
				item.remove(itemname);
			}
			else
			{
				recordCategory newrecord = new recordCategory(category, oldvalue-1);
				item.put(itemname, newrecord);
			}
		}
		
		return 2;// default 2 step(delete item, delete person maybe)
	}
	
	public trueNameNum finditem(String itemname)
	{
		trueNameNum result = new trueNameNum();
		
		// if itemname is a category name
		if(categoryList.containsKey(itemname)) // if it is 
		{
			result.setNum(categoryList.get(itemname));
			result.setTruename(itemname);
		}
		else  
		{
			// if it has been searched before
			if(item.containsKey(itemname))
			{
				String truename = item.get(itemname).getCategory()+'/'+itemname;
				int num = item.get(itemname).getNum();
				result.setNum(num);
				result.setTruename(truename);
			}
			else
			{
				if(count == 0)  // first time to search- set initial random num = 5
				{
					result.setNum(randomnum);
					result.setTruename(itemname);
				}
				else  // not the first time, set the average number
				{
					result.setNum(totalnum/count); 
					result.setTruename(itemname);
				}
			}
		}
		return result;
	}
	
	public int getTotalNum()
	{
		return this.totalnum;
	}
	
	public int getCount()
	{
		return this.count;
	}
	
	public void addtotalnum(int realvalue) // the real numbers of items returned from server
	{
		totalnum += realvalue;
	}
	
	public void addcount()
	{
		count++;
	}
}
