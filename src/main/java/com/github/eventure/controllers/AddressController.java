package com.github.eventure.controllers;

import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import com.github.eventure.model.address.Address;
import com.github.eventure.model.address.Cep;
import com.github.eventure.storage.Storage;
import com.github.eventure.web.Requests;

public class AddressController {
    private Storage<Address> addresses;

    private List<Address> findAddressByStreet(String street) {
        return addresses.find(address -> address.getStreet().equals(street)).toList();
    }

    public Cep getFromViacep(String cep) {
        if (cep.length() != 8) {
            return null;
        }

        return Requests.get(Cep.VIACEP_URL + "/" + cep + "/json", Cep.class);
    }

    public ArrayList<Cep> searchAddressOnViacep(String uf, String city, String street) {
        if (uf.length() < 2 || city.length() < 3 || street.length() < 3) {
            return null;
        }

        Type cepListType = new TypeToken<List<Cep>>(){}.getType();

        return Requests.get(String.format("%s/%s/%s/%s/json/", Cep.VIACEP_URL, uf, city.replace(" ", "+"), street.replace(" ", "+")), cepListType);
    }
}
