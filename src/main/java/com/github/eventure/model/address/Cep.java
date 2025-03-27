package com.github.eventure.model.address;

import com.google.gson.annotations.SerializedName;

public class Cep {
    public static final String VIACEP_URL = "https://viacep.com.br/ws/";
    private String cep;
    private @SerializedName("logradouro") String street;
    private @SerializedName("complemento") String complement;
    private @SerializedName("unidade") String unit;
    private @SerializedName("bairro") String neighborhood;
    private @SerializedName("localidade") String locality;
    private @SerializedName("uf") String stateShorthand;
    private @SerializedName("estado") String state;
    private @SerializedName("regiao") String region;

    public String getCep() {
        return cep;
    }

    public String getStreet() {
        return street;
    }

    public String getComplement() {
        return complement;
    }

    public String getUnit() {
        return unit;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getLocality() {
        return locality;
    }

    public String getStateShorthand() {
        return stateShorthand;
    }

    public String getState() {
        return state;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return street + ", " + neighborhood + ", " + state;
    }
}
