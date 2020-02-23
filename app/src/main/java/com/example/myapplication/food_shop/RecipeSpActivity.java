package com.example.myapplication.food_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.main_page.BaseActivity;
import com.example.myapplication.web.Token;
import com.example.myapplication.web.WebService;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecipeSpActivity extends BaseActivity implements View.OnClickListener {
    private int Id;
    private RecipeItem recipeItem;
    private String name;
    private String http_pic;
    private List<String> bf_ingredients=new ArrayList<>();
    private List<String> ln_ingredients=new ArrayList<>();
    private List<String> dn_ingredients=new ArrayList<>();
    private List<String> bf_nutrient=new ArrayList<>();
    private List<String> ln_nutrient=new ArrayList<>();
    private List<String> dn_nutrient=new ArrayList<>();
    private String bf_html;
    private String ln_html;
    private String dn_html;
    private TextView textView_name;
    private ImageView imageView;
    private TextView textView_nut_fat;
    private TextView textView_nut_pro;
    private TextView textView_nut_car;
    private TaocanSpRawTextAdapter adapter;
    private RecyclerView recyclerView;
    private TextView textView_bf;
    private TextView textView_ln;
    private TextView textView_dn;
    private WebView webView;
    int a=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_sp);
        Id=getIntent().getIntExtra("id",0);//用来请求的id
        textView_nut_car=findViewById(R.id.recipe_sp_tshhw);
        textView_nut_fat=findViewById(R.id.recipe_sp_zf);
        textView_nut_pro=findViewById(R.id.recipe_sp_dbz);
        textView_name=findViewById(R.id.recipe_sp_taocan_name);
        imageView=findViewById(R.id.recipe_sp_pic);
        recyclerView=findViewById(R.id.recipe_sp_yingyang);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        textView_bf=findViewById(R.id.recipe_sp_bf);
        textView_bf.setOnClickListener(this);
        textView_ln=findViewById(R.id.recipe_sp_ln);
        textView_ln.setOnClickListener(this);
        textView_dn=findViewById(R.id.recipe_sp_dn);
        textView_dn.setOnClickListener(this);
        webView=findViewById(R.id.recipe_sp_webview);
        Init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recipe_sp_bf:
                if (a!=1){
                    breakfast();
                    textView_bf.setTextColor(getResources().getColor(R.color.food_black));
                    textView_dn.setTextColor(getResources().getColor(R.color.food_gray));
                    textView_ln.setTextColor(getResources().getColor(R.color.food_gray));
                    a=1;
                }
                break;
            case R.id.recipe_sp_ln:
                if (a!=2){
                    lunch();
                    textView_bf.setTextColor(getResources().getColor(R.color.food_gray));
                    textView_dn.setTextColor(getResources().getColor(R.color.food_gray));
                    textView_ln.setTextColor(getResources().getColor(R.color.food_black));
                    a=2;
                }
                break;
            case R.id.recipe_sp_dn:
                if (a!=3){
                    dinner();
                    textView_bf.setTextColor(getResources().getColor(R.color.food_gray));
                    textView_dn.setTextColor(getResources().getColor(R.color.food_black));
                    textView_ln.setTextColor(getResources().getColor(R.color.food_gray));
                    a=3;
                }
                break;
        }
    }

    void breakfast(){
        textView_nut_fat.setText(bf_nutrient.get(0));
        textView_nut_pro.setText(bf_nutrient.get(1));
        textView_nut_car.setText(bf_nutrient.get(2));
        adapter=new TaocanSpRawTextAdapter(bf_ingredients);
        webView.loadDataWithBaseURL(null,bf_html,"text/html", "utf-8", null);
        adapter.notifyDataSetChanged();
    }
    void lunch(){
        textView_nut_fat.setText(ln_nutrient.get(0));
        textView_nut_pro.setText(ln_nutrient.get(1));
        textView_nut_car.setText(ln_nutrient.get(2));
        adapter=new TaocanSpRawTextAdapter(ln_ingredients);
        webView.loadDataWithBaseURL(null,ln_html,"text/html", "utf-8", null);
        adapter.notifyDataSetChanged();
    }
    void dinner(){
        textView_nut_fat.setText(dn_nutrient.get(0));
        textView_nut_pro.setText(dn_nutrient.get(1));
        textView_nut_car.setText(dn_nutrient.get(2));
        adapter=new TaocanSpRawTextAdapter(dn_ingredients);
        webView.loadDataWithBaseURL(null,dn_html,"text/html", "utf-8", null);
        adapter.notifyDataSetChanged();
    }
    void Init(){
        Request request=new Request.Builder()
                .url("https://gym.eeyes.xyz/api/recipes/"+Id+"/details")
                .addHeader("content-type", "application/x-www-form-urlencoded")//请求头
                .addHeader("Authorization", Token.access_token)//token,记得判断一下是否过期
                .build();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)//5秒的超时时间
                .writeTimeout(5, TimeUnit.SECONDS)//写入超时
                .readTimeout(5, TimeUnit.SECONDS)//读取超时
                .build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RecipeSpActivity.this,"网络错误",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject=new JSONObject(response.body().string());
                    name=jsonObject.getJSONObject("data").getString("name");
                    http_pic=jsonObject.getJSONObject("data").getString("cover");
                    for (int i = 0;i<jsonObject.getJSONObject("data").getJSONObject("breakfast").getJSONArray("ingredients").length();i++){
                        bf_ingredients.add(jsonObject.getJSONObject("data").getJSONObject("breakfast").getJSONArray("ingredients").getJSONObject(i).getString("name")+"+"+jsonObject.getJSONObject("data").getJSONObject("breakfast").getJSONArray("ingredients").getJSONObject(i).getString("amount"));
                    }
                    for (int i = 0;i<jsonObject.getJSONObject("data").getJSONObject("lunch").getJSONArray("ingredients").length();i++){
                        ln_ingredients.add(jsonObject.getJSONObject("data").getJSONObject("lunch").getJSONArray("ingredients").getJSONObject(i).getString("name")+"+"+jsonObject.getJSONObject("data").getJSONObject("breakfast").getJSONArray("ingredients").getJSONObject(i).getString("amount"));
                    }
                    for (int i = 0;i<jsonObject.getJSONObject("data").getJSONObject("dinner").getJSONArray("ingredients").length();i++){
                        dn_ingredients.add(jsonObject.getJSONObject("data").getJSONObject("dinner").getJSONArray("ingredients").getJSONObject(i).getString("name")+"+"+jsonObject.getJSONObject("data").getJSONObject("breakfast").getJSONArray("ingredients").getJSONObject(i).getString("amount"));
                    }
                    bf_nutrient.add(jsonObject.getJSONObject("data").getJSONObject("breakfast").getJSONObject("nutrient").getString("fat"));
                    bf_nutrient.add(jsonObject.getJSONObject("data").getJSONObject("breakfast").getJSONObject("nutrient").getString("protein"));
                    bf_nutrient.add(jsonObject.getJSONObject("data").getJSONObject("breakfast").getJSONObject("nutrient").getString("carbohydrate"));
                    ln_nutrient.add(jsonObject.getJSONObject("data").getJSONObject("lunch").getJSONObject("nutrient").getString("fat"));
                    ln_nutrient.add(jsonObject.getJSONObject("data").getJSONObject("lunch").getJSONObject("nutrient").getString("protein"));
                    ln_nutrient.add(jsonObject.getJSONObject("data").getJSONObject("lunch").getJSONObject("nutrient").getString("carbohydrate"));
                    dn_nutrient.add(jsonObject.getJSONObject("data").getJSONObject("dinner").getJSONObject("nutrient").getString("fat"));
                    dn_nutrient.add(jsonObject.getJSONObject("data").getJSONObject("dinner").getJSONObject("nutrient").getString("protein"));
                    dn_nutrient.add(jsonObject.getJSONObject("data").getJSONObject("dinner").getJSONObject("nutrient").getString("carbohydrate"));
                    bf_html=jsonObject.getJSONObject("data").getJSONObject("breakfast").getString("step");
                    ln_html=jsonObject.getJSONObject("data").getJSONObject("lunch").getString("step");
                    dn_html=jsonObject.getJSONObject("data").getJSONObject("dinner").getString("step");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView_nut_fat.setText(bf_nutrient.get(0));
                            textView_nut_pro.setText(bf_nutrient.get(1));
                            textView_nut_car.setText(bf_nutrient.get(2));
                            textView_name.setText(name);
                            adapter=new TaocanSpRawTextAdapter(bf_ingredients);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    });
                    Request request=new Request.Builder().url(http_pic).build();
                    OkHttpClient client0 = new OkHttpClient.Builder()
                            .connectTimeout(5, TimeUnit.SECONDS)//5秒的超时时间
                            .writeTimeout(5, TimeUnit.SECONDS)//写入超时
                            .readTimeout(5, TimeUnit.SECONDS)//读取超时
                            .build();
                    Call call0 =client0.newCall(request);
                    call0.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RecipeSpActivity.this,"网络有误，图片加载失败",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            InputStream inputStream = response.body().byteStream();//得到图片的流
                            final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    imageView.setImageBitmap(bitmap);
                                    webView.loadDataWithBaseURL(null,bf_html,"text/html", "utf-8", null);
                                }
                            });
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
