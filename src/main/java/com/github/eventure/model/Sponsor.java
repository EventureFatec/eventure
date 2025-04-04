// this model indicates what properties have. In this case, it has a name, a bussiness email,
// an address, a CNPJ and a unique id to differentiate each sponsor with a easier identifier

package com.github.eventure.model;

public class Sponsor {
    private String name;
    private String bussinessEmail;
    private Address address;
    private int cnpj;
    private int idSponsor;
//  private Event event; Future plans if we have time

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBussinessEmail() {
        return bussinessEmail;
    }

    public void setBussinessEmail(String bussinessEmail) {
        this.bussinessEmail = bussinessEmail;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getCnpj() {
        return cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    public int getIdSponsor() {
        return idSponsor;
    }

    public void setIdSponsor(int idSponsor) {
        this.idSponsor = idSponsor;
    }
}
