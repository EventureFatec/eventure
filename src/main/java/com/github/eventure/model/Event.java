package com.github.eventure.model;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import com.github.eventure.model.address.Address;
import com.github.eventure.model.address.Cep;

public class Event {
	private int id;
	private String name;
	private String description;
	private String title;
	private EventClassification type;
	private List<Image> images;
	private Date date;
	private LocalTime startHours;
	private LocalTime endHours;
	private Address address;
	private Cep cep;

	public Event(int id, String name, String description, String title, EventClassification type) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.title = title;
		this.type = type;
	}

	public Event(int id, String name, String description, String title, EventClassification type, Date date,
			LocalTime startHours, LocalTime endHours, Cep cep) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.title = title;
		this.type = type;
		this.date = date;
		this.startHours = startHours;
		this.endHours = endHours;
		this.cep = cep;
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

	public Cep getCep() {
		return cep;
	}

	public void setCep(Cep cep) {
		this.cep = cep;
	}

	public void setName(Cep cep) {
		this.cep = cep;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public LocalTime getStartHours() {
		return startHours;
	}

	public void setStartHours(LocalTime startHours) {
		this.startHours = startHours;
	}

	public LocalTime getEndHours() {
		return endHours;
	}

	public void setEndHours(LocalTime endHours) {
		this.endHours = endHours;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public EventClassification getType() {
		return type;
	}

	public void setType(EventClassification type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return String.format("Evento(id=%d, name=%s)", id, name);
	}

}
