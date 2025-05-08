package com.github.eventure.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import java.util.Arrays;

import com.github.eventure.encryption.Encryption;
import com.github.eventure.model.Event;
import com.github.eventure.model.User;
import com.github.eventure.model.address.Cep;
import com.github.eventure.model.passwords.Password;
import com.github.eventure.storage.Storage;

public class UserController {
    private Storage<User> userStorage;
    private static int lastGeneratedId = 0;
    private static UserController instance;
    private UserController() {
            userStorage = new Storage<User>(); 
    }
    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }
    public User createUser(String firstName, String username, String password, String email) {
        // Instantiate the user
        var u = new User();
        u.setName(firstName);
        EmailController emailTest = new EmailController();
        boolean verdade_ou_nao = emailTest.ValidateEmail(email);
        u.setEmail(email);
        if (verdade_ou_nao && !emailRegister(email)) {
            u.setEmail(emailTest.getEmail());
        }else {
        	if(!verdade_ou_nao) {
        	JOptionPane.showMessageDialog(null, "Email digitado invalido.");}
        	else {
        		JOptionPane.showMessageDialog(null, "Email ja cadastrado digite outro ou faça o login.");}
        	
        	}
    
        if(!usernameRegister(username)) {
        u.setUsername(username);
        }else {
        	JOptionPane.showMessageDialog(null, "Usuario ja cadastrado.");
        }
        // Create the password hash
        if(validarSenha(password))
        {
            var salt = Encryption.generateSalt();
            var hash = Encryption.generateHash(password, salt);
            var passwordClass = new Password();
            passwordClass.setPasswordSalt(salt);
            passwordClass.setPasswordHash(hash);
            u.setPassword(passwordClass);
        }else {
        	JOptionPane.showMessageDialog(null, "Senha invalida preencha corretamente");
        }
        
        if (!u.getName().isEmpty() && u.getPassword() != null && !u.getEmail().isEmpty() && !u.getUsername().isEmpty()) {
            int id = UserController.generateId();
            u.setUserId(id);
            userStorage.add(u);
            JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso");
        }
        // Return the user
        return u;
    }

    public User createUser(String firstName, String lastName, String password, String email, String cpf) {
        // Instantiate the user
        var u = new User();
        u.setName(firstName + " " + lastName);
        EmailController emailTest = new EmailController();
        boolean verdade_ou_nao = emailTest.ValidateEmail(email);

        if (verdade_ou_nao) {
            u.setEmail(emailTest.getEmail());
        }
        boolean cpfValid = validateCpf(cpf);

        if (cpfValid) {
            u.setCpf(cpf);
            u.setOrganazador(true);
        }
        // Create the password hash
        var salt = Encryption.generateSalt();
        var hash = Encryption.generateHash(password, salt);
        var passwordClass = new Password();
        passwordClass.setPasswordSalt(salt);
        passwordClass.setPasswordHash(hash);
        u.setPassword(passwordClass);
        if (u.getName() != null && u.getPassword() != null && u.getName() != null && cpfValid && verdade_ou_nao) {
            int id = UserController.generateId();
            u.setUserId(id);
            userStorage.add(u);
        }

        // Return the user
        return u;
    }

    public void createUser(User u) {
        EmailController eController = new EmailController();
        boolean isAllCorrect = true;

        if (!eController.ValidateEmail(u.getEmail()))
            isAllCorrect = false;
        if (!validateCpf(u.getCpf()))
            isAllCorrect = false;

        if (isAllCorrect) {
            userStorage.add(u);
        }
    }

    public boolean emailRegister(String email) {
        return userStorage.stream().anyMatch(user -> user.getEmail().equals(email));
    }
    public boolean usernameRegister(String username) {
        return userStorage.stream().anyMatch(user -> user.getUsername().equals(username));
    } 
	public boolean validarSenha(String senha) {
        if (senha == null || senha.length() < 8) {
            return false;
        }

        boolean temMaiuscula = senha.matches(".*[A-Z].*");
        boolean temNumero = senha.matches(".*\\d.*");
        boolean temEspecial = senha.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");

        return temMaiuscula && temNumero && temEspecial;
    }

    public void cloneUser(String firstName, String lastName, String password, String email, String cpf, int id) {
        // Instantiate the user
        var userCloned = new User();
        userCloned.setName(firstName + " " + lastName);
        EmailController emailTest = new EmailController();
        boolean verdade_ou_nao = emailTest.ValidateEmail(email);

        if (verdade_ou_nao) {
            userCloned.setEmail(emailTest.getEmail());
        }
        boolean cpfValid = validateCpf(cpf);

        if (cpfValid) {
            userCloned.setCpf(cpf);
        }
        // Create the password hash
        var salt = Encryption.generateSalt();
        var hash = Encryption.generateHash(password, salt);
        var passwordClass = new Password();
        passwordClass.setPasswordSalt(salt);
        passwordClass.setPasswordHash(hash);
        userCloned.setPassword(passwordClass);
        // arrumar esse metodo pois não posso gerar uma salt nova o que vai mudar a
        // senha totalmente

        if (userCloned.getName() != null && userCloned.getPassword() != null && userCloned.getName() != null
                && cpfValid) {

            userCloned.setUserId(id);

        }

        applyChanges(id, userCloned);

    }

    public void deleteUser(User u) {
        userStorage.remove(u);
    }
    public User findUserByEmail(String email) {
        return userStorage.find(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }
    public User findUserByUsername(String username) {
        return userStorage.find(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }
    public User findUserById(int id) {
        return userStorage.find(user -> user.getUserId() == id).findFirst().orElse(null);
    }

    public boolean isUserRegistered(String username) {
        return userStorage.stream().anyMatch(user -> user.getName().equals(username));
    }

    public boolean isOrganizador(int id) {
        User u = findUserById(id);
        if (u.isOrganazador() == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean login(String emailOrusername, String password) {
    // Procura o usuário no storage
    	print();
    User user = findUserByEmail(emailOrusername);
    System.out.println("usuario = "+user);
    if(user == null)
    {
    	user = findUserByUsername(emailOrusername);
    }  
    if (user != null) {
        // Verifica a senha
        Password userPassword = user.getPassword();
        System.out.println(user.getPassword().getPasswordSalt().toString());
        byte[] salt = user.getPassword().getPasswordSalt();
        byte[] hash = Encryption.generateHash(password, salt);
        boolean loginValido = Encryption.checkHashes(hash, user.getPassword().getPasswordHash());
        // Se o hash gerado com a senha inserida for igual ao hash armazenado
//        return Arrays.equals(hash, userPassword.getPasswordHash());
        if(loginValido) {
        	System.out.println("Hash gerado: " + bytesToHex(hash));
        	System.out.println("Hash salvo:   " + bytesToHex(user.getPassword().getPasswordHash()));
        	System.out.println("logado com sucesso");
        	return true;
        }else {
        	return false;
        }
    }

    // Caso o usuário não seja encontrado
    return false;
}

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    public void print() {
        for (User u : userStorage) {
            System.out.println("id = "+u.getUserId());
            System.out.println("nome = "+u.getName());
            System.out.println("email = "+u.getEmail());
//            System.out.println(u.getCpf());
            System.out.println("hash = "+u.getPassword().getPasswordHash().toString());
            System.out.println("salt = "+u.getPassword().getPasswordSalt().toString());
        }

    }

    public boolean validateCpf(String cpf) {

        boolean valid = false;
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            System.out.println("CPF inválido");
            return false;
        }

        int[] cpfArray = ConvertCpfToArray(cpf);

        if (IsCpf(1, cpfArray) == true) {
            System.out.println("é um cpf valido");
            valid = true;
        } else {
            System.out.println("Cpf nao e valido");
        }
        return valid;
    }

    private int[] ConvertCpfToArray(String cpf) {
        int[] cpfArray = new int[11];
        for (int i = 0; i <= 10; i++) {
            cpfArray[i] = Integer.parseInt(String.valueOf(cpf.charAt(i)));
        }
        return cpfArray;
    }

    private boolean IsCpf(int posicaoCodigo, int[] cpfArray) {
        int j = 0;

        if (posicaoCodigo == 1) {
            j = 10;
        } else {
            j = 11;
        }
        int indexParameter = 7 + posicaoCodigo;

        int resultado = 0;
        for (int i = 0; i <= indexParameter; i++) {
            resultado += j * cpfArray[i];
            j--;
        }
        int restoDiv = resultado % 11;
        if (restoDiv < 2) {
            if (cpfArray[indexParameter + 1] == 0) {
                if (posicaoCodigo == 1) {
                    return IsCpf(2, cpfArray);
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } else {
            int dif = 11 - restoDiv;
            if (cpfArray[indexParameter + 1] == dif) {
                if (posicaoCodigo == 1) {
                    return IsCpf(2, cpfArray);
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    public void applyChanges(int id, User userCloned) {
        var storedUser = findUserById(id);

        if (!(storedUser.getName() == userCloned.getName()) && userCloned.getName() != null) {
            storedUser.setName(userCloned.getName());
        }
        if (!(storedUser.getEmail() == userCloned.getEmail()) && userCloned.getEmail() != null) {
            storedUser.setEmail(userCloned.getEmail());
        }
        if (!(storedUser.getCpf() == userCloned.getCpf()) && userCloned.getCpf() != null) {
            storedUser.setCpf(userCloned.getCpf());
        }
        if (userCloned.getPassword() != null && !(storedUser.getPassword().equals(userCloned.getPassword()))) {
            storedUser.setPassword(userCloned.getPassword());
        }

    }

    public static int generateId() {
        return lastGeneratedId++;
    }
}