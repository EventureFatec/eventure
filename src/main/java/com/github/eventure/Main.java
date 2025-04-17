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
    	EventController e = new EventController();
    	UserController u = new UserController();
    	u.createUser("allisson", "thomas", "Allisson12@", "allisson@gmail.com");
    	Date date = new Date(04/01/2006);
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    	String inputTime = "15:30";
    	LocalTime starHours = LocalTime.parse(inputTime, formatter);
    	System.out.println(starHours);
    	String inputTime2 = "18:30";
    	LocalTime endHours = LocalTime.parse(inputTime2, formatter);
    	System.out.println(endHours);
    	 Address ad = new Address();
    	 var aController = new AddressController();
    	 String cep01 = "12760000";
         String url = Cep.VIACEP_URL + "/" + cep01 + "/json/";
         Cep cep = Requests.get(url, Cep.class);
         if(cep != null)
         {
        	 System.out.println(cep.getLocality());
        	 System.out.println(cep.getState());
         }
    	 String cep02 = "12760000";
         String url01 = Cep.VIACEP_URL + "/" + cep02 + "/json/";
         Cep cept = Requests.get(url01, Cep.class);
         if(cept != null)
         {
        	 System.out.println(cept.getLocality());
        	 System.out.println(cept.getState());
         }
    	e.createEvent(0, "feira de ciencias", "uma feira de ciencias que vai chocar o mundo","feira de ciencias são paulo", EventClassification.EDUCATIONAL, date, starHours, endHours, cep);
    	e.createEvent(0, "show do veigh", "show para os amantes do trap","show em são paulo", EventClassification.PARTIES_AND_SHOWS, date, starHours, endHours, cept);
    	e.print();
        Window.run();
    }
}
