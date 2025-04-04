package com.github.eventure.model;

import java.util.ArrayList;
import java.util.List;

public class Community {
	private String name;
	private String description;
	private int idMaker;
	// id do criador para diferenciar eles dos demais usuarios sendo ele que
	// controla quem ele coloca na comunidade
	private int idCommunity;
	// a ideia é ter o id dos usuarios participantes da comunidade para não precisar
	// armazenar o Usuario diretamente
	// a ideia é que os usuarios tenha uma area no programa aonde seja exibido as
	// comunidades que eles participam
	private List<Integer> IdUsers = new ArrayList<>();
	private List<Message> mensagens;

	// mensagem seria um conjunto de label para dar uma formatação a mensagem.
	// mensagem ia ter = nome do usuario , horario de envio e a propria mensagem
	public Community(String name, int idMaker) {
		this.name = name;
		this.idMaker = idMaker;
	}

	public String getName() {
		return name;
	}

	public void addUserList(int idUser) {
		IdUsers.add(idUser);
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

}
