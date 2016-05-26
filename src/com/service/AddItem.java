package com.service;

import javax.jws.WebService;
import com.data.Sales;

@WebService
public interface AddItem 
{
	public String addItem(Sales sales);

}
