package com.example.myapplication.main_page;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.web.WebService;

public class Un_sign_inActivity extends BaseActivity implements View.OnClickListener{
    private EditText editText_passwd;
    private EditText editText_user_name;
    private Button button_sign_in;
    private Button button_push;
    private TextView textView_sign_in_change;
    private LinearLayout ll_mm;
    private LinearLayout ll_yzm;
    private TimeCount timeCount;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    private Handler mhandler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){//判断登录状态
                case 200://登陆成功
                    editor=getSharedPreferences("token",MODE_PRIVATE).edit();//修改目前的token
                    editor.putInt("token",1);
                    editor.apply();
                    Intent intent=new Intent(Un_sign_inActivity.this,MainActivity.class);//跳转页面
                    startActivity(intent);
                    finish();
                    break;
                    default:
                        Toast.makeText(Un_sign_inActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                        break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_sign_in);
        check_Is_sign_in();
        ll_mm=findViewById(R.id.ll_mm);
        ll_yzm=findViewById(R.id.ll_yzm);
        ll_yzm.setVisibility(View.GONE);
        button_push=findViewById(R.id.sign_in_push);
        button_sign_in=findViewById(R.id.btn_sign_in);
        editText_passwd=findViewById(R.id.passwd);
        editText_user_name=findViewById(R.id.user_name);
        textView_sign_in_change=findViewById(R.id.sign_in_change);
        button_push.setOnClickListener(this);
        textView_sign_in_change.setOnClickListener(this);
        button_sign_in.setOnClickListener(this);
        timeCount=new TimeCount(6000,1000,button_push,"重新获取");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sign_in:
                final String user_name=editText_user_name.getText().toString();
                final String passwd=editText_passwd.getText().toString();
                int answer=WebService.Handler_Sign_in(user_name,passwd);
                Message message=new Message();
                message.what=answer;
                mhandler.sendMessage(message);
                break;
            case R.id.sign_in_change:
                if (textView_sign_in_change.getText().toString().equals("验证码登录")){
                    ll_mm.setVisibility(View.GONE);
                    ll_yzm.setVisibility(View.VISIBLE);
                    textView_sign_in_change.setText("密码登录");
                }else{
                    ll_mm.setVisibility(View.VISIBLE);
                    ll_yzm.setVisibility(View.GONE);
                    textView_sign_in_change.setText("验证码登录");
                }
                break;
            case R.id.sign_in_push:
                timeCount.start();
                Toast.makeText(this,"发送成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    void check_Is_sign_in(){//判断登录状态
        int stutes=0;
        sharedPreferences=getSharedPreferences("token",MODE_PRIVATE);
        stutes=sharedPreferences.getInt("token",0);
        switch (stutes){
            case 0:
                break;
            case 1:
                Intent intent=new Intent(Un_sign_inActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
    class TimeCount extends CountDownTimer {
        private Button button;
        private String finsh_string;
        public TimeCount(long millisInFuture, long countDownInterval,Button button,String s) {
            super(millisInFuture, countDownInterval);
            this.button=button;
            finsh_string=s;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            //button_push.setBackgroundColor(Color.parseColor("#B6B6D8"));
            button.setClickable(false);
            button.setText("("+millisUntilFinished / 1000 +") ");
        }

        @Override
        public void onFinish() {
            button.setText(finsh_string);
            button.setClickable(true);
        //    button_push.setBackgroundColor(Color.parseColor("#4EB84A"));

        }
    }
}
