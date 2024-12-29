package com.example.myfragmentandnavigators;

public class Airport {
    private  String name;
    private String city;
    private String lat;
    private String lon;

    public Airport(String name, String city, String lat, String lon) {
        this.name = name;
        this.city = city;

        this.lat = lat;
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }



    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "\nName= " + name +
                "City= " + city +
                "Latitude= " + lat +
                "\nLongitude= " + lon ;
    }
}
