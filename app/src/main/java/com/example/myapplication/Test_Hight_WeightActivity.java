package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.example.myapplication.main_page.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test_Hight_WeightActivity extends BaseActivity implements View.OnClickListener{
    private TextView textView_target;
    private TextView textView_exercise;
    private TextView textView_birthday;
    private TextView textView_weight;
    private TextView textView_time_start;
    private TextView textView_time_end;
    private TimePickerView pickerView;
    private TimePickerView pickerView2;
    private TimePickerView pickerView3;
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
        pickerView =new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                textView_birthday.setText(getTime(date));
            }
        }).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字;
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();
        pickerView2=new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                textView_time_start.setText(getTime2(date));
            }
        }).setType(new boolean[]{false,false,false,true,true,false})
                .setCancelText("取消")
                .setSubmitText("确定")
                .isCenterLabel(false)
                .build();
        pickerView3=new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
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
            case R.id.text_my_birth_day:
                pickerView.show();
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

}
