package com.github.eventure.model;

public class Adress {
    private String localName;
    private String adress;
    private String street;
    private String state;
    private String district;
    private String city;
    private int cep;
    
	public void setLocalName(String localName)
	{
		this.localName = localName;
	}
	public String getLocalName()
	{
		return this.localName;
	}
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}
}
