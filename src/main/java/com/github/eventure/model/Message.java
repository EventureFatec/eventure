package com.github.eventure.model;

import java.time.LocalTime;

public class Message {
	private String message;
	private String horario;
	private String name;
	private int idUser;
	public Message(String message,String horario,String name,int idUser)
	{
		this.message = message;
		this.horario = horario;
		this.name = name;
		this.idUser = idUser;
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
}
