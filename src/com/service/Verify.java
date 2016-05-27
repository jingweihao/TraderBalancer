package com.service;

import javax.jws.WebService;

import com.data.User;

@WebService
public interface Verify 
{
	public User verfiyUser(User user);
}
