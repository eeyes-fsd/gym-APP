package com.example.myapplication.order;

import java.util.Date;
import java.util.List;

public class Order {
    private String name;
    private List<Order_Item> list;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order_Item> getList() {
        return list;
    }

    public void setList(List<Order_Item> list) {
        this.list = list;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private int price;
}
