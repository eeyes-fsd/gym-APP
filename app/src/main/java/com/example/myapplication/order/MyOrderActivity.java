package com.example.myapplication.order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.web.Token;
import com.example.myapplication.web.WebService;
import com.google.gson.Gson;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyOrderActivity extends AppCompatActivity {
    private List<Order> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
    }
    private void Init(){
        Call call= WebService.GYM_call("/orders", Token.access_token,"GET",null);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MyOrderActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    Parse(response.body().string());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    private void Parse(String s){
        try {
            Order[] orders=new Gson().fromJson(s,Order[].class);
            list.addAll(Arrays.asList(orders));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
