package com.example.myapplication.food_shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.shehuan.niv.NiceImageView;

import java.util.List;

public class TaocanSpFoodPicAdapter extends RecyclerView.Adapter<TaocanSpFoodPicAdapter.ViewHolder> {
    private Context mcontext;
    private List<Integer> list;
    static class ViewHolder extends RecyclerView.ViewHolder{
        NiceImageView niceImageView;
        public ViewHolder(View view) {
            super(view);
            niceImageView=view.findViewById(R.id.taocan_sp_food_pic);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mcontext==null){
            mcontext=parent.getContext();
        }
        View view= LayoutInflater.from(mcontext).inflate(R.layout.taocan_sp_food_pic,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Integer integer=list.get(position);
        holder.niceImageView.setImageResource(integer);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
