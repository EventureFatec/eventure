
package com.github.eventure.controllers;

//import java.time.LocalTime;
//import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.github.eventure.model.Address;
import com.github.eventure.model.Event;
import com.github.eventure.model.EventClassification;
import com.github.eventure.model.address.Cep;
import com.github.eventure.storage.Storage;

public class EventController {
	private Storage<Event> eventController;
	private static EventController instance;
	private static int lastGeneratedId = 0;

	private EventController() {
		if (eventController == null) {
			eventController = new Storage<Event>();
		}
	}

	public static EventController getInstance() {
		if (instance == null) {
			instance = new EventController();
		}
		return instance;
	}

	public void createEvent(int idMaker, String title, String description, EventClassification type, String date,
			String startHours, String endHours, String caminho, String cep, String estado, String cidade, String bairro,
			String rua, String numero, String complemento) {
		var e = new Event();
		var address = new Address();
		e.setTitle(title);
		e.setDescription(description);
		e.setType(type);
		e.setDate(date);
		e.setStartHours(startHours);
		e.setEndHours(endHours);
		e.setImagePath(caminho);
		address.setCep(cep);
		address.setEstado(estado);
		address.setCidade(cidade);
		address.setBairro(bairro);
		address.setRua(rua);
		address.setNumero(numero);
		if (!complemento.isEmpty()) {
			address.setComplemento(complemento);
		}
		e.setAddress(address);
		if (!e.getTitle().isEmpty() && !e.getDate().isEmpty() && !e.getStartHours().isEmpty()
				&& !e.getImagePath().isEmpty() && e.getAddress() != null) {
			e.setId(generateId());
			e.setIdMaker(idMaker);
			e.addConfirmedParticipantIds(idMaker);
			var userController = UserController.getInstance();
			var user = userController.findUserById(idMaker);
			user.addListMyEvents(e.getId());
			eventController.add(e);
			JOptionPane.showMessageDialog(null, "Evento criado com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao criar evento, Preencha os campos corretamente");
		}

	}

	public void createEvent(int id, String description, String title, EventClassification type) {

		System.out.println("deu certo");
	}

	public void deleteEvent(Event e) {
		eventController.remove(e);
	}

	public void deleteEventById(int id) {
		// deletar caso o que eu tenha seja o id da empresa
		var e = findEventById(id);
		eventController.remove(e);
	}

	public Event findEventById(int id) {
		return eventController.find(event -> event.getId() == id).findFirst().orElse(null);
	}

	public Event findEventByExactTitle(String title) {
		return eventController.find(event -> event.getTitle().equals(title)).findFirst().orElse(null);
	}

	public List<Event> findEventsByTitleContaining(String title) {
		return eventController.find(event -> event.getTitle().toLowerCase().contains(title.toLowerCase()))
				.collect(Collectors.toList());
	}

	public void eventClone(int idEvent, String title, String description, EventClassification type, String date,
			String startHours, String endHours, String caminho, String cep, String estado, String cidade, String bairro,
			String rua, String numero, String complemento) {
		var eventClone = new Event();
		eventClone.setTitle(title);
		eventClone.setDescription(description);
		eventClone.setType(type);
		eventClone.setDate(date);
		eventClone.setStartHours(startHours);
		eventClone.setEndHours(endHours);
		eventClone.setImagePath(caminho);
		var address = new Address();
		address.setCep(cep);
		address.setEstado(estado);
		address.setCidade(cidade);
		address.setBairro(bairro);
		address.setComplemento(complemento);
		address.setNumero(numero);
		address.setRua(rua);
		eventClone.setAddress(address);
		eventClone.setId(idEvent);
		applyChanges(idEvent, eventClone);
	}

	public void applyChanges(int id, Event eventClone) {
		var event = findEventById(id);
		if (event == null) {
			return;
		}
		if (eventClone.getTitle() != null && !eventClone.getTitle().trim().isEmpty()
				&& !eventClone.getTitle().equals(event.getTitle())) {
			event.setTitle(eventClone.getTitle());
		}

		if (eventClone.getDescription() != null && !eventClone.getDescription().trim().isEmpty()
				&& !(eventClone.getDescription().equals(event.getDescription()))) {
			event.setDescription(eventClone.getDescription());
		}
		if (eventClone.getTitle() != null && !eventClone.getTitle().trim().isEmpty()
				&& !(eventClone.getTitle().equals(event.getTitle()))) {
			event.setTitle(eventClone.getTitle());
		}
		if (eventClone.getDate() != null && !(eventClone.getDate().equals(event.getDate()))) {
			event.setDate(eventClone.getDate());
		}
		if (eventClone.getStartHours() != null && !(eventClone.getStartHours().equals(event.getStartHours()))) {
			event.setStartHours(eventClone.getStartHours());
		}
		if (eventClone.getEndHours() != null && !(eventClone.getEndHours().equals(event.getEndHours()))) {
			event.setEndHours(eventClone.getEndHours());
		}
		if (eventClone.getImagePath() != null && !(eventClone.getImagePath().equals(event.getImagePath()))) {
			event.setImagePath(eventClone.getImagePath());
		}
		if (eventClone.getAddress().getCep() != null
				&& !(eventClone.getAddress().getCep().equals(event.getAddress().getCep()))) {
			event.getAddress().setCep(eventClone.getAddress().getCep());
		}
		if (eventClone.getAddress().getEstado() != null
				&& !(eventClone.getAddress().getEstado().equals(event.getAddress().getEstado()))) {
			event.getAddress().setEstado(eventClone.getAddress().getEstado());
		}
		if (eventClone.getAddress().getCidade() != null
				&& !(eventClone.getAddress().getCidade().equals(event.getAddress().getCidade()))) {
			event.getAddress().setCidade(eventClone.getAddress().getCidade());
		}
		if (eventClone.getAddress().getBairro() != null
				&& !(eventClone.getAddress().getBairro().equals(event.getAddress().getBairro()))) {
			event.getAddress().setBairro(eventClone.getAddress().getBairro());
		}
		if (eventClone.getAddress().getRua() != null
				&& !(eventClone.getAddress().getRua().equals(event.getAddress().getRua()))) {
			event.getAddress().setRua(eventClone.getAddress().getRua());
		}
		if (eventClone.getAddress().getComplemento() != null
				&& !(eventClone.getAddress().getComplemento().equals(event.getAddress().getComplemento()))) {
			event.getAddress().setComplemento(eventClone.getAddress().getComplemento());
		}
		if (eventClone.getAddress().getNumero() != null
				&& !(eventClone.getAddress().getNumero().equals(event.getAddress().getNumero()))) {
			event.getAddress().setNumero(eventClone.getAddress().getNumero());
		}

	}
	
	// metodo para adicionar o usuario a lista de participantes de um evento sendo que se evento for publico
	// ele adiciona automaticamente se não for ele adiciona a lista de solicitaçoes que o usuario so é adicionado
	// se o criador do evento aceitar
	public void adicionarParticipante(int idEvent, int idUser)
	{
		boolean publicOrPrivate = true;
		Event event = findEventById(idEvent);
		if(!event.usersParticipaOuNão(idUser))
		{
		 if(publicOrPrivate)
		 {
			event.addConfirmedParticipantIds(idUser);
			JOptionPane.showInternalMessageDialog(null,"Presença confirmada");
			//  evento publico
		 }else
		  {
			 // evento privado
			 // adiciona o usuario a uma lista que o criador vai decidir se confirma ou não a participação
			event.addPendingRequestIds(idUser);
		  }
		}else {
			JOptionPane.showMessageDialog(null, "Você já participa desse evento!");
		}
	}
	
	public void adicionarParticipantesPrivateEvento(int idEvent,int idUser) {
		Event event = findEventById(idEvent);
		event.addConfirmedParticipantIds(idUser);
	    JOptionPane.showInternalMessageDialog(null,"Presença confirmada");
	}

	public void print(List<Event> eventos) {
		for (Event eb : eventos) {
			System.out.println(eb.getId());
			System.out.println(eb.getTitle());
			System.out.println(eb.getDescription());
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
			System.out.println(eb.getTitle());
			System.out.println(eb.getDescription());
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

	public void filterCategories(List<EventClassification> eventClassification) {
		List<Event> eventos = eventController.find(event -> eventClassification.contains(event.getType()))
				.collect(Collectors.toList());
		print(eventos);
	}

	public List<Event> getAllEvents() {
		return eventController.toList();
	}

	public List<Event> filterEventByPesquisa(String pesquisa) {
		List<Event> eventos = eventController.find(event -> event.getTitle().toLowerCase().contains(pesquisa))
				.collect(Collectors.toList());
		return eventos;
	}

	public void createEventSemMessageBox(int idMaker, String title, String description, EventClassification type,
			String date,
			String startHours, String endHours, String caminho, String cep, String estado, String cidade, String bairro,
			String rua, String numero, String complemento) {
		var e = new Event();
		var address = new Address();
		e.setTitle(title);
		e.setDescription(description);
		e.setType(type);
		e.setDate(date);
		e.setStartHours(startHours);
		e.setEndHours(endHours);
		e.setImagePath(caminho);
		address.setCep(cep);
		address.setEstado(estado);
		address.setCidade(cidade);
		address.setBairro(bairro);
		address.setRua(rua);
		address.setNumero(numero);
		if (!complemento.isEmpty()) {
			address.setComplemento(complemento);
		}
		e.setAddress(address);
		if (!e.getTitle().isEmpty() && !e.getDate().isEmpty() && !e.getStartHours().isEmpty()
				&& !e.getImagePath().isEmpty() && e.getAddress() != null) {
			e.setId(generateId());
			e.setIdMaker(idMaker);
			e.addConfirmedParticipantIds(idMaker);
			var userController = UserController.getInstance();
			var user = userController.findUserById(idMaker);
			user.addListMyEvents(e.getId());
			eventController.add(e);
			// JOptionPane.showMessageDialog(null, "Evento criado com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao criar evento, Preencha os campos corretamente");
		}

	}

	public static int generateId() {
		return lastGeneratedId++;
	}
}
