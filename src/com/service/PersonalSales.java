package com.service;

import java.util.*;
import javax.jws.WebService;
import com.data.Sales;

@WebService
public interface PersonalSales 
{
    public ArrayList<Sales> getSales(String username);
}
