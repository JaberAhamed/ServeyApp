package com.example.serveyappfor.models;

public class Serveyinfo {

    String ceck;
    String address;

    public Serveyinfo(String ceck, String address) {
        this.ceck = ceck;
        this.address = address;
    }

    public String getCeck() {
        return ceck;
    }

    public void setCeck(String ceck) {
        this.ceck = ceck;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
