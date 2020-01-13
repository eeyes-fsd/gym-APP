package com.example.myapplication.adress;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Constant;
import com.example.myapplication.R;

import java.util.List;

/*
适配器类
 */
public class AdressAdapter extends RecyclerView.Adapter<AdressAdapter.ViewHolder> {
    private List<Adress> list;
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView_name_gender;
        TextView textView_adress;
        TextView textView_tel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_adress=itemView.findViewById(R.id.adress_ar);
            textView_name_gender=itemView.findViewById(R.id.adress_name_sex);
            textView_tel=itemView.findViewById(R.id.adress_tel);
        }
    }

    public AdressAdapter(List<Adress> list) {
        this.list=list;
    }
    public AdressAdapter(){

    }
    public void setList(List<Adress> list){
        this.list=list;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Adress adress=list.get(position);
        holder.textView_tel.setText(adress.getTel());
        holder.textView_name_gender.setText(adress.getName()+" "+adress.getSex());
        holder.textView_adress.setText(adress.getAdress());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adress,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
