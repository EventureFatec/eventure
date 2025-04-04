// here we have the methods. In this case

package com.github.eventure.controllers;

import com.github.eventure.model.Sponsor;
import com.github.eventure.storage.Storage;

public class SponsorController {
    private Storage<Sponsor> sponsorStorage;
    public SponsorController() {
        if (sponsorStorage == null) {
            sponsorStorage = new Storage<Sponsor>();
        } // always execute first to ensure that it's instanced
    }

    private void createSponsor(String name,String bussinessEmail, String cep, String cnpj) {
        // create a sponsor var
        var sponsor = new Sponsor();

        // sets the name directly if it's not empty
        if (name != null) {
            sponsor.setName(name);
        }

        // creates the controller object for validating the email and CNPJ
        EmailController emailController = new EmailController();
        CnpjController cnpjController = new CnpjController();

        // creates the respectives boolean
        boolean emailValidator,cnpjValidator;

        emailValidator = emailController.ValidateEmail(bussinessEmail);
        cnpjValidator = cnpjController.isCnpjValid(cnpj);
        if (emailValidator && cnpjValidator) { // if the email and cnpj is valid:
            sponsor.setBussinessEmail(bussinessEmail); // sets the email in the Storage
            sponsor.setCnpj(cnpj); // sets the CNPJ in the Storage
        }
    }
}
