package com.example.myapplication.adress;

import android.view.LayoutInflater;
import android.view.View;

public class Adress {
    private int id;
    private String details;
    private String gender;
    private String phone;
    private String name;

    public Adress(String details, String gender, String phone, String name) {
        this.details = details;
        this.gender = gender;
        this.phone = phone;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}



