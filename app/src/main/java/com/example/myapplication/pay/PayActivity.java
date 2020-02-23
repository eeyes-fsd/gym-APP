package com.example.myapplication.pay;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.Constant;
import com.example.myapplication.R;
import com.example.myapplication.adress.Adress;
import com.example.myapplication.adress.Pay_Item_Adapter;
import com.example.myapplication.food_shop.Global_shop_cart;
import com.example.myapplication.main_page.BaseActivity;

public class PayActivity extends BaseActivity implements View.OnClickListener {
    private TextView textView_choose;
    private LinearLayout linearLayout;
    private TextView textView_name_sex;
    private TextView textView_tel;
    private TextView textView_adress;
    private RecyclerView recyclerView;
    private         Pay_Item_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        textView_choose=findViewById(R.id.pay_choose);//选择地址
        textView_choose.setOnClickListener(this);
        linearLayout=findViewById(R.id.pay_ll_adress);
        linearLayout.setVisibility(View.GONE);
        textView_name_sex=findViewById(R.id.pay_adress_name_sex);
        textView_tel=findViewById(R.id.pay_adress_tel);
        textView_adress=findViewById(R.id.pay_adress_ar);
        recyclerView=findViewById(R.id.pay_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new Pay_Item_Adapter();
        recyclerView.setAdapter(adapter);
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
                        if (linearLayout.getVisibility()==View.GONE){
                            linearLayout.setVisibility(View.VISIBLE);
                        }
                        int p=data.getIntExtra("position",-1);
                        Adress adress=Constant.list.get(p);
                        textView_name_sex.setText(adress.getName()+" "+adress.getGender());
                        textView_tel.setText(adress.getPhone());
                        textView_adress.setText(adress.getDetails());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
