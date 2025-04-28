package com.github.eventure.model;

public class Address {
	private String localName;
	private String adress;
	private String street;
	private String state;
	private String district;
	private String city;
	private String cep;
	private int idUser;

	public Address(String localName, String adress, String street, String state, String district, String city,
			String cep, int idUser) {

		this.localName = localName;
		this.adress = adress;
		this.street = street;
		this.state = state;
		this.district = district;
		this.city = city;
		this.cep = cep;
		this.idUser = idUser;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getLocalName() {
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

}
