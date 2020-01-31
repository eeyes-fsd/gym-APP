package com.example.myapplication.food_shop;

public class Food {
    public Food(int price, String name) {
        num=1;
        this.price = price;
        this.name = name;
    }
    public Food(){
        num=0;
        price=0;
        name="未命名";
    }

    public int getPrice() {
        return price;
    }
    public int getcost(){
        return price*num;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    private int num;
    private int price;
    private String name;
    public void add_num(){
        this.num=num+1;
    }
}
