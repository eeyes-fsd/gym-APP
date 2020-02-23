package com.example.myapplication.food_shop;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class TaoCan {
    public static final String get_new_recommend="/recipes/new?count=10&page=2";
    public static final String get_recipe_detail_pre="/recipes/";
    //中间差一个加一个
    public static final String get_recipe_detail_post="/details";
    public static final String get_already_bought="/recipes/bought";
    public static final String get_all_recipe="/recipes";
    public static final String get_today_recommend="/recipes/today";

    //这个东西是套餐和食谱的结合，包括的属性为
    //id 描述 名字
    private int id;
    private String description;
    private String cover;
    public boolean isAdded;
    private int num;//数量
    public List<Food> foodList=new ArrayList<>();
    private int imageId;//图片
    private String name;//名字

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public TaoCan(){
        this.num=1;
    }

    public void num_add(){
        num=num+1;
    }
    public void num_sub(){
        num=num-1;
    }

    public TaoCan(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
        this.num=1;
        this.isAdded=false;
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

    public int getNum() {
        return num;
    }


    public int getPrice(){
        int sum=0;
        for (int i=0;i<foodList.size();i++){
            sum=sum+foodList.get(i).getPrice()*foodList.get(i).getNum();
        }
        return sum;
    }
}
