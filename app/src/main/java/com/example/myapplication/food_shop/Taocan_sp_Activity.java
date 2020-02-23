package com.example.myapplication.food_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Constant;
import com.example.myapplication.R;
import com.example.myapplication.main_page.BaseActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;


public class Taocan_sp_Activity extends BaseActivity implements View.OnClickListener {
    private TaoCan taoCan;
    private Button button1;
    private Button button2;
    private Button button3;
    private PopupWindow popupWindow;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private int position;
    private static         List<String> list=new ArrayList<>();
    private TaocanSpRawTextAdapter taocanSpRawTextAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taocan_sp_);
        Intent intent=getIntent();
        position =intent.getIntExtra("position",-1);
        if (position!=-1){
            taoCan=Global_shop_cart.taoCanlist.get(position);
        }
        String name=taoCan.getName();
        int imageid=taoCan.getImageId();
        //Toolbar toolbar=findViewById(R.id.toolbar_sp);
        ImageView taocanImageView=findViewById(R.id.taocan_sp_pic);
        TextView taocanContentText=findViewById(R.id.taocan_content_text);
        button1=findViewById(R.id.taocan_sp_btn_food);
        button2=findViewById(R.id.taocan_sp_btn_raw);
        button3=findViewById(R.id.taocan_sp_buy);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView1=findViewById(R.id.taocan_sp_shushi_pic);
//        recyclerView1.setLayoutManager(layoutManager);
//        recyclerView2=findViewById(R.id.taocan_sp_raw_pic);
//        recyclerView2.setLayoutManager(layoutManager);
        recyclerView3=findViewById(R.id.taocan_sp_raw_text);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
        list.add("东鸡胸肉+1431");
        list.add("西兰花+1431");
        list.add("牛油果+1431");
        taocanSpRawTextAdapter=new TaocanSpRawTextAdapter(list);
        recyclerView3.setAdapter(taocanSpRawTextAdapter);

        //下面是适配器

        //        setSupportActionBar(toolbar);
//        ActionBar actionBar=getSupportActionBar();
//        if(actionBar!=null){
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
        taocanImageView.setBackground(getDrawable(imageid));
        //Glide.with(this).load(imageid).into(taocanImageView);
        taocanContentText.setText(name+"\n秤食成餐外卖/新鲜食材速递");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.taocan_sp_btn_food:

                break;
            case R.id.taocan_sp_btn_raw:

                break;
            case R.id.taocan_sp_buy:
                View contentView = LayoutInflater.from(this).inflate(R.layout.shop_cart,null);
                ExpandableListView expandableListView=contentView.findViewById(R.id.shop_cart_elv);
                Shop_cart_adapter shop_cart_adapter=new Shop_cart_adapter(this);
                expandableListView.setAdapter(shop_cart_adapter);
                for (int i=0;i<Global_shop_cart.taoCan_bought_list.size();i++){
                    expandableListView.expandGroup(i);
                }
                popupWindow=new PopupWindow(contentView, RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setContentView(contentView);
                View rootview = LayoutInflater.from(this).inflate(R.layout.activity_taocan_sp_, null);
                popupWindow.showAtLocation(rootview, Gravity.BOTTOM,0,0);
                break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            if (popupWindow.isShowing()){
                popupWindow.dismiss();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return super.dispatchTouchEvent(ev);
    }
}
