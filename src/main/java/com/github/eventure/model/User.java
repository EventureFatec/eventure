package com.github.eventure.model;

import com.github.eventure.model.passwords.Password;

public class User {

	private String name;
	private String email;
	private Password password;
	private int userId;
	private String cpf;
    private boolean organazador = false;
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public boolean isOrganazador() {
		return organazador;
	}

	public void setOrganazador(boolean organazador) {
		this.organazador = organazador;
	}
	
}
