package com.example.myapplication.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.shehuan.niv.NiceImageView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private Context mcontext;
    private List<Order> list;
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView_name;
        TextView textView_time;
        TextView textView_price;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_name=itemView.findViewById(R.id.order_kj_name);
            textView_price=itemView.findViewById(R.id.order_kj_price);
            textView_time=itemView.findViewById(R.id.prder_kj_time);
            recyclerView=itemView.findViewById(R.id.order_kj_recycle);
        }
    }
    public OrderAdapter(List<Order> orders){
        list=orders;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mcontext==null){
            mcontext=parent.getContext();
        }
        View view= LayoutInflater.from(mcontext).inflate(R.layout.activity_my_order,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order=list.get(position);
        holder.textView_name.setText(order.getName());
        holder.textView_time.setText("下单时间："+order.getTime());
        holder.textView_price.setText("总价：￥"+order.getPrice());
        OrderItemAdapter adapter=new OrderItemAdapter(order.getList());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(mcontext);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
