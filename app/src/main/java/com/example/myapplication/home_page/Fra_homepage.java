package com.example.myapplication.home_page;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.Constant;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.food_shop.RecipeActivity;
import com.example.myapplication.web.Token;
import com.example.myapplication.web.WebService;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Fra_homepage extends Fragment implements View.OnClickListener{
    private Button button_test;
    private Food_pic_adapter food_pic_adapter;
    private ViewPager viewPager;
    private Food_pic_adapter food_pic_adapter_recipe;
    private ViewPager viewPager_today;
    //private List<Bitmap> list;
    private List<String> list=new ArrayList<>();
    private List<String> list_today=new ArrayList<>();
//    Handler handler=new Handler(){//图片处理
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            list.add((Bitmap)msg.obj);
//            list.add((Bitmap)msg.obj);
//            list.add((Bitmap)msg.obj);
//            food_pic_adapter.notifyDataSetChanged();
//        }
//    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fra_homepage,container,false);
        viewPager=view.findViewById(R.id.food_pic);
        viewPager_today=view.findViewById(R.id.food_today_recipe);
        //button_test=view.findViewById(R.id.measure);
        //button_test.setOnClickListener(this);
       // Init_pic();
        food_pic_adapter=new Food_pic_adapter(getActivity(),list);
        food_pic_adapter_recipe=new Food_pic_adapter(getActivity(),list_today);
        viewPager.setAdapter(food_pic_adapter);
        viewPager_today.setAdapter(food_pic_adapter_recipe);
        viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getActivity(),viewPager.getCurrentItem()+"",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), RecipeActivity.class));//跳转
            }
        });
        return view;
    }

    private void Init_pic(){
//        list.add(R.drawable.pic_text_2);
//        list.add(R.drawable.pic_test_3);
//        list.add(R.drawable.pic_test_1);

//        Thread thread=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String adress1="http://10.0.2.2/pic1.jpg";
//                //String adress1="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575777829028&di=04f71498cc1ab3bddfd95924c8ec7c01&imgtype=0&src=http%3A%2F%2Fwww.cyec8.com%2FPublic%2FUploads%2FArticle%2F20150711%2F55a0efbe96fae.jpg";
//                try {
//                    URL url1=new URL(adress1);
//                    HttpURLConnection connection=(HttpURLConnection)url1.openConnection();
//                    connection.setRequestMethod("GET");
//                    connection.setReadTimeout(1000*5);
//                    connection.setConnectTimeout(1000*5);
//                    //connection.setRequestProperty("token",token); //请求头
//                    connection.connect();
//                    if (connection.getResponseCode()==HttpURLConnection.HTTP_OK){
//                        InputStream is=connection.getInputStream();
//                        Bitmap bitmap= BitmapFactory.decodeStream(is);
//                        Message message=handler.obtainMessage();
//                        message.obj=bitmap;
//                        message.what=0;
//                        handler.sendMessage(message);
//    	    			is.close();
//                    }else Toast.makeText(getActivity(),"fail",Toast.LENGTH_SHORT).show();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread.start();
        Token.judge(getActivity());
        Call call=WebService.GYM_call("/recipes/new?count=10&page=2",Token.access_token,"GET",null);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "网络连接失败，获取上新推荐失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s=response.body().string();
            }
        });
    }



    private void Init_today_recipe(){
        Token.judge(getActivity());
        Call call=WebService.GYM_call("/recipes/today",Token.access_token,"GET",null);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "网络连接失败，获取今日推荐失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s=response.body().string();
            }
        });
    }
    @Override
    public void onClick(View v) {
        //this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1
        Fra_homepage.this.requestPermissions(new String[]{Manifest.permission.INTERNET},Constant.REQUEST_INTERNET_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case Constant.REQUEST_INTERNET_CODE:
                if (grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Init_pic();
                }else {
                    Toast.makeText(getActivity(),"你拒绝了申请",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    private void Init_today(){

    }
    private void Init_new_recipe(){
        //今日推荐
        Token.judge(getActivity());
        Call call=WebService.GYM_call("/recipes/today",Token.access_token,"GET",null);

    }
    private void Init_get_intake(){
        Token.judge(getActivity());
        Call call=WebService.GYM_call("/health/intake",Token.access_token,"GET",null);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getActivity(), "获取摄入信息失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s=response.body().string();
                try {
                    JSONObject jsonObject=new JSONObject(s);

                }catch (Exception e){

                }

            }
        });
    }
}
