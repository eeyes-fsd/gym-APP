package com.example.myapplication.adress;

import android.view.LayoutInflater;
import android.view.View;

public class Adress {
    private int ID;
    public Adress(){

    }
    public Adress(String adress, String sex, String tel, String name) {
        this.adress = adress;
        this.sex = sex;
        this.tel = tel;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String adress;
    private String sex;
    private String tel;
    private String name;
}



