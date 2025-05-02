package com.github.eventure.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.github.eventure.encryption.Encryption;
import com.github.eventure.model.Event;
import com.github.eventure.model.User;
import com.github.eventure.model.address.Cep;
import com.github.eventure.model.passwords.Password;
import com.github.eventure.storage.Storage;

public class UserController {
	private Storage<User> userStorage;
	private static int lastGeneratedId = 0;

	public UserController() {
		if (userStorage == null) {
			userStorage = new Storage<User>();
		}
	}

	public User createUser(String firstName, String lastName, String password, String email) {
		// Instantiate the user
		var u = new User();
		u.setName(firstName + " " + lastName);
		EmailController emailTest = new EmailController();
		boolean verdade_ou_nao = emailTest.ValidateEmail(email);

		if (verdade_ou_nao && !emailRegister(email)) {
			u.setEmail(emailTest.getEmail());
		}
		// Create the password hash
		var salt = Encryption.generateSalt();
		var hash = Encryption.generateHash(password, salt);
		var passwordClass = new Password();
        passwordClass.setPasswordSalt(salt);
        passwordClass.setPasswordHash(hash);
        u.setPassword(passwordClass);
		if (u.getName() != null && u.getPassword() != null && u.getEmail() != null) {
			int id = UserController.generateId();
			u.setUserId(id);
			userStorage.add(u);
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
    public Boolean emailRegister(String email) {
    	var u = userStorage.find(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    	if(u != null)
    	{
    		return true;
    	}else {
    		return false;
    	}
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
       // arrumar esse metodo pois não posso gerar uma salt nova o que vai mudar a senha totalmente 

		if (userCloned.getName() != null && userCloned.getPassword() != null && userCloned.getName() != null
				&& cpfValid) {

			userCloned.setUserId(id);

		}

		applyChanges(id, userCloned);

	}

	public void deleteUser(User u) {
		userStorage.remove(u);
	}

	public User findUserById(int id) {
		return userStorage.find(user -> user.getUserId() == id).findFirst().orElse(null);
	}

	public boolean isOrganizador(int id) {
		User u = findUserById(id);
		if (u.isOrganazador() == true) {
			return true;
		} else {
			return false;
		}
	}
	public void print() {
		for (User u : userStorage) {
			System.out.println(u.getUserId());
			System.out.println(u.getName());
			System.out.println(u.getEmail());
			System.out.println(u.getCpf());
			System.out.println(u.getPassword().getPasswordHash().toString());
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
