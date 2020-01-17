package com.example.myapplication.food_shop;

import java.util.ArrayList;
import java.util.List;

public class TaoCan {
    public List<Food> foodList=new ArrayList<>();
    public TaoCan(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
        num=1;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int imageId;
    private String name;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    private int num;
    public int getPrice(){
        int sum=0;
        for (int i=0;i<foodList.size();i++){
            sum=sum+foodList.get(i).getPrice();
        }
        return sum;
    }
    public void add_food(Food food){
        foodList.add(food);
    }
}
