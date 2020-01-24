package com.example.myapplication.food_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecipeActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private TaoCan[] taoCans={new TaoCan(R.drawable.pic_test_1,"套餐一"),new TaoCan(R.drawable.pic_text_2,"套餐二"),new TaoCan(R.drawable.pic_test_3,"套三")
            ,new TaoCan(R.drawable.round_pic,"套餐四")};
    private List<TaoCan> taoCanList=new ArrayList<>();
    private TextView textView_ygb;
    private TextView textView_all;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
       // setContentView(R.layout.recipe_buy);
     //   setContentView(R.layout.test);
        Init_1();
        Log.d("SWB", "点击成功");
        Init_2();
        adapter=new  RecipeAdapter(taoCanList);
        recyclerView=findViewById(R.id.recipe_recycle);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        textView_all=findViewById(R.id.text_all);
        textView_ygb=findViewById(R.id.text_ygb);
        textView_all.setOnClickListener(this);
        textView_ygb.setOnClickListener(this);
//       button=findViewById(R.id.test216);
//       button.setOnClickListener(this);
    }
    private void Init_2(){
        if (taoCanList.isEmpty()){
            for(int i=0;i<taoCans.length;i++){
                taoCanList.add(taoCans[i]);
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
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_all:
                textView_ygb.setTextColor(getResources().getColor(R.color.food_gray));
                textView_all.setTextColor(getResources().getColor(R.color.food_black));
                break;
            case R.id.text_ygb:
                textView_all.setTextColor(getResources().getColor(R.color.food_gray));
                textView_ygb.setTextColor(getResources().getColor(R.color.food_black));
                break;
                default:
                    break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            adapter.popupWindow.dismiss();
        }catch (Exception e){
            e.printStackTrace();
        }
        return super.dispatchTouchEvent(ev);
    }

    //    private void show(){
//        View contentView = LayoutInflater.from(this).inflate(R.layout.recipe_buy,null);
//        popupWindow=new PopupWindow(contentView,RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
//        popupWindow.setContentView(contentView);
//        View rootview = LayoutInflater.from(this).inflate(R.layout.test, null);
//        popupWindow.showAtLocation(rootview, Gravity.BOTTOM,0,0);
//    }

//    @Override
//    public  boolean onTouchEvent(MotionEvent event) {
////        try {
////            Toast.makeText(this,"sad",Toast.LENGTH_SHORT).show();
////            adapter.popupWindow.dismiss();
//        Log.d("SWB", "点击成功");
////        }catch (Exception e){
////            e.printStackTrace();
////        }
//        return true;
//    }
}
