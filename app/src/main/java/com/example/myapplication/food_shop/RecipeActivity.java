package com.example.myapplication.food_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.web.MyProgressDialog;
import com.example.myapplication.web.Token;
import com.example.myapplication.web.WebService;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecipeActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private TaoCan[] taoCans={new TaoCan(R.drawable.pic_test_1,"套餐一"),new TaoCan(R.drawable.pic_text_2,"套餐二"),new TaoCan(R.drawable.pic_test_3,"套三")
            ,new TaoCan(R.drawable.round_pic,"套餐四")};
    private List<TaoCan> taoCanList=new ArrayList<>();
    private TextView textView_ygb;
    private TextView textView_all;
    private List<TaoCan> taoCanList_bought_recipe=new ArrayList<>();//已购买的配餐
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
       // setContentView(R.layout.recipe_buy);
     //   setContentView(R.layout.test);
//        Init_1();
//        Log.d("SWB", "点击成功");
//        Init_2();
        get_data();
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
            case R.id.text_all://全部
                textView_ygb.setTextColor(getResources().getColor(R.color.food_gray));
                textView_all.setTextColor(getResources().getColor(R.color.food_black));
                break;
            case R.id.text_ygb://已购买
                textView_all.setTextColor(getResources().getColor(R.color.food_gray));
                textView_ygb.setTextColor(getResources().getColor(R.color.food_black));
                if (taoCanList_bought_recipe.isEmpty()){
                    get_bought();
                }
                break;
                default:
                    break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            if (adapter.popupWindow.isShowing()) adapter.popupWindow.dismiss();
        }catch (Exception e){
            e.printStackTrace();
        }
        return super.dispatchTouchEvent(ev);
    }
    void get_data(){//这个时候获得为全部套餐
        if (taoCanList.isEmpty()){//当数据为空时获得全部数据
            final Request request=new Request.Builder().url("http://10.0.2.2/get_recipe.json").build();
            OkHttpClient client=new OkHttpClient();
            Call call=client.newCall(request);
            Log.d("SWBSWBSWB", "孙文冰孙文冰");
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RecipeActivity.this,"网络连接失败",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        Log.d("SWBSWBSWB", "response撒是的哇打算");
                        String s=response.body().string();
//                        TaoCan[] taoCans=new Gson().fromJson(s,TaoCan[].class);
//                        for (TaoCan taoCan:taoCans){
//                            taoCanList.add(taoCan);
//                            Log.d("SWBSWBSWB", taoCan.getName());
//                        }
                        JSONArray jsonArray=new JSONArray(s);
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            TaoCan taoCan=new TaoCan();
                            taoCan.setName(jsonObject.getString("name"));
                            taoCan.setId(jsonObject.getInt("id"));
                            taoCan.setDescription(jsonObject.getString("description"));
                            taoCan.setCover(jsonObject.getString("cover"));
                            taoCanList.add(taoCan);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
//            Token.judge(this);
//            MyProgressDialog.CreatProgressDialog(this);
//            Call call_ = WebService.GYM_call(TaoCan.get_all_recipe,Token.access_token,"GET",null);
//
//            call_.enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    MyProgressDialog.Diss_progress_dialog();
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(RecipeActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    MyProgressDialog.Diss_progress_dialog();
//                    Parse(response.body().string(),1);
//                }
//            });
        }
    }
    private void get_bought(){
//        Token.judge(this);
//        Call call=WebService.GYM_call(TaoCan.get_already_bought,Token.access_token,"GET",null);
//        MyProgressDialog.CreatProgressDialog(this);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                MyProgressDialog.Diss_progress_dialog();
//                Toast.makeText(RecipeActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                MyProgressDialog.Diss_progress_dialog();
//                Parse(response.body().string(),2);
//            }
//        });
    }
    private void Parse(String s,int a){
        TaoCan[] taoCans=new Gson().fromJson(s,TaoCan[].class);
        if (a==1){
            taoCanList.addAll(Arrays.asList(taoCans));
            adapter.notifyDataSetChanged();
        }else{
            taoCanList_bought_recipe.addAll(Arrays.asList(taoCans));
            //这里是一个新的适配器的notify
        }
    }
}
