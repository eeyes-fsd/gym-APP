package com.example.myapplication;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.home_page.Fra_My;
import com.example.myapplication.home_page.Fra_food;
import com.example.myapplication.home_page.Fra_homepage;
import com.example.myapplication.main_page.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
//import com.android.support.design.widget.BottomNavigationView;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private Fra_homepage fra_homepage;
    private Fra_My fra_my;
    private Fra_food fra_food;
//    private Button btn_homepage;
//    private Button btn_my;
//    private Button btn_food;
    private BottomNavigationView bottomNavigationView;
    int judge=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fra_homepage=new Fra_homepage();
        fra_my=new Fra_My();
        fra_food=new Fra_food();
        getSupportFragmentManager().beginTransaction().replace(R.id.home_ll,fra_homepage).commit();
        bottomNavigationView=findViewById(R.id.main_bv);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_page:
                        if (judge!=0){
                            getSupportFragmentManager().beginTransaction().replace(R.id.home_ll,fra_homepage).commit();
                            judge=0;
                        }

                        return true;
                    case R.id.my:
                        if (judge!=1){
                            getSupportFragmentManager().beginTransaction().replace(R.id.home_ll,fra_my).commit();
                            judge=1;
                        }

                        return true;
                    case R.id.food:
                        if (judge!=2){
                            getSupportFragmentManager().beginTransaction().replace(R.id.home_ll,fra_food).commit();
                            judge=2;
                        }
                        return true;
                }
                return false;
            }
        });
//        btn_food=findViewById(R.id.home_page_food);
//        btn_homepage=findViewById(R.id.home_page_home);
//        btn_my=findViewById(R.id.home_page_my);
//        btn_homepage.setOnClickListener(this);
//        btn_my.setOnClickListener(this);
//        btn_food.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.home_page_food:
//                getSupportFragmentManager().beginTransaction().replace(R.id.home_ll,fra_food).commit();
//                break;
//            case R.id.home_page_my:
//                getSupportFragmentManager().beginTransaction().replace(R.id.home_ll,fra_my).commit();
//                break;
//            case R.id.home_page_home:
//                getSupportFragmentManager().beginTransaction().replace(R.id.home_ll,fra_homepage).commit();
//                break;
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case Constant.REQUEST_INTERNET_CODE:
                if (grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"你接受了申请",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"你拒绝了申请",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public interface MyTouchListener {
        public void onTouchEvent(MotionEvent event);
    }

    // 保存MyTouchListener接口的列表
    private List<MyTouchListener> myTouchListeners = new ArrayList<MainActivity.MyTouchListener>();

    /**
     * 提供给Fragment通过getActivity()方法来注册自己的触摸事件的方法
     * @param listener
     */
    public void registerMyTouchListener(MyTouchListener listener) {
        myTouchListeners.add(listener);
    }

    /**
     * 提供给Fragment通过getActivity()方法来取消注册自己的触摸事件的方法
     * @param listener
     */
    public void unRegisterMyTouchListener(MyTouchListener listener) {
        myTouchListeners.remove( listener );
    }

    /**
     * 分发触摸事件给所有注册了MyTouchListener的接口
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyTouchListener listener : myTouchListeners) {
            listener.onTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }
}
