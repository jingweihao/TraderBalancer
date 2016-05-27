package com.data;

public class User 
{
	private String username;
	private String tel;
	private String password;
	private String confirmpassword;
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setTel(String tel)
	{
		this.tel = tel;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public void setConfirmpassword(String confirmpassword)
	{
		this.confirmpassword = confirmpassword;
	}

	public String getUsername()
	{
		return username;
	}
	
	public String getTel()
	{
		return tel;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getConfirmpassword()
	{
		return confirmpassword;
	}

}
