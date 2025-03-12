package com.github.eventure.controllers;

import com.github.eventure.model.Address;
import com.github.eventure.storage.Storage;

public class AddressController {
	
	private Storage<Address> addressStorage;
	private Address address;
	public AddressController()
	{
		if(addressStorage == null)
		{
			addressStorage = new Storage<Address>();	
		}
	}
	public void createAddres(Address address)
	{
		 this.address = address;
		 addressStorage.add(address);
	}
	
	public void imprimir ()
	{
		for(Address a : addressStorage)
		{
			System.out.println("nome local" + a.getLocalName());
			System.out.println("cidade : " + a.getCity());
			System.out.println("estado : " + a.getState());
			System.out.println("bairro : " + a.getDistrict());
			System.out.println("rua :" + a.getStreet());
			System.out.println("cep :" + a.getCep());
			System.out.println("endereco :" + a.getAdress());	
			System.out.println("-------------------------");
		}
	}

}
