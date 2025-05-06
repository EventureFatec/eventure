package com.github.eventure.model;

import java.util.List;

import com.github.eventure.model.passwords.Password;

public class User {

	private String name;
	private String email;
	private String userName;
	private Password password;
	private int userId;
	private String cpf;
    private boolean organazador = false;
    private List<Integer> eventsList;  // lista de eventos que o usuario partocipa
    private List<Integer> communityList; // lista de comunidades que o usuario participa
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
	public void addListaEventos(int id)
	{
		eventsList.add(id);
	}
	
}
