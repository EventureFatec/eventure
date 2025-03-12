package com.github.eventure.model;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class Event {
	private int id;
	private String name;
	private String description;
	private String title;
	private String typeEvent;
	private String[] category = { "Musicais", "Festas e Shows", "Teatros e espetáculos", "Stand Up Comedy",
			"Passeios e Tours", "Esportes", "Congressos e Palestras", "Infaltil", "Gastronomia", "Cursos e Workshops",
			"Eventos online", "Tecnologia", "Religião e esperitualidade" };
	private List<Image> images;
	private Date date;
	// não sei qual é melhor para armazenar data se é a class Date ou uma string
	private LocalTime startHours;
	private LocalTime endHours;
	// Ideia para uso seria LocalTime hora = LocalTime.of(14,35); para pegar somente
	// hora e minutos
	// mesma ideia da data não sei qual é melhor por causa do espaço em memoria
	// ocupado
	private Address address;

	public Event(int id, String name, String description, String title, String typeEvent, List<Image> images, Date date,
			LocalTime startHours, LocalTime endHours, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.title = title;
		this.typeEvent = typeEvent;
		this.images = images;
		this.date = date;
		this.startHours = startHours;
		this.endHours = endHours;
		this.address = address;
	}

	public Event() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Evento(id=%d, name=%s)", id, name);
	}

}
