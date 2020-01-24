package com.example.myapplication.food_shop;

import java.util.ArrayList;
import java.util.List;

public class Global_shop_cart {
    public static List<List<Food>> foodlist=new ArrayList<>();//所有食物
    public static List<TaoCan> taoCanlist=new ArrayList<>();//所有套餐
    public static List<TaoCan> taoCan_search_list=new ArrayList<>();//展示的套餐
    public static List<List<Food>> food_search_list=new ArrayList<>();//展示的食物

    public static void copy_food(){
        food_search_list.clear();
        food_search_list.addAll(foodlist);
    }
    public static void copy_taocan(){
        taoCan_search_list.clear();
        taoCan_search_list.addAll(taoCanlist);
    }
    private static void clear__all(){
        //把所有套餐的状态改为未未添加
        for(int i=0;i<taoCanlist.size();i++){
            taoCanlist.get(i).isAdded=false;
        }
    }
    private static void init(){
        clear__all();
        taoCan_search_list.clear();
        food_search_list.clear();
    }

    public static void search(String s){
        //s是食谱名，或者食材名
        // 先搜索套餐名
        init();
        for (int i=0;i<taoCanlist.size();i++){
            if (s.equals(taoCanlist.get(i).getName())){
                taoCanlist.get(i).isAdded=true;
                taoCan_search_list.add(taoCanlist.get(i));
                food_search_list.add(foodlist.get(i));//达成对应关系
            }
        }
        //搜索食品名
        for (int i=0;i<taoCanlist.size();i++){
            if (!taoCanlist.get(i).isAdded){//当该食谱未添加到里面时
                for (int j=0;j<foodlist.get(i).size();j++){
                    if (s.equals(foodlist.get(i).get(j).getName())){
                        taoCanlist.get(i).isAdded=true;
                        taoCan_search_list.add(taoCanlist.get(i));
                        food_search_list.add(foodlist.get(i));//达成对应关系
                        break;
                    }
                }
            }
        }
    }
}
