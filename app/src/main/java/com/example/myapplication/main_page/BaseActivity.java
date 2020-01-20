package com.example.myapplication.main_page;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.ActionMode;

import androidx.annotation.Nullable;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BaseActivity extends AppCompatActivity {
  //  private Stack<AppCompatActivity> activities=new Stack<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置竖屏
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
       // activities.push(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
      //  activities.remove(this);
    }

}
