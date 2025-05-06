//package com.github.eventure.storage;
//
//import com.github.eventure.model.User;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Stream;
//
//public class UserStorage {
//    private static final List<User> users = new ArrayList<>();  // Armazena os usuários em memória
//
//    // Método para adicionar um usuário
//    public void add(User user) {
//        // Verifica se o usuário já existe antes de adicionar (por email, nome de usuário ou nome)
//        Optional<User> existingUser = users.stream()
//                .filter(u -> u.getEmail().equalsIgnoreCase(user.getEmail()) ||
//                            u.getUserName().equalsIgnoreCase(user.getUserName()) ||
//                            u.getName().equalsIgnoreCase(user.getName()))
//                .findFirst();
//        
//        if (existingUser.isEmpty()) {
//            users.add(user);  // Se o usuário não existir, adiciona o novo
//            System.out.println("Usuário adicionado: " + user.getUserName());
//        } else {
//            System.out.println("Usuário já existe: " + user.getUserName());
//        }
//    }
//
//    // Método para buscar um usuário (por email, nome de usuário ou nome)
//    public Optional<User> findUser(String login) {
//        return users.stream()
//                .filter(u -> u.getEmail().equalsIgnoreCase(login) ||
//                            u.getUserName().equalsIgnoreCase(login) ||
//                            u.getName().equalsIgnoreCase(login))
//                .findFirst();
//    }
//
//    // Método para retornar todos os usuários como um Stream
//    public Stream<User> stream() {
//        return users.stream();
//    }
//
//    // Método para verificar se há usuários cadastrados
//    public boolean hasUsers() {
//        return !users.isEmpty();
//    }
//
//    // Método para listar os usuários (apenas para depuração)
//    public List<User> getUsers() {
//        return users;
//    }
//}
