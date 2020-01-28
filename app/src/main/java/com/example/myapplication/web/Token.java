package com.example.myapplication.web;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.Constant;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Token {
    public static String access_token;
    public static Date token_time;
    static SharedPreferences.Editor editor;
    static SharedPreferences sharedPreferences;

    //本地储存，库名：token，  "access_token",access_token);
    //                         "token_time" , token_time.gettime;
    public final static String name="token";
    public final static String key1="access_token";
    public final static String key2="token_time";

    //逻辑：这个类存的是token的值和时间，每次系统启动时，会调用一次，每次请求时，先调用判断函数，如果过期就refresh
    private final static long ExpiredTime=7200;//过期时间，先定位两个小时，单位为ms
    private final static String refresh_token_api="/authorizations/current";
    private final static String login="/authorizations/weapp";



    public static void judge(Context context){
        if (isTokenExpired()){//过期的话进行调换，否则直接用即可
            refresh_token(context);
        }
    }


    public static boolean isTokenExpired() {
        Date date = new Date(System.currentTimeMillis());

        if (date.getTime() - token_time.getTime() > ExpiredTime) {
            return true;//已过期
        } else {
            return false;//未过期
        }
    }
    public static synchronized void get_token(final Context context){//初始化时
        Log.d("gym_get_token", "get_token: ");
        sharedPreferences=context.getSharedPreferences(name,Context.MODE_PRIVATE);
        String ttoken=sharedPreferences.getString(key1,null);
        if (ttoken!=null){//token为空时  这时候说明没有登录,引导登录
            access_token=ttoken;
            token_time=new Date(sharedPreferences.getLong(key2,0));
            //本地有token，直接拿取,但是拿取的之后要计算判断是否过期
        }
    }

    public static synchronized void refresh_token(final Context context){
        Log.d("gym_refresh_token", "refresh_token: ");
        Call call=WebService.GYM_call(refresh_token_api,access_token,"PUT",null);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                access_token=null;
//                token_time=null;   不应该设置为空，等有网络再去取，当退出时，设置为空
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    access_token=get_token_form(response.body().string());
                    token_time=new Date(System.currentTimeMillis());
                    editor=context.getSharedPreferences(name,Context.MODE_PRIVATE).edit();
                    editor.putString(key1,access_token);
                    editor.putLong(key2,token_time.getTime());
                    editor.apply();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private static synchronized void login(final Context context,JSONObject jsonObject){//登录

        Call call=WebService.GYM_call(login,null,"POST",jsonObject);
        //json数据需要数据填充
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(context,"登录失败，请检查网络",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {//登录成功时
                editor=context.getSharedPreferences("token",Context.MODE_PRIVATE).edit();
                access_token=get_token_form(response.body().string());
                token_time=new Date(System.currentTimeMillis());
                editor.putString("access_token",access_token);
                editor.putLong("token_time",token_time.getTime());
                editor.apply();
            }
        });
    }
    private static String get_token_form(String s){//从后端得到的数据经过处理成为token
        return s;
    }
}
