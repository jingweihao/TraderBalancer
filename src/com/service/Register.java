package com.service;

import javax.jws.WebService;

import com.data.User;

@WebService
public interface Register 
{
	public boolean registerUser(User user);
}
