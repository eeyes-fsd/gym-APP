package com.example.myapplication.order;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.shehuan.niv.NiceImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {
    private Context mcontext;
    private List<Order_Item> list;
    public class ViewHolder extends RecyclerView.ViewHolder{
        NiceImageView niceImageView;
        TextView textView_name_taocan;
        TextView textView_name__food_num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            niceImageView=itemView.findViewById(R.id.order_content_pic);
            textView_name_taocan=itemView.findViewById(R.id.order_content_name_taocan);
            textView_name__food_num=itemView.findViewById(R.id.order_content_name_food_num);
        }
    }
    public OrderItemAdapter(List<Order_Item> order_items){
        list=order_items;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mcontext==null){
            mcontext=parent.getContext();
        }
        View view= LayoutInflater.from(mcontext).inflate(R.layout.order_content,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Order_Item order_item=list.get(position);
        holder.textView_name_taocan.setText(order_item.getName_taocan());
        holder.textView_name__food_num.setText(order_item.getName_item()+"*"+order_item.getNum());
        Request request=new Request.Builder().url(order_item.getHttp_pic()).build();
        final OkHttpClient client=new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //这里准备一张图片作为默认图片
                //
                //holder.niceImageView.setImageBitmap(bitmap);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    InputStream inputStream = response.body().byteStream();//得到图片的流
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    holder.niceImageView.setImageBitmap(bitmap);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
