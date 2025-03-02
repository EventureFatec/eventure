package com.github.eventure.controllers;

import com.github.eventure.encryption.Encryption;
import com.github.eventure.model.User;

public class UserController {
    public User createUser(String firstName, String lastName, String password) {
        // Instantiate the user
        var u = new User();
        u.setName(firstName + " " + lastName);

        // Create the password hash
        var salt = Encryption.generateSalt();
        var hash = Encryption.generateHash(password, salt);
        u.setPasswordSalt(salt);
        u.setPasswordHash(hash);

        // Return the user
        return u;
    }
    public void validarCpf(String cpf) {
        int [] cpfArray = ConvertCpfToArray(cpf);
        if(IsCpf(1 , cpfArray) == true)
        {
            System.out.println("é um cpf valido");
        }else
        {
            System.out.println("cpf nao e valido");
        }
       }
       private int [] ConvertCpfToArray(String cpf)
       {
           int [] cpfArray = new int [11];
           for(int i = 0; i <= 10; i++)
           {
               cpfArray[i] = Integer.parseInt(String.valueOf(cpf.charAt(i)));

           }
           return cpfArray;
       }
       private boolean IsCpf(int posicaoCodigo , int [] cpfArray) {
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
                       return IsCpf(2,cpfArray);
                   } else {
                       return true;
                   }
               } else {
                   return false;
               }
           }
       }
}
