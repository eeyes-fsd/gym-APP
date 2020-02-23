package com.example.myapplication.food_shop;

public class RecipeItem {
    private int id;
    private String name;
    private String http_pic;
    private Meal breakfast;
    private Meal lunch;
    private Meal dinner;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHttp_pic() {
        return http_pic;
    }
    public void setHttp_pic(String http_pic) {
        this.http_pic = http_pic;
    }
    public Meal getBreakfast() {
        return breakfast;
    }
    public void setBreakfast(Meal breakfast) {
        this.breakfast = breakfast;
    }
    public Meal getLunch() {
        return lunch;
    }

    public void setLunch(Meal lunch) {
        this.lunch = lunch;
    }

    public Meal getDinner() {
        return dinner;
    }

    public void setDinner(Meal dinner) {
        this.dinner = dinner;
    }
}
