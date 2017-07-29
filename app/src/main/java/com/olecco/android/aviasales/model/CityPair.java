package com.olecco.android.aviasales.model;

public class CityPair {

    private final City cityFrom;
    private final City cityTo;

    public CityPair(City cityFrom, City cityTo) {
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
    }

    public City getCityFrom() {
        return cityFrom;
    }

    public City getCityTo() {
        return cityTo;
    }

}
