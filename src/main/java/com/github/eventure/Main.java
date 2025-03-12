package com.github.eventure;

import com.github.eventure.storage.Storage;

import java.util.ArrayList;
import java.util.List;

import com.github.eventure.controllers.AddressController;
import com.github.eventure.controllers.UserController;
import com.github.eventure.model.Address;
import com.github.eventure.model.Event;
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
        
        var d = new Address(" Espaço capela "," 450 " , "rua ronaldo viera" , "São paulo" , "capela" , "lavrinhas" , "1276000" ,id);
        var d2 = new Address(" Espaço cruzeiro "," 600 " , "rua dois" , "São paulo" , "centro" , "cruzeiro" , "1270000" , id);

        AddressController ad = new AddressController();
        ad.createAddres(d);
        ad.createAddres(d2);
        ad.imprimir();
        
	}
}
