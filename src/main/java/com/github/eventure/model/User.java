package com.github.eventure.model;

public class User {

	private String name;
	private String email;
	private byte[] passwordSalt;
    private byte[] passwordHash;
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

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    public byte[] getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(byte[] passwordSalt) {
        this.passwordSalt = passwordSalt;
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
