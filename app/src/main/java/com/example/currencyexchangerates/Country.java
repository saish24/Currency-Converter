package com.example.currencyexchangerates;

public class Country {
    private int flagID;
    private String  countryName;

    public int getFlagID() {
        return flagID;
    }

    public String getCountryName() {
        return countryName;
    }

    public Country(int flagID, String countryName) {
        this.flagID = flagID;
        this.countryName = countryName;
    }
}
