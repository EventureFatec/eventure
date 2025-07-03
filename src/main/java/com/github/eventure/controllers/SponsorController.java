// here we have the methods. In this case

package com.github.eventure.controllers;

import com.github.eventure.model.Sponsor;
import com.github.eventure.storage.Storage;

public class SponsorController {
    private static int lastGeneratedId = 0;
    private Storage<Sponsor> sponsorStorage;
    public SponsorController() {
        if (sponsorStorage == null) {
            sponsorStorage = new Storage<Sponsor>();
        }
    }

    public Sponsor createSponsor(String name,String bussinessEmail, String cep, long cnpj) {
        
        var sponsor = new Sponsor();

        
        if (name != null) {
            sponsor.setName(name);
        }

        
        EmailController emailController = new EmailController();
        CnpjController cnpjController = new CnpjController();

        
        boolean emailValidator,cnpjValidator;

        emailValidator = emailController.ValidateEmail(bussinessEmail);
        cnpjValidator = cnpjController.isCnpjValid(cnpj);
        if (emailValidator && cnpjValidator) {
            sponsor.setBussinessEmail(bussinessEmail);
            sponsor.setCnpj(cnpj); 
        }
        return sponsor;
    }

        public void createSponsor(Sponsor sponsor) {
            EmailController eController = new EmailController();
            CnpjController cController = new CnpjController();
            boolean isAllCorrect = true;

            if (!eController.ValidateEmail(sponsor.getBussinessEmail()) && !cController.isCnpjValid(sponsor.getCnpj())) {
                isAllCorrect = false;
            } else {
                sponsorStorage.add(sponsor);
            }
    }

        public void deleteSponsor(Sponsor sponsor) {
            sponsorStorage.remove(sponsor);
        }

        public Sponsor findSponsor(int idSponsor) {
            return sponsorStorage.find(sponsor -> sponsor.getIdSponsor() == idSponsor).findFirst().orElse(null);
        }

        public void cloneSponsor(String name,String bussinessEmail, String cep, long cnpj) {
            var sponsorCloned = new Sponsor();
            sponsorCloned.setName(name);
            EmailController emailController = new EmailController(); 
            CnpjController cnpjController = new CnpjController();

            boolean emailIsCorrect = emailController.ValidateEmail(bussinessEmail);
            boolean cnpjIsCorrect = cnpjController.isCnpjValid(cnpj);

            if(emailIsCorrect && cnpjIsCorrect) {
                sponsorCloned.setBussinessEmail(bussinessEmail);
                sponsorCloned.setCnpj(cnpj);
            }
        }
        
       	public static int generateId() {
	    	return lastGeneratedId++;
	}
    }
