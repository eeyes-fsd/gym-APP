package com.example.myapplication.food_shop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Constant;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.util.List;

public class TaoCanAdapter extends RecyclerView.Adapter<TaoCanAdapter.ViewHolder> {
    private Context mcontext;
//    private List<TaoCan> mTaoCanlist;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        Button button;
        View mview;
        public ViewHolder(View view) {
            super(view);
            textView=view.findViewById(R.id.taocan_name);
            imageView=view.findViewById(R.id.taocan_pic);
            button=view.findViewById(R.id.taocan_add);
            mview=view;
        }
    }

    public TaoCanAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//在这里加点击事件
        if (mcontext==null){
            mcontext=parent.getContext();
        }
        View view= LayoutInflater.from(mcontext).inflate(R.layout.food_specific,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(),viewHolder.textView.getText(),Toast.LENGTH_SHORT).show();
                int postion=viewHolder.getAdapterPosition();
                TaoCan taoCan=Global_shop_cart.taoCanlist.get(postion);
                //更改要同时两个东西，达到对应关系  这里是添加键
                Global_shop_cart.bought_add(taoCan);
                try {
                    Toast.makeText(v.getContext(),Global_shop_cart.taoCanlist.get(Global_shop_cart.taoCanlist.size()-1).getName(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int postion=viewHolder.getAdapterPosition();
//                TaoCan taoCan=Global_shop_cart.taoCanlist.get(postion);
                Intent intent=new Intent(mcontext,Taocan_sp_Activity.class);
                intent.putExtra("position",postion);
//                intent.putExtra(Constant.TAOCAN_NAME,taoCan.getName());
//                intent.putExtra(Constant.TAOCAN_IMAGE,taoCan.getImageId());
                mcontext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return Global_shop_cart.taoCanlist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        TaoCan taoCan=Global_shop_cart.taoCanlist.get(position);
        holder.textView.setText(taoCan.getName());
        Glide.with(mcontext).load(taoCan.getImageId()).into(holder.imageView);
    }
}
