package com.example.myapplication.home_page;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myapplication.R;
import com.example.myapplication.food_shop.RecipeActivity;
import com.example.myapplication.food_shop.RecipeAdapter;

import java.util.List;
import java.util.zip.Inflater;

public class Food_pic_adapter extends PagerAdapter {
    private Context context;
    //private List<Bitmap> list;
    private List<Integer> list;

    public Food_pic_adapter(Context context,List<Integer> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        final View view= View.inflate(context, R.layout.food_pic,null);
        ImageView imageView=view.findViewById(R.id.pic_food);//找到布局文件
        //imageView.setImageBitmap(list.get(position));//设置图片
        imageView.setImageResource(list.get(position));
        imageView.setOnClickListener(new View.OnClickListener() {//点击事件
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, RecipeActivity.class));
            }
        });
        
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}
