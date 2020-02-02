package com.example.myapplication.food_shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class TaocanSpRawTextAdapter extends RecyclerView.Adapter<TaocanSpRawTextAdapter.ViewHolder> {
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView_name;
        TextView textView_weight;
        public ViewHolder(View view) {
            super(view);
            textView_name=view.findViewById(R.id.taocan_sp_raw_name);
            textView_weight=view.findViewById(R.id.taocan_sp_raw_weight);
        }
    }
    private Context mcontext;
    private List<String> list;
    public TaocanSpRawTextAdapter(List<String> list){
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mcontext==null){
            mcontext=parent.getContext();
        }
        View view= LayoutInflater.from(mcontext).inflate(R.layout.taocan_sp_raw_text,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String s=list.get(position);
        int a=s.indexOf("+");
        holder.textView_name.setText(s.substring(0,a));
        holder.textView_weight.setText(s.substring(a+1,s.length()-1));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
