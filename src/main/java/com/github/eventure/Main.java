package com.github.eventure;

import com.github.eventure.storage.Storage;

import java.util.ArrayList;
import java.util.List;

import com.github.eventure.controllers.AddressController;
import com.github.eventure.controllers.EventController;
import com.github.eventure.controllers.UserController;
import com.github.eventure.model.Address;
import com.github.eventure.model.Event;
import com.github.eventure.model.EventClassification;
import com.github.eventure.model.User;

public class Main {
	public static void main(String[] args) {
		// Armazenamento
		var eventStorage = new Storage<Event>();

		// Criar todos os eventos
		String[] names = { "Acelera Fatec", "Kapota", "Palestra Fatec", "Carnaval" };
		int counter = 0;
		for (String name : names) {
			var event = new Event();
			event.setId(++counter);
			event.setName(name);
			eventStorage.add(event);
		}

		// Encontrar eventos da Fatec
		for (Event event : eventStorage.find(event -> event.getName().contains("Fatec")).toList()) {
			System.out.println(event.toString());
		}

		// Create a user
		var userController = new UserController();
		var user = userController.createUser("Chrystian", "Mendes Franklin", "exemplo123", "Chrystian@gmail.com",
				"52998224725");

		System.out.println(user.getName());
		System.out.println(user.getPasswordHash().toString());
		System.out.println(user.getCpf());
		System.out.println("id do chrystian " + user.getUserId());
		userController.cloneUser("Chrystian", "Mendes Franklin", "Queijo123", "Chrystian02@gmail.com", "5299", 0);
		int id = user.getUserId();
		System.out.println(user.getName());
		System.out.println(user.getPasswordHash().toString());
		System.out.println(user.getCpf());
		System.out.println(user.getEmail());
		System.out.println("id do chrystian " + user.getUserId());
        
//        var d = new Address(" Espaço capela "," 450 " , "rua ronaldo viera" , "São paulo" , "capela" , "lavrinhas" , "1276000" ,id);
//        var d2 = new Address(" Espaço cruzeiro "," 600 " , "rua dois" , "São paulo" , "centro" , "cruzeiro" , "1270000" , id);
//
//        AddressController ad = new AddressController();
//        ad.createAddres(d);
//        ad.createAddres(d2);
//        ad.imprimir();
        EventController e = new EventController();
        EventClassification type = EventClassification.SCIENTIFIC;
        EventClassification type2 = EventClassification.PARTIES_AND_SHOWS;
        EventClassification type3 = EventClassification.COURSES_AND_WORKSHOPS;
        e.createEvent(id, "feira de ciencias", "uma feira de ciencias", "feira de ciencias dia 17", type );
        e.createEvent(id, "show", "show do ze", "show do ze as 10", type2);
        e.createEvent(id, "curso de java", "curso para aprender java", "curso de java dia 20 ", type3);
        e.createEvent(id, "curso de c#", "curso para aprender c#", "curso de c# dia 20 ", type3);
        List<EventClassification> lista = new ArrayList<EventClassification>();
        lista.add(type3);
        lista.add(type);
//        e.imprimir();
        e.filtro(lista);
        
        
//        System.out.println(type.getLabel());

	}
}
