package com.example.myapplication.pay;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.food_shop.Global_shop_cart;
import com.example.myapplication.food_shop.TaoCan;

public class Pay_good_adapter extends RecyclerView.Adapter<Pay_good_adapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView_name;
        TextView textView_num;
        TextView textView_price;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_name=itemView.findViewById(R.id.pay_item_name);
            textView_num=itemView.findViewById(R.id.pay_item_num);
            textView_price=itemView.findViewById(R.id.pay_item_price);
            itemView=itemView.findViewById(R.id.pay_item_pic);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.pay_food,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaoCan taoCan=Global_shop_cart.taoCan_bought_list.get(position);
        holder.textView_name.setText(taoCan.getName());
        holder.textView_num.setText(taoCan.getNum());
        holder.textView_price.setText("￥"+taoCan.getPrice()*taoCan.getNum());
        holder.imageView.setImageResource(taoCan.getImageId());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
