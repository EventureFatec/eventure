package com.github.eventure;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.github.eventure.controllers.AddressController;
import com.github.eventure.controllers.EventController;
import com.github.eventure.controllers.UserController;
import com.github.eventure.model.EventClassification;
import com.github.eventure.model.address.Address;
import com.github.eventure.model.address.Cep;
import com.github.eventure.view.windowing.Window;
import com.github.eventure.web.Requests;

public class Main {
    public static void main(String[] args) {
    	var uc = new UserController();
    	uc.createUser("allisson" , "silva" , "Corinthias7787@" , "allisson@gmail.com");
    	uc.createUser("allisson" , "thomas" , "Corinthias7787@" , "allisson600@gmail.com");
    	uc.print();
        Window.run();
    }
}
