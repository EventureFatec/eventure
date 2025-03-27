package com.github.eventure.controllers;

import com.github.eventure.model.address.Cep;
import com.github.eventure.web.Requests;

public class AddressController {
    public Cep getFromViacep(String cep) {
        if (cep.length() != 8) {
            return null;
        }

        return Requests.get(Cep.VIACEP_URL + cep + "/json", Cep.class);
    }
}
