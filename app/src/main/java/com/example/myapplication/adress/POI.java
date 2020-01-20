package com.example.myapplication.adress;

public class POI {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public POI(String name, String adress) {
        this.name = name;
        this.adress = adress;
    }

    private String name;
    private String adress;

}
