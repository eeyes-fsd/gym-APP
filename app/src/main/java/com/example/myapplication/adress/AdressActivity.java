package com.example.myapplication.adress;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Constant;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Test_Hight_WeightActivity;
import com.example.myapplication.main_page.BaseActivity;
import com.example.myapplication.web.MyProgressDialog;
import com.example.myapplication.web.Token;
import com.example.myapplication.web.WebService;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AdressActivity extends BaseActivity implements View.OnClickListener{
    private int count=0;
    private TextView textView;
    private Button button;
    private RecyclerView recyclerView;
    private static final String get_adress_list="/addresses";
    private static final String post_new_adress="/addresses";
    private static final String get_adress_cover="/addresses/2";
    private static final String delete_adress_cover="/addresses/3";
    private static final String put_change_adress="/addresses/2";
    public AdressAdapter adapter=new AdressAdapter();
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress);
        textView=findViewById(R.id.adress_hide);
        button=findViewById(R.id.adress_new);
        button=findViewById(R.id.adress_new);
        button.setOnClickListener(this);
        recyclerView=findViewById(R.id.recycle_adress);
        Init_adress();
        judge();
        context=this;
        adapter.setAdressActivity(this);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    void Init_adress(){//这里安放的是请求功能函数
        if(Constant.list.size()==0){
            OkHttpClient client=new OkHttpClient();
            Request request=new  Request.Builder().url("http://10.0.2.2/get_data.json").build();
            Call call=client.newCall(request);
            MyProgressDialog.CreatProgressDialog(this);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                    MyProgressDialog.Diss_progress_dialog();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(AdressActivity.this,"网络连接失败，请检查网络",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    MyProgressDialog.Diss_progress_dialog();
                    String respdata=response.body().string();
                    ParseJson(WebService.getJsonStrFromNetData(respdata));
                    Log.d("AdressAvtivity", Constant.list.size()+"json数组内容152");
                    try {
                        Thread.sleep(3000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    void New_adress(){
        Intent intent=new Intent(AdressActivity.this,New_Adress_Activity.class);
        startActivityForResult(intent, Constant.NEW_ADRESS);//请求码
        Log.d("sadfsadadasdsad", Constant.list.size()+" asdasd");
    }
    void ParseJson(String str){
        try {
//            str= WebService.getJsonStrFromNetData(str);
//            JSONArray jsonArray=new JSONArray(str);
//            //Log.d("dasd", jsonArray.length()+"asdasdasdasdasdasdasdasdasd打算读");
//            Log.d("SWBGYM", str+"qqqqqqqq");
//            for (int i=0;i<jsonArray.length();i++){
//                JSONObject jsonObject=jsonArray.getJSONObject(i);
//                Adress adress=new Adress();
//                adress.setAdress(jsonObject.getString("adress"));
//                adress.setName(jsonObject.getString("name"));
//                adress.setSex(jsonObject.getString("gender"));
//                adress.setTel(jsonObject.getString("phone"));
//                Log.d("AdressActivity",jsonObject.getString("name")+"大苏打的暗示打算读阿斯顿阿三打是打算撒旦");
//                Constant.list.add(adress);
//            }


            Adress[] adress=new Gson().fromJson(str,Adress[].class);
            for (Adress adress1:adress){
                Constant.list.add(adress1);
            }
            judge();
            adapter.notifyDataSetChanged();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.adress_new:
                New_adress();
                break;
        }
    }
    void judge(){
        count=Constant.list.size();
        if (count!=0){//如果地址个数为0，则显示该文本
            textView.setVisibility(View.GONE);
        }else textView.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode){
            case Constant.NEW_ADRESS:
                if (resultCode==RESULT_OK){
                    final String name=data.getStringExtra("name");
                    final String tel=data.getStringExtra("tel");
                    final String menpai=data.getStringExtra("menpai");
                    final String adress=data.getStringExtra("adress");
                    final String gender=data.getStringExtra("gender");
                    Adress adress1=new Adress(adress,gender,tel,name);
                    Constant.list.add(adress1);
                    count=Constant.list.size();
                    judge();
                    JSONObject jsonObject=new JSONObject();
                    adapter.notifyDataSetChanged();
                    try {
                        jsonObject.put("adress",adress);
                        jsonObject.put("name",name);
                        jsonObject.put("phone",tel);
                        jsonObject.put("gender",gender);
                        Call call=WebService.GYM_call(post_new_adress, Token.access_token,"POST",jsonObject);
                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(AdressActivity.this,"网络有误，上传失败，请检查网络",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                            }
                        });
                    }catch (Exception e){
                        e.printStackTrace();
                    }

               }
                break;
            case Constant.CHANGE_ADRESS:
                if(resultCode==RESULT_OK){
                    final String name=data.getStringExtra("name");
                    final String tel=data.getStringExtra("tel");
                    final String menpai=data.getStringExtra("menpai");
                    final String adress=data.getStringExtra("address");
                    final String gender=data.getStringExtra("gender");
                    int position=data.getIntExtra("position",0);
                    Constant.list.get(position).setSex(gender);
                    Constant.list.get(position).setTel(tel);
                    Constant.list.get(position).setName(name);
                    Constant.list.get(position).setAdress(adress);
                    adapter.notifyDataSetChanged();
                    JSONObject jsonObject=new JSONObject();
                    try {
                        jsonObject.put("adress",adress);
                        jsonObject.put("name",name);
                        jsonObject.put("phone",tel);
                        jsonObject.put("gender",gender);
                        Token.judge(this);
                        Call call=WebService.GYM_call(put_change_adress, Token.access_token,"PUT",jsonObject);
                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(AdressActivity.this,"网络有误，修改失败，请检查网络",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                            }
                        });
                    }catch (Exception e){
                        e.printStackTrace();
                    }
//                Toast.makeText(this,"asd",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    public void delete_adress(){
        Toast.makeText(this,"asdasdasd",Toast.LENGTH_SHORT).show();
        judge();
//        Call call=WebService.GYM_call(delete_adress_cover,Token.access_token,"DELETE",null);
    }
}
