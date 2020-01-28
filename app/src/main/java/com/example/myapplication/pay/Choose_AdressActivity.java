package com.example.myapplication.pay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.Constant;
import com.example.myapplication.R;
import com.example.myapplication.adress.Adress;
import com.example.myapplication.adress.AdressActivity;
import com.example.myapplication.web.MyProgressDialog;
import com.example.myapplication.web.WebService;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Choose_AdressActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private         Pay_Adress_Adapter pay_adress_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose__adress);
        Init();
        recyclerView=findViewById(R.id.pay_adress);
        pay_adress_adapter=new Pay_Adress_Adapter();
        pay_adress_adapter.setAdressActivity(this);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setAdapter(pay_adress_adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
    void Init(){
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
                            Toast.makeText(Choose_AdressActivity.this,"网络连接失败，请检查网络",Toast.LENGTH_SHORT).show();
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
            pay_adress_adapter.notifyDataSetChanged();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
