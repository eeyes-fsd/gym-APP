package com.example.myapplication.web;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Permission;

public class WebService {
    private static String data;
    private final static String url="eeyes";//这里是静态网址链接，以后会有很多，现在用一个url代替

    public static String WebRequest_GET(final String adress){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                BufferedReader reader=null;

                try {
                    //请求前加上token
                    URL url=new URL(adress);
                    connection=(HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(10000);//超时事件为10s
                    connection.setReadTimeout(10000);//超时事件为10s
                    connection.connect();
                    if (connection.getResponseCode()==200){//请求成功
                        InputStream in=connection.getInputStream();
                        reader=new BufferedReader(new InputStreamReader(in));//得到了输入，接下来开始处理数据******************************************************
                        StringBuilder response=new StringBuilder();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
        return "";
    }
    public static String WebRequest_POST(final String adress,final String data){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                try {
                    URL url=new URL(adress);
                    connection=(HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setConnectTimeout(1000*10);
                    connection.connect();
                    switch (connection.getResponseCode()){//////////////////根据返回码进行操作
                        case 1:
                            break;
                            default:
                                break;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (connection!=null){
                        connection.disconnect();
                    }
                }


            }
        }).start();
        return "";
    }
    public static int Handler_Sign_in(final String username,final String password){
        if (username.equals("user")&&password.equals("passwd")){
            return 200;
        }else return 1;
        //下面是网络请求，现在先用默认的简单请求


//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            URL http_url;
//                            try {
//                                http_url=new URL(url);
//                                if(http_url!=null)
//                                {
//                                    //打开一个HttpURLConnection连接
//                                    HttpURLConnection conn = (HttpURLConnection) http_url.openConnection();
//                                    conn.setConnectTimeout(5* 1000);//设置连接超时
//                                    conn.setRequestMethod("POST");//以get方式发起请求
//                                    //允许输入输出流
//                                    conn.setDoInput(true);
//                                    conn.setDoOutput(true);
//                                    conn.setUseCaches(false);//使用Post方式不能使用缓存
//                                    //获取账号密码
//                                    String params = "username=" + username
//                                            + "&password=" + password;
//                                    //设置请求体的类型是文本类型
//                                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//                                    //设置请求体的长度--字节长度
//                                    conn.setRequestProperty("Content-Length",String.valueOf(params.getBytes().length) );
//                                    //发送post参数
//                                    BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//                                    bw.write(params);
//                                    bw.close();
//                                    //接收服务器响应
//                                    if (conn.getResponseCode() == 200) {//网络请求成功，下面进行数据处理
//                                        InputStream is = conn.getInputStream();//得到网络返回的输入流
//                                        BufferedReader buf=new BufferedReader(new InputStreamReader(is));//转化为字符缓冲流
//                                        data=buf.readLine();
//                                        buf.close();is.close();
//                                        //得到数据，进行判断
//                                        Analyse_Answer(data);
//                                    }
//                                }
//                            } catch( Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }).start();
                    //return 200;
    }

    private static void Analyse_Answer(String data){
        //这里处理得到的登入结果
        try {
            JSONObject json_data=new JSONObject(data);
            Boolean state=json_data.getBoolean("success");//是否登录成功
            String msg=json_data.getString("msg"); //其他信息，具体信息待定
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void Get_Pic(final String url, ImageView imageView){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL imgUrl = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection)imgUrl.openConnection();
                    conn.setDoInput(true);
                    conn.setRequestMethod("GET");
                    //链接超时
                    conn.setConnectTimeout(1000*10);
                    //读取超时
                    conn.setReadTimeout(1000*10);
                    conn.connect();
                    //获得输入流
                    if(conn.getResponseCode()==200){
                        InputStream is = conn.getInputStream();
                        //把其转化为位图
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        is.close();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public static void Ask_For_Internet(Activity activity,String[] permissions,int requestCode){

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.INTERNET)== PackageManager.PERMISSION_GRANTED){//得到许可
            Toast.makeText(activity,"得到许可",Toast.LENGTH_SHORT).show();
        }else {
            ActivityCompat.requestPermissions(activity,permissions,requestCode);
        }
    }


}
