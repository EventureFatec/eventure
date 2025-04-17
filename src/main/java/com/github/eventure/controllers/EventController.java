
package com.github.eventure.controllers;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.github.eventure.model.Event;
import com.github.eventure.model.EventClassification;
import com.github.eventure.model.address.Address;
import com.github.eventure.model.address.Cep;
import com.github.eventure.storage.Storage;

public class EventController {
	private Storage<Event> eventController;

	public EventController() {
		if (eventController == null) {
			eventController = new Storage<Event>();
		}
	}

	public void createEvent(int id, String name, String description, String title, EventClassification type, Date date,
			LocalTime startHours, LocalTime endHours, Cep cep) {
		var e = new Event(id, name, description, title, type, date, startHours, endHours, cep);
		eventController.add(e);

	}

	public void createEvent(int id, String name, String description, String title, EventClassification type) {
		var e = new Event(id, name, description, title, type);
		eventController.add(e);
		System.out.println("deu certo");
	}

	public void print(List<Event> eventos) {
		for (Event eb : eventos) {
			System.out.println(eb.getId());
			System.out.println(eb.getName());
			System.out.println(eb.getDescription());
			System.out.println(eb.getTitle());
			System.out.println(eb.getType().getLabel());
			System.out.println(eb.getDate().toString());
			System.out.println(eb.getEndHours().toString());
			System.out.println(eb.getStartHours().toString());
			Cep cep = new Cep();
			cep = eb.getCep();
			System.out.println(cep.getLocality());
			System.out.println(cep.getState());
			System.out.println("---------------------------");
		}

	}

	public void print() {
		for (Event eb : eventController) {
			System.out.println(eb.getId());
			System.out.println(eb.getName());
			System.out.println(eb.getDescription());
			System.out.println(eb.getTitle());
			System.out.println(eb.getType().getLabel());
			System.out.println(eb.getDate().toString());
			System.out.println(eb.getEndHours().toString());
			System.out.println(eb.getStartHours().toString());
			Cep cep = new Cep();
			cep = eb.getCep();
			System.out.println(cep.getLocality());
			System.out.println(cep.getState());
			System.out.println("---------------------------");
		}

	}

	public void filterCategories(List<EventClassification> o) {
		List<Event> eventos = eventController.find(event -> o.contains(event.getType())).collect(Collectors.toList());
		print(eventos);
	}
}
