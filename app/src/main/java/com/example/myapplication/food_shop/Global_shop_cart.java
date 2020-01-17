package com.example.myapplication.food_shop;

import java.util.ArrayList;
import java.util.List;

public class Global_shop_cart {
    public static List<List<Food>> foodlist=new ArrayList<>();
    public static List<TaoCan> taoCanlist=new ArrayList<>();
    public static void clear_taocan_list(){
        taoCanlist.clear();
    }
    public static void clear_food_list(){
        foodlist.clear();
    }
}
