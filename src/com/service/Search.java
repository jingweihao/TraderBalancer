package com.service;

import java.util.*;

import javax.jws.WebService;

import com.data.SearchResult;

@WebService
public interface Search 
{
	public ArrayList<SearchResult> doSearch(String keyword);
}
