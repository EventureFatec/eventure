package com.github.eventure.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.github.eventure.encryption.Encryption;
import com.github.eventure.model.User;
import com.github.eventure.storage.Storage;

public class UserController {
	private Storage<User> userStorage;
	private static int lastGeneratedId = 0;
	public UserController()
	{
		if(userStorage == null)
		{
			userStorage = new Storage<User>();
		}
	}
    public User createUser(String firstName, String lastName, String password , String email) {
        // Instantiate the user
        var u = new User();
        u.setName(firstName + " " + lastName);
        EmailController emailTest = new EmailController();
        boolean verdade_ou_nao = emailTest.ValidarEmail(email);
        System.out.println("email é :"+verdade_ou_nao);
        if(verdade_ou_nao)
        {
        u.setEmail(emailTest.getEmail());
        }
        // Create the password hash
        var salt = Encryption.generateSalt();
        var hash = Encryption.generateHash(password, salt);
        u.setPasswordSalt(salt);
        u.setPasswordHash(hash);
        
        if(u.getName()  != null && u.getPasswordHash() != null && u.getName() != null)
        {
        	int id = UserController.generateId();
        	u.setUserId(id);
        }
        userStorage.add(u);
        
        // Return the user
        return u;
    }
    
    public void deleteUser(User u)
    {
    	userStorage.remove(u);
    }
    
    public List<String> findUser(String lastName)
    {
    	List<String> emails = userStorage.find(user -> user.getName().equals("Chrystian Mendes Franklin")).map(User::getEmail).collect(Collectors.toList());
    	return emails;
    }

    public void validarCpf(String cpf) {
        int[] cpfArray = ConvertCpfToArray(cpf);
        if (IsCpf(1, cpfArray) == true) {
            System.out.println("é um cpf valido");
        } else {
            System.out.println("cpf nao e valido");
        }
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
    public static int generateId() 
        { 
    	return lastGeneratedId++; 
    	}
}
