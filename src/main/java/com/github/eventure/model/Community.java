package com.github.eventure.model;

import java.util.ArrayList;
import java.util.List;

public class Community {
	private boolean ativo = true; 
	private String name;
	private String description;
	private int idMaker;
	private String imagePath;
	private int idCommunity;
	private List<Integer> IdUsers = new ArrayList<>();
	private List<Message> mensagens = new ArrayList<>();
	public Community() {

	}
	public Community(String name, int idMaker, int idCommunity ) {
		this.name = name;
		this.idMaker = idMaker;
		this.idCommunity = idCommunity;
	}

	public String getName() {
		return name;
	}

	public void addUserList(int idUser) {
		IdUsers.add(idUser);
	}
	
	public void removeUserList(int idUser)
	{
		IdUsers.remove(Integer.valueOf(idUser));
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdMaker() {
		return idMaker;
	}

	public void setIdMaker(int idMaker) {
		this.idMaker = idMaker;
	}

	public int getIdCommunity() {
		return idCommunity;
	}

	public void setIdCommunity(int idCommunity) {
		this.idCommunity = idCommunity;
	}

	public List<Integer> getIdUsers() {
		return IdUsers;
	}

	public void setIdUsers(List<Integer> idUsers) {
		IdUsers = idUsers;
	}

	public List<Message> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Message> mensagens) {
		this.mensagens = mensagens;
	}
	public void addMessage(Message message)
	{
		this.mensagens.add(message);
	}
	public void removeMessage(Message message)
	{
		this.mensagens.remove(message);
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	

}
