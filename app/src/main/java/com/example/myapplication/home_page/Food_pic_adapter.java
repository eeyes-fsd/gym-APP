package com.example.myapplication.home_page;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myapplication.R;
import com.example.myapplication.food_shop.RecipeActivity;
import com.example.myapplication.food_shop.RecipeAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.Inflater;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Food_pic_adapter extends PagerAdapter {
    private Context context;
    //private List<Bitmap> list;
    //private List<Integer> list;

    private List<String> stringList;
    public Food_pic_adapter(Context context,List<String> list) {
        this.context=context;
        this.stringList=list;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        final View view= View.inflate(context, R.layout.food_pic,null);
        final ImageView imageView=view.findViewById(R.id.pic_food);//找到布局文件
        Request request=new Request.Builder().url(stringList.get(position)).build();
        final OkHttpClient client=new OkHttpClient();//申请图片
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //这里准备一张图片作为默认图片
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    InputStream inputStream = response.body().byteStream();//得到图片的流
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageView.setImageBitmap(bitmap);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
//        //imageView.setImageBitmap(list.get(position));//设置图片
//        imageView.setImageResource(stringList.get(position));
//        imageView.setOnClickListener(new View.OnClickListener() {//点击事件
//            @Override
//            public void onClick(View v) {
//                context.startActivity(new Intent(context, RecipeActivity.class));
//            }
//        });
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
