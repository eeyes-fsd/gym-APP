package com.example.myapplication.home_page;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.myapplication.food_shop.Shop_cart_window;
import com.example.myapplication.food_shop.TaoCan;
import com.example.myapplication.food_shop.TaoCanAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fra_food extends Fragment {
    private TaoCan[] taoCans={new TaoCan(R.drawable.pic_test_1,"套餐一"),new TaoCan(R.drawable.pic_text_2,"套餐二"),new TaoCan(R.drawable.pic_test_3,"套三")
            ,new TaoCan(R.drawable.round_pic,"套餐四")};;
    private List<TaoCan> taoCanList=new ArrayList<>();
    private TaoCanAdapter adapter;
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
        adapter=new TaoCanAdapter(taoCanList);
        recyclerView.setAdapter(adapter);
        FloatingActionButton floatingActionButton= getView().findViewById(R.id.shop_cart);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Shop_cart_window.class));
            }
        });
        super.onActivityCreated(savedInstanceState);
    }
    private void Init_2(){
        if (taoCanList.isEmpty()){
            for(int i=0;i<10;i++){
                Random random=new Random();
                int index=random.nextInt(taoCans.length);
                taoCanList.add(taoCans[index]);
            }

        }

    }

    void Init_1(){
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
