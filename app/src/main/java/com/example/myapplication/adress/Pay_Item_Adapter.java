package com.example.myapplication.adress;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.food_shop.Global_shop_cart;
import com.example.myapplication.food_shop.TaoCan;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Pay_Item_Adapter extends RecyclerView.Adapter<Pay_Item_Adapter.MyViewHolder> {
    private Context mcontext;
    public static class MyViewHolder  extends RecyclerView.ViewHolder{
        TextView textView_price;
        TextView textView_name;
        TextView textView_num;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.pay_item_pic);
            textView_name=itemView.findViewById(R.id.pay_item_name);
            textView_num=itemView.findViewById(R.id.pay_item_num);
            textView_price=itemView.findViewById(R.id.pay_item_price);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mcontext == null) {
            mcontext = parent.getContext();
        }
        View view = LayoutInflater.from(mcontext).inflate(R.layout.pay_food, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final  @NonNull MyViewHolder holder, int position) {
        final TaoCan taoCan=Global_shop_cart.taoCan_bought_list.get(position);
        holder.textView_name.setText(taoCan.getName());
        holder.textView_price.setText(taoCan.getPrice());
        holder.textView_num.setText(taoCan.getNum());
        //图片
        Request request=new Request.Builder().url(taoCan.getCover()).build();
        final OkHttpClient client=new OkHttpClient();
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
                    holder.imageView.setImageBitmap(bitmap);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return Global_shop_cart.taoCan_bought_list.size();
    }
}
