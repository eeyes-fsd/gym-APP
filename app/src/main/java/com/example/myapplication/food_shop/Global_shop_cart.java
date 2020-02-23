package com.example.myapplication.food_shop;

import java.util.ArrayList;
import java.util.List;

public class Global_shop_cart {

//    需要的食品列表包括
//    1.所有套餐和所有食物（套餐对应的食物）需要用到的地方是食堂， 加一个全局变量为全部的食谱
//    2.以购买的套餐和食谱，分别用于结算购物车的货物和显示已购买食谱
//    3.


    public static List<List<Food>> foodlist=new ArrayList<>();//所有食物 食堂
    public static List<TaoCan> taoCanlist=new ArrayList<>();//所有套餐 食堂
    public static List<TaoCan> taoCanList_recipe=new ArrayList<>();//配餐
    public static List<List<Food>> foodlist_recipe=new ArrayList<>();//配餐
    public static List<TaoCan> taoCan_search_list=new ArrayList<>();//展示的套餐
    public static List<List<Food>> food_search_list=new ArrayList<>();//展示的食物
    public static List<TaoCan> taoCan_bought_list=new ArrayList<>();//展示的套餐
    public static List<List<Food>> food_bought_list=new ArrayList<>();//展示的食物
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
        taoCan_search_list.clear();
        food_search_list.clear();
    }
    public static void search(String s){
        //s是食谱名，或者食材名
        // 先搜索套餐名
        clear__all();
        if (s.equals("")){//这个说明输入为空，返回所有的
            taoCan_bought_list.addAll(taoCanlist);
            food_search_list.addAll(foodlist);
            return;
        }

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
    public static  void bought_add_food(TaoCan taoCan,Food food){
        //先判断是否food是否在里面,如果是，则数量+1，

    }

    public static void bought_add(TaoCan taoCan){
        int i=Is_repeated(taoCan);
        if (i==-1){//不重复
            taoCan_bought_list.add(taoCan);
            food_bought_list.add(taoCan.foodList);
        }else {
            taoCan_bought_list.get(i).num_add();
        }
    }
    public static void bought_add(int position){
        taoCan_bought_list.get(position).num_add();
    }
    public static void bought_sub(int position){
        taoCan_bought_list.get(position).num_sub();
        if (taoCan_bought_list.get(position).getNum()==0){
            taoCan_bought_list.remove(position);
            food_bought_list.remove(position);
        }
    }
    public static int Is_repeated(TaoCan taoCan){
        for (int i=0;i<taoCan_bought_list.size();i++){//搜索购买列表
            if (taoCan_bought_list.get(i).getName().equals(taoCan.getName())){
                return i;
            }
        }
        return -1;
    }
    public static int get_bought_price(){
        int price=0;
        for (TaoCan taoCan:taoCan_bought_list){
            price=price+taoCan.getPrice()*taoCan.getNum();
        }
        return  price;
    }
    public static void bought_food_add(int group_position,int child_position){
        food_bought_list.get(group_position).get(child_position).add_num();
    }
    public static void bought_food_sub(int group_position,int child_position){
        int num=food_bought_list.get(group_position).get(child_position).getNum();
        if (num==1){
            food_bought_list.get(group_position).remove(child_position);
            return;
        }
        food_bought_list.get(group_position).get(child_position).setNum(num-1);
    }

}
