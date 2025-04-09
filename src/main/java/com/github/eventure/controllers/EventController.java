
package com.github.eventure.controllers;

import java.util.List;
import java.util.stream.Collectors;
import com.github.eventure.model.Event;
import com.github.eventure.model.EventClassification;
import com.github.eventure.storage.Storage;

public class EventController {
	private Storage<Event> eventController;

	public EventController() {
		if (eventController == null) {
			eventController = new Storage<Event>();
		}
	}

	public void createEvent(int id, String name, String description, String title, EventClassification type) {
		var e = new Event(id, name, description, title, type);
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
