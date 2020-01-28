package com.example.myapplication.pay;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.Constant;
import com.example.myapplication.R;
import com.example.myapplication.main_page.BaseActivity;

public class PayActivity extends BaseActivity implements View.OnClickListener {
    private TextView textView_choose;
    private TextView textView_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        textView_choose=findViewById(R.id.pay_choose);//选择地址
        textView_choose.setOnClickListener(this);
        textView_name=findViewById(R.id.pay_name);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pay_choose:
                //跳转到一个新的页面，获取点击的位置，然后返回
                startActivityForResult(new Intent(this,Choose_AdressActivity.class),Constant.CHOOSE_ADRESS);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Constant.CHOOSE_ADRESS:
                if(resultCode==RESULT_OK){
                    try {
                        textView_name.setText(Constant.list.get(data.getIntExtra("position",0)).getAdress());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
