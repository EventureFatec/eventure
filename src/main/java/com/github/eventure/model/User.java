package com.github.eventure.model;

public class User {

	private String name;
	private String email;
	private String password;
	private int userId;
	private int cpf;
	public String getName()
	{
		return this.name;
	}
	public void setName (String name)
	{
		this.name = name;
	}
	public String getEmail()
	{
		return this.email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPassword()
	{
		return this.password;
	}
	public void setPassword(String password)
	{
       this.password = password;
	}
	public int getUserId()
	{
		return this.userId;
	}
	public int getCpf()
	{
		return this.cpf;
	}
	public void setCpf(int cpf)
	{
		this.cpf = cpf;
	}
}
