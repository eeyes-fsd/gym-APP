package com.example.myapplication.adress;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AdressActivity extends BaseActivity implements View.OnClickListener{
    private int count=0;
    private TextView textView;
    private Button button;
    private RecyclerView recyclerView;
    private ArrayList<Adress> list=new ArrayList<>();
    String result="";
    AdressAdapter adapter=new AdressAdapter();
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
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.setList(list);

        recyclerView.setAdapter(adapter);
    }
    void Init_adress(){//这里安放的是请求功能函数
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    OkHttpClient client=new OkHttpClient();
//                    Request request=new  Request.Builder().url("http://10.0.2.2/get_data.json").build();
//                    Response response=client.newCall(request).execute();
//                    String respdata=response.body().string();
//                    Log.d("AdressAvtivity", respdata+"json数组内容");
//                    ParseJson(respdata);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String adress="http://10.0.2.2/get_data.json";
                try {
                    URL url=new URL(adress);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    InputStreamReader is = new InputStreamReader(conn.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(is);//读入
                    StringBuffer strBuffer = new StringBuffer();//字符串
                    String line = null;
                    while (null!=(line = bufferedReader.readLine()))  {
                        strBuffer.append(line);
                    }
                    result = strBuffer.toString();
                    is.close();
                    conn.disconnect();
                    ParseJson(result);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    void New_adress(){
        Intent intent=new Intent(AdressActivity.this,New_Adress_Activity.class);
        startActivityForResult(intent, Constant.NEW_ADRESS);
    }
    void ParseJson(String str){
        try {
            JSONArray jsonArray=new JSONArray(str);
            //Log.d("dasd", jsonArray.length()+"asdasdasdasdasdasdasdasdasd打算读");
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                Adress adress=new Adress();
                adress.setAdress(jsonObject.getString("adress"));
                adress.setName(jsonObject.getString("name"));
                adress.setSex(jsonObject.getString("gender"));
                adress.setTel(jsonObject.getString("phone"));
                Log.d("AdressActivity",jsonObject.getString("name")+"大苏打的暗示打算读阿斯顿阿三打是打算撒旦");
                list.add(adress);
            }
            judge();
            adapter.notifyDataSetChanged();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    void temp(){
        Adress adress = new Adress();
        adress.setSex("sex");
        adress.setTel("11531");
        adress.setName("name");
        adress.setAdress("dasd");
        for (int i=0;i<10;i++) {
            list.add(adress);
        }
        count=list.size();
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
        count=list.size();
        if (count!=0){//如果地址个数为0，则显示该文本
            textView.setVisibility(View.GONE);
        }
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
                    list.add(adress1);
                    count=list.size();
                    adapter.notifyDataSetChanged();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String adres="http://10.0.2.2/get_data.json";
                            try {
                                URL url=new URL(adres);
                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                conn.setRequestMethod("POST");
                                conn.setRequestProperty("Charset", "UTF-8");
                                JSONObject jsonObject=new JSONObject();
                                jsonObject.put("adress",adress);
                                jsonObject.put("name",name);
                                jsonObject.put("phont",tel);
                                jsonObject.put("gender",gender);
                                conn.setDoOutput(true);
                                // 设置允许输入
                                conn.setDoInput(true);
                                // 设置不用缓存
                                conn.setUseCaches(false);
                                conn.connect();
                                DataOutputStream out=new DataOutputStream(conn.getOutputStream());
                                out.write(jsonObject.toString().getBytes());
                                out.flush();
                                out.close();
                                InputStream is=conn.getInputStream();
                                conn.disconnect();

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }

                    }).start();
               }
                break;
        }
    }
}
