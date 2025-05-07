package com.github.eventure.storage;

import java.util.List;
import java.util.Optional;

import com.github.eventure.model.User;

public class UserStorage extends Storage<User> {
    private static final UserStorage instance = new UserStorage();

    // Construtor privado para garantir o padrão singleton
    private UserStorage() {
        super();
    }

    // Método público para obter a instância única
    public static UserStorage getInstance() {
        return instance;
    }

    // Retorna todos os usuários armazenados
    public List<User> getUsers() {
        return this;  // Como UserStorage herda de ArrayList<User>, podemos retornar diretamente a instância
    }

    // Encontrar um usuário pelo nome de usuário
    public Optional<User> findUserByUsername(String username) {
        return find(user -> user.getUsername().equals(username)).findFirst();
    }
}
