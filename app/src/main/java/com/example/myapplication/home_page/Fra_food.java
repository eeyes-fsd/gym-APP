package com.example.myapplication.home_page;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.food_shop.Food;
import com.example.myapplication.food_shop.Global_shop_cart;
import com.example.myapplication.food_shop.Shop_cart_adapter;
import com.example.myapplication.food_shop.Shop_cart_window;
import com.example.myapplication.food_shop.TaoCan;
import com.example.myapplication.food_shop.TaoCanAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fra_food extends Fragment implements View.OnClickListener{
    private TextView textView_csd;
    private TextView textView_cst;
    private TaoCan[] taoCans={new TaoCan(R.drawable.pic_test_1,"套餐一"),new TaoCan(R.drawable.pic_text_2,"套餐二"),new TaoCan(R.drawable.pic_test_3,"套三")
            ,new TaoCan(R.drawable.round_pic,"套餐四")};
//    private List<TaoCan> taoCanList=new ArrayList<>();
    private TaoCanAdapter adapter;
    private PopupWindow popupWindow;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fra_food,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Init_1();//初始化套餐
        Init_2();//初始化套餐的内容
        RecyclerView recyclerView=getView().findViewById(R.id.taocan_recycle);
        GridLayoutManager manager=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(manager);
        adapter=new TaoCanAdapter();
        recyclerView.setAdapter(adapter);
        textView_csd=getView().findViewById(R.id.text_csd);
        textView_csd.setOnClickListener(this);
        textView_cst=getView().findViewById(R.id.text_cst);
        textView_cst.setOnClickListener(this);
        FloatingActionButton floatingActionButton= getView().findViewById(R.id.shop_cart);
        floatingActionButton.setOnClickListener(this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shop_cart:
               // show_shop_cart();
                startActivity(new Intent(getActivity(), Shop_cart_window.class));
                break;
            case R.id.text_csd:
                textView_cst.setTextColor(getResources().getColor(R.color.food_gray));
                textView_csd.setTextColor(getResources().getColor(R.color.food_black));
                break;
            case R.id.text_cst:
                textView_csd.setTextColor(getResources().getColor(R.color.food_gray));
                textView_cst.setTextColor(getResources().getColor(R.color.food_black));
                break;
        }
    }

    private void Init_2(){
        if (Global_shop_cart.taoCanlist.isEmpty()){
            for(int i=0;i<10;i++){
                Random random=new Random();
                int index=random.nextInt(taoCans.length);
                Global_shop_cart.taoCanlist.add(taoCans[index]);
            }
        }
    }
//    private void show_shop_cart(){
//        ExpandableListView expandableListView=getView().findViewById(R.id.shop_cart_elv);
//        Shop_cart_adapter shop_cart_adapter=new Shop_cart_adapter(getContext());
//        expandableListView.setAdapter(shop_cart_adapter);
//        for (int i = 0; i< Global_shop_cart.taoCanlist.size(); i++){
//            expandableListView.expandGroup(i);
//        }
//        View view=LayoutInflater.from(getActivity()).inflate(R.layout.shop_cart,null);
//        popupWindow=new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        View rootview=LayoutInflater.from(getActivity()).inflate(R.layout.fra_food,null);
//        popupWindow.setContentView(view);
//        popupWindow.showAtLocation(rootview, Gravity.BOTTOM,0,0);
//    }
    private void Init_1(){
        taoCans[0].foodList.add(new Food(10,"1猪肉"));
        taoCans[0].foodList.add(new Food(10,"1牛肉"));
        taoCans[1].foodList.add(new Food(5,"2胡萝卜"));
        taoCans[1].foodList.add(new Food(12,"2白菜"));
        taoCans[2].foodList.add(new Food(17,"3猪肉"));
        taoCans[2].foodList.add(new Food(16,"3牛肉"));
        taoCans[3].foodList.add(new Food(13,"4猪肉"));
        taoCans[3].foodList.add(new Food(5,"4牛肉"));
    }

}
