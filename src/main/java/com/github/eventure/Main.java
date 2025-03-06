package com.github.eventure;

import com.github.eventure.storage.Storage;

import java.util.List;

import com.github.eventure.controllers.UserController;
import com.github.eventure.model.Event;
import com.github.eventure.model.User;

public class Main {
    public static void main(String[] args) {
        // Armazenamento
        var eventStorage = new Storage<Event>();

        // Criar todos os eventos
        String[] names = { "Acelera Fatec", "Kapota", "Palestra Fatec", "Carnaval" };
        int counter = 0;
        for (String name: names) {
            var event = new Event();
            event.setId(++counter);
            event.setName(name);
            eventStorage.add(event);
        }

        // Encontrar eventos da Fatec
        for (Event event: eventStorage.find(
            event -> event.getName().contains("Fatec")
        ).toList()) {
            System.out.println(event.toString());
        }

        // Create a user
        var userController = new UserController();
        var user = userController.createUser("Chrystian", "Mendes Franklin", "exemplo123");
        

        System.out.println(user.getName());
        System.out.println(user.getPasswordHash().toString());
        String cpf = "29640340871";
        userController.validarCpf(cpf);
//        userController.deleteUser(user);
        
        List<String> emails = userController.findUser("Mendes Franklin");
        System.out.println("\nEmail do usuario com nome Chrystian Mendes Franklin:");
        emails.forEach(System.out::println);
  
        
        
    }
}
