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
import com.example.myapplication.web.WebService;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Fra_homepage extends Fragment implements View.OnClickListener{
    private Button button_test;
    private Food_pic_adapter food_pic_adapter;
    private ViewPager viewPager;
    //private List<Bitmap> list;
    private List<Integer> list;
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
        list=new ArrayList<>();
        //button_test=view.findViewById(R.id.measure);
        //button_test.setOnClickListener(this);
        Init_pic();
        food_pic_adapter=new Food_pic_adapter(getActivity(),list);
        viewPager.setAdapter(food_pic_adapter);
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
        list.add(R.drawable.pic_text_2);
        list.add(R.drawable.pic_test_3);
        list.add(R.drawable.pic_test_1);

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
}
