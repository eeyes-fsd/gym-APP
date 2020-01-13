package com.example.myapplication.adress;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Constant;
import com.example.myapplication.R;
import com.example.myapplication.Test_Hight_WeightActivity;
import com.example.myapplication.main_page.BaseActivity;

public class New_Adress_Activity extends BaseActivity implements View.OnClickListener{
    private Button button;
    private EditText editText_name;
    private String name;
    private EditText editText_tel;
    private String tel;
    private EditText editText_adress;
    private String adress;
    private EditText editText_menpai;
    private String menpai;
    private TextView textView_sex;
    private String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__adress_);
        button=findViewById(R.id.new_adress_summit);
        textView_sex=findViewById(R.id.new_adress_gender);
        textView_sex.setOnClickListener(this);
        editText_adress=findViewById(R.id.new_adress_adress);
        editText_menpai=findViewById(R.id.new_adress_menpai);
        editText_tel=findViewById(R.id.new_adress_tel);
        editText_name=findViewById(R.id.new_adress_name);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.new_adress_summit:
                name=editText_name.getText().toString();
                tel=editText_tel.getText().toString();
                menpai=editText_menpai.getText().toString();
                adress=editText_adress.getText().toString();
                gender=textView_sex.getText().toString();
                if(!name.equals("")&&!gender.equals("")&&!tel.equals("")&&!menpai.equals("")&&!adress.equals("")){
                    Intent intent=new Intent(New_Adress_Activity.this,AdressActivity.class);
                    intent.putExtra("name",name);
                    intent.putExtra("tel",tel);
                    intent.putExtra("menpai",menpai);
                    intent.putExtra("adress",adress);
                    intent.putExtra("gender",gender);
                    setResult(RESULT_OK,intent);
                    finish();
                }else {
                    Toast.makeText(New_Adress_Activity.this,"请全部输入",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.new_adress_gender:
                final String[] items = {"先生","女士"};
                AlertDialog.Builder builder1= new AlertDialog.Builder(New_Adress_Activity.this);
                builder1.setTitle("您的称呼是");
                builder1.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView_sex.setText(items[which]);
                    }
                });
                builder1.show();
                break;
        }
    }
}
