package com.olecco.android.aviasales.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class City {

    @SerializedName("countryCode")
    @Expose
    private String countryCode;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("latinFullName")
    @Expose
    private String latinFullName;

    @SerializedName("fullname")
    @Expose
    private String fullName;

    @SerializedName("clar")
    @Expose
    private String clar;

    @SerializedName("latinClar")
    @Expose
    private String latinClar;

    @SerializedName("location")
    @Expose
    private Coordinates location;

    @SerializedName("hotelsCount")
    @Expose
    private int hotelsCount;

    @SerializedName("iata")
    @Expose
    private List<String> iata = null;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("latinCity")
    @Expose
    private String latinCity;

    @SerializedName("timezone")
    @Expose
    private String timezone;

    @SerializedName("timezonesec")
    @Expose
    private int timezonesec;

    @SerializedName("latinCountry")
    @Expose
    private String latinCountry;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("countryId")
    @Expose
    private int countryId;

    @SerializedName("_score")
    @Expose
    private int score;

    @SerializedName("state")
    @Expose
    private Object state;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLatinFullName() {
        return latinFullName;
    }

    public void setLatinFullName(String latinFullName) {
        this.latinFullName = latinFullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getClar() {
        return clar;
    }

    public void setClar(String clar) {
        this.clar = clar;
    }

    public String getLatinClar() {
        return latinClar;
    }

    public void setLatinClar(String latinClar) {
        this.latinClar = latinClar;
    }

    public Coordinates getCoordinates() {
        return location;
    }

    public void setCoordinates(Coordinates location) {
        this.location = location;
    }

    public int getHotelsCount() {
        return hotelsCount;
    }

    public void setHotelsCount(int hotelsCount) {
        this.hotelsCount = hotelsCount;
    }

    public List<String> getIata() {
        return iata;
    }

    public void setIata(List<String> iata) {
        this.iata = iata;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLatinCity() {
        return latinCity;
    }

    public void setLatinCity(String latinCity) {
        this.latinCity = latinCity;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getTimezonesec() {
        return timezonesec;
    }

    public void setTimezonesec(int timezonesec) {
        this.timezonesec = timezonesec;
    }

    public String getLatinCountry() {
        return latinCountry;
    }

    public void setLatinCountry(String latinCountry) {
        this.latinCountry = latinCountry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

}
