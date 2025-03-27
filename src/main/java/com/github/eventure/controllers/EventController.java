package com.github.eventure.controllers;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.github.eventure.model.Event;
import com.github.eventure.model.EventClassification;
import com.github.eventure.storage.Storage;

public class EventController {
	private Storage<Event> eventController;
    private LocalTime endHora;
    private LocalTime startHora;
    private Date d;
	public EventController() {
		if (eventController == null) {
			eventController = new Storage<Event>();
		}
	}

	public void createEvent(int id, String name, String description, String title, EventClassification type,
			String date, String startHours, String endHours) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("HH:mm");

		try {
			d = sdf.parse(date);
			String data = sdf.format(d);
			System.out.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			endHora = LocalTime.parse(endHours, dt);
			startHora = LocalTime.parse(startHours, dt);
			System.out.println("hora de inicio = " + startHora);
			System.out.println("hora de termino = " + endHora);
		} catch (Exception e) {
			e.printStackTrace();
		}

		var e = new Event(id, name, description, title, type, d, endHora, startHora);
		eventController.add(e);

	}

	public void print(List<Event> eventos) {
		for (Event eb : eventos) {
			System.out.println(eb.getId());
			System.out.println(eb.getName());
			System.out.println(eb.getDescription());
			System.out.println(eb.getTitle());
			System.out.println(eb.getType().getLabel());
			System.out.println("---------------------------");
		}

	}

	public void filterCategories(List<EventClassification> o) {
		List<Event> eventos = eventController.find(event -> o.contains(event.getType())).collect(Collectors.toList());
		print(eventos);
	}
}
