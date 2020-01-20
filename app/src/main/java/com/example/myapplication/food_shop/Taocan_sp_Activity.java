package com.example.myapplication.food_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Constant;
import com.example.myapplication.R;
import com.example.myapplication.main_page.BaseActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;


public class Taocan_sp_Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taocan_sp_);
        Intent intent=getIntent();
        String name=intent.getStringExtra(Constant.TAOCAN_NAME);
        int imageid=intent.getIntExtra(Constant.TAOCAN_IMAGE,0);
        //Toolbar toolbar=findViewById(R.id.toolbar_sp);
        CollapsingToolbarLayout collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar_sp);
        ImageView taocanImageView=findViewById(R.id.taocan_sp_pic);
        TextView taocanContentText=findViewById(R.id.taocan_content_text);
//        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(name);
        Glide.with(this).load(imageid).into(taocanImageView);
        taocanContentText.setText(name);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
