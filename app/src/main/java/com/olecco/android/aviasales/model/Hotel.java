package com.olecco.android.aviasales.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hotel {

    @SerializedName("state")
    @Expose
    private Object state;

    @SerializedName("stars")
    @Expose
    private int stars;

    @SerializedName("locationFullName")
    @Expose
    private String locationFullName;

    @SerializedName("latinLocationFullName")
    @Expose
    private String latinLocationFullName;

    @SerializedName("hotelFullName")
    @Expose
    private String hotelFullName;

    @SerializedName("location")
    @Expose
    private Coordinates location;

    @SerializedName("timezone")
    @Expose
    private Object timezone;

    @SerializedName("timezonesec")
    @Expose
    private int timezonesec;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("locationId")
    @Expose
    private int locationId;

    @SerializedName("photoCount")
    @Expose
    private int photoCount;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("latinCity")
    @Expose
    private String latinCity;

    @SerializedName("latinClar")
    @Expose
    private String latinClar;

    @SerializedName("latinCountry")
    @Expose
    private String latinCountry;

    @SerializedName("locationHotelsCount")
    @Expose
    private int locationHotelsCount;

    @SerializedName("rating")
    @Expose
    private int rating;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("distance")
    @Expose
    private double distance;

    @SerializedName("_score")
    @Expose
    private int score;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("latinName")
    @Expose
    private String latinName;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("photos")
    @Expose
    private List<Long> photos = null;

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getLocationFullName() {
        return locationFullName;
    }

    public void setLocationFullName(String locationFullName) {
        this.locationFullName = locationFullName;
    }

    public String getLatinLocationFullName() {
        return latinLocationFullName;
    }

    public void setLatinLocationFullName(String latinLocationFullName) {
        this.latinLocationFullName = latinLocationFullName;
    }

    public String getHotelFullName() {
        return hotelFullName;
    }

    public void setHotelFullName(String hotelFullName) {
        this.hotelFullName = hotelFullName;
    }

    public Coordinates getCoordinates() {
        return location;
    }

    public void setCoordinates(Coordinates location) {
        this.location = location;
    }

    public Object getTimezone() {
        return timezone;
    }

    public void setTimezone(Object timezone) {
        this.timezone = timezone;
    }

    public int getTimezonesec() {
        return timezonesec;
    }

    public void setTimezonesec(int timezonesec) {
        this.timezonesec = timezonesec;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(int photoCount) {
        this.photoCount = photoCount;
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

    public String getLatinClar() {
        return latinClar;
    }

    public void setLatinClar(String latinClar) {
        this.latinClar = latinClar;
    }

    public String getLatinCountry() {
        return latinCountry;
    }

    public void setLatinCountry(String latinCountry) {
        this.latinCountry = latinCountry;
    }

    public int getLocationHotelsCount() {
        return locationHotelsCount;
    }

    public void setLocationHotelsCount(int locationHotelsCount) {
        this.locationHotelsCount = locationHotelsCount;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Long> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Long> photos) {
        this.photos = photos;
    }

}
