package com.github.eventure.model;

import java.util.List;
import java.util.ArrayList;
import com.github.eventure.model.passwords.Password;

public class User {

    public User() {
    	
    }

    private String name;
    private String email;
    private String username;
    private Password password;
    private int userId;
    private String cpf;
	private String profilePic;
    private boolean organazador = false;
    private boolean isLogged = false;
    private List<Integer> myEventsList = new ArrayList<>(); // salva uma lista com todos os eventos que ele criou
    private List<Integer> eventsList = new ArrayList<>();  // Inicializando a lista de eventos
    private List<Integer> communityList = new ArrayList<>(); // Inicializando a lista de comunidades
    private List<Integer> myCommunityList = new ArrayList<>();

    // Construtor
    public User(String name, String email, String username, Password password, int userId, String cpf, String profilePic, boolean isLogged) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.cpf = cpf;
		this.profilePic = profilePic;
        this.isLogged = isLogged;
    }

    // Getters e Setters
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean getIsLogged(boolean isLogged) {
        return this.isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

	public String getProfilePic() {
		return this.profilePic;
	}
	
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

    public boolean isOrganazador() {
        return organazador;
    }

    public void setOrganazador(boolean organazador) {
        this.organazador = organazador;
    }

    public List<Integer> getEventsList() {
        return eventsList;
    }

    public void addListaEventos(int id) {
        eventsList.add(id);
    }

    public List<Integer> getCommunityList() {
        return communityList;
    }

    public void addCommunity(int communityId) {
        communityList.add(communityId);
    }
    public void addListMyEvents(int idEvento) {
    	myEventsList.add(idEvento);
    }
    public void removeListMyEvents(int idEvento) {
    	myEventsList.remove(idEvento);
    }

	public List<Integer> getMyEventsList() {
		return myEventsList;
	}

	public void setMyEventsList(List<Integer> myEventsList) {
		this.myEventsList = myEventsList;
	}
    
}
