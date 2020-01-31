package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.myapplication.main_page.BaseActivity;
import com.example.myapplication.web.MyProgressDialog;
import com.example.myapplication.web.Token;
import com.example.myapplication.web.WebService;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Test_Hight_WeightActivity extends BaseActivity implements View.OnClickListener{
    private static final String post_save_healthy="/health";
    private static final String get_healthy="/health";
    private static final String put_healthy="/health";
    private static final String get_intake="/health/intake";
    private TextView textView_target;
    private TextView textView_exercise;
    private TextView textView_birthday;
    private TextView textView_weight;
    private TextView textView_time_start;
    private TextView textView_time_end;
    private TextView textView_fat_rate;
    private TextView textView_salt_rate;
    private TextView textView_hight;
    private Button button;
    private TimePickerView pickerView;
    private TimePickerView pickerView2;
    private TimePickerView pickerView3;
    private TextView textView_gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test__hight__weight);
        textView_birthday=findViewById(R.id.text_my_birth_day);
        textView_birthday.setOnClickListener(this);
        textView_weight=findViewById(R.id.my_weight);
        textView_weight.setOnClickListener(this);
        textView_exercise=findViewById(R.id.exercise);
        textView_exercise.setOnClickListener(this);
        textView_target=findViewById(R.id.target);
        textView_target.setOnClickListener(this);
        textView_time_end=findViewById(R.id.time_end);
        textView_time_end.setOnClickListener(this);
        textView_time_start=findViewById(R.id.time_start);
        textView_time_start.setOnClickListener(this);
        textView_gender=findViewById(R.id.healthy_gender);
        textView_gender.setOnClickListener(this);

        button=findViewById(R.id.healthy_submit);
        button.setOnClickListener(this);

        textView_fat_rate=findViewById(R.id.fat_rate);
        textView_fat_rate.setOnClickListener(this);

        textView_salt_rate=findViewById(R.id.salt_rate);
        textView_salt_rate.setOnClickListener(this);
        textView_hight=findViewById(R.id.my_hight);
        textView_hight.setOnClickListener(this);
//        get_data();
        pickerView =new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                textView_birthday.setText(getTime(date));
            }
        }).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字;
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();

        pickerView2=new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                textView_time_start.setText(getTime2(date));
            }
        }).setType(new boolean[]{false,false,false,true,true,false})
                .setCancelText("取消")
                .setSubmitText("确定")
                .isCenterLabel(false)
                .build();
        pickerView3=new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                textView_time_end.setText(getTime2(date));
            }
        }).setType(new boolean[]{false,false,false,true,true,false})
                .setCancelText("取消")
                .setSubmitText("确定")
                .isCenterLabel(false)
                .build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.healthy_submit:                         //post
                String birthday=textView_birthday.getText().toString();
                String gender=textView_gender.getText().toString();
                String weight=textView_weight.getText().toString();
                String height=textView_hight.getText().toString();
                String exercise =textView_exercise.getText().toString();
                String purpose =textView_target.getText().toString();
                String fat_rate=textView_fat_rate.getText().toString();//选填项
                String salt_rate=textView_salt_rate.getText().toString();//
                String time1=textView_time_start.getText().toString();//
                String time2=textView_time_end.getText().toString();//
                String work_time =textView_time_start.getText().toString()+"-"+textView_time_end.getText().toString();//
                if (!birthday.equals("")&&!gender.equals("")&&!weight.equals("")&&!height.equals("")&&!exercise.equals("")&&!purpose.equals("")){
                    JSONObject jsonObject=new JSONObject();
                    try {
                        jsonObject.put("birthday",birthday);
                        jsonObject.put("gender",gender);
                        jsonObject.put("weight",weight);
                        jsonObject.put("height",height);
                        jsonObject.put("exercise",exercise);
                        jsonObject.put("purpose",purpose);
                        jsonObject.put("fat_rate",fat_rate);
                        jsonObject.put("salt_rate",salt_rate);
                        jsonObject.put("work_time",work_time);

                        Token.judge(this);
                        Call call = WebService.GYM_call(post_save_healthy, Token.access_token,"POST",jsonObject);
                        MyProgressDialog.CreatProgressDialog(this);
                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Test_Hight_WeightActivity.this,"网络有误上传失败",Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                MyProgressDialog.Diss_progress_dialog();
                            }
                        });
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(this,"请填写必填项",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.healthy_gender:
                final String[] items3 = {"男","女"};
                AlertDialog.Builder builder10= new AlertDialog.Builder(Test_Hight_WeightActivity.this);
                builder10.setTitle("请输入您的性别");
                builder10.setItems(items3, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView_gender.setText(items3[which]);
                    }
                });
                builder10.show();
                break;
            case R.id.text_my_birth_day:
                pickerView.show();
                break;
            case R.id.my_hight:
                final AlertDialog.Builder builder3=new AlertDialog.Builder(this);
                final EditText editText3=new EditText(this);
                builder3.setTitle("请输入体重");
                builder3.setView(editText3);
                builder3.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String string=editText3.getText().toString();
                        int a=1;
                        try {
                            double temp=Double.parseDouble(string);
                        }catch (Exception e){
                            Toast.makeText(Test_Hight_WeightActivity.this,"只能输入数字",Toast.LENGTH_SHORT).show();
                            a=0;
                            editText3.setText("");
                        }
                        if(a==1) {
                            textView_hight.setText(string+" cm");
                            Toast.makeText(Test_Hight_WeightActivity.this, "输入成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder3.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder3.show();
                break;
            case R.id.my_weight:
                final AlertDialog.Builder builder=new AlertDialog.Builder(this);
                final EditText editText=new EditText(this);
                builder.setTitle("请输入体重");
                builder.setView(editText);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String string=editText.getText().toString();
                        int a=1;
                        try {
                            double temp=Double.parseDouble(string);
                        }catch (Exception e){
                            Toast.makeText(Test_Hight_WeightActivity.this,"只能输入数字",Toast.LENGTH_SHORT).show();
                            a=0;
                            editText.setText("");
                        }
                        if(a==1) {
                            textView_weight.setText(string+" kg");
                            Toast.makeText(Test_Hight_WeightActivity.this, "输入成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
                break;
            case R.id.exercise:
                final String[] items = {"选项1","选项2","选项3"};
                AlertDialog.Builder builder1= new AlertDialog.Builder(Test_Hight_WeightActivity.this);
                builder1.setTitle("请输入锻炼频率");
                builder1.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView_exercise.setText(items[which]);
                    }
                });
                builder1.show();
                break;
            case R.id.target:
                final  String[] items2={"选项1","选项2","选项3"};
                AlertDialog.Builder builder2=new AlertDialog.Builder(Test_Hight_WeightActivity.this);
                builder2.setTitle("请输入锻炼目的");
                builder2.setItems(items2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView_target.setText(items2[which]);
                    }
                });
                builder2.show();
                break;
            case R.id.time_start:
                pickerView2.show();
                break;
            case R.id.time_end:
                pickerView3.show();
                break;
            case R.id.fat_rate:
                break;
            case R.id.salt_rate:
                final AlertDialog.Builder builder0=new AlertDialog.Builder(this);
                final EditText editText0=new EditText(this);
                builder0.setTitle("请输入体重");
                builder0.setView(editText0);
                builder0.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String string=editText0.getText().toString();
                        int a=1;
                        try {
                            double temp=Double.parseDouble(string);
                        }catch (Exception e){
                            Toast.makeText(Test_Hight_WeightActivity.this,"只能输入数字",Toast.LENGTH_SHORT).show();
                            a=0;
                            editText0.setText("");
                        }
                        if(a==1) {
                            textView_salt_rate.setText(string+" g");
                            Toast.makeText(Test_Hight_WeightActivity.this, "输入成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder0.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder0.show();
                break;
        }
    }

    private String getTime(java.util.Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        return dateStr;
    }
    private String getTime2(java.util.Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        String dateStr = sdf.format(date);
        return dateStr;
    }



    private void get_data(){//第一次加载时获取服务端用户信息
//        Token.judge(this);
        Call call=WebService.GYM_call(get_healthy,Token.access_token,"GET",null);
        MyProgressDialog.CreatProgressDialog(this);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.Diss_progress_dialog();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Test_Hight_WeightActivity.this,"网络有误获取数据失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                MyProgressDialog.Diss_progress_dialog();
                try {
                    String s=response.body().string();
                    JSONObject jsonObject=new JSONObject(s);
                    textView_birthday.setText(jsonObject.getString("birthday"));
                    textView_gender.setText(jsonObject.getString("gender"));
                    textView_weight.setText(jsonObject.getString("weight"));
                    textView_hight.setText(jsonObject.getString("height"));
                    textView_exercise.setText(jsonObject.getString("exercise"));
                    textView_target.setText(jsonObject.getString("purpose"));
                    textView_fat_rate.setText(jsonObject.getString("fat_rate"));
                    textView_salt_rate.setText(jsonObject.getString("salt_rate"));
                    String time=jsonObject.getString("work_time");
                    int i=time.indexOf("-");
                    textView_time_start.setText(time.substring(0,i));
                    textView_time_end.setText(time.substring(i+1,time.length()));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
