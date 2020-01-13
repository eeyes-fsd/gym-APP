package com.example.myapplication.main_page;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.web.WebService;

public class Un_sign_inActivity extends BaseActivity implements View.OnClickListener{
    private EditText editText_passwd;
    private EditText editText_user_name;
    private Button button_sign_in;
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
        button_sign_in=findViewById(R.id.btn_sign_in);
        editText_passwd=findViewById(R.id.passwd);
        editText_user_name=findViewById(R.id.user_name);
        button_sign_in.setOnClickListener(this);
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
}
