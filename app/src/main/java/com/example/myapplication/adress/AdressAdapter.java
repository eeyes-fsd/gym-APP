package com.example.myapplication.adress;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Constant;
import com.example.myapplication.R;

import java.util.List;

/*
适配器类
 */
public class AdressAdapter extends RecyclerView.Adapter<AdressAdapter.ViewHolder> {

    public void setAdressActivity(AdressActivity adressActivity) {
        this.adressActivity = adressActivity;
    }
    private AdressActivity adressActivity;
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView_name_gender;
        TextView textView_adress;
        TextView textView_tel;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_adress=itemView.findViewById(R.id.adress_ar);
            textView_name_gender=itemView.findViewById(R.id.adress_name_sex);
            textView_tel=itemView.findViewById(R.id.adress_tel);
            linearLayout=itemView.findViewById(R.id.ll_adress);
        }
    }

    public AdressAdapter(){
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Adress adress=Constant.list.get(position);
        holder.textView_tel.setText(adress.getPhone());
        holder.textView_name_gender.setText(adress.getName()+" "+adress.getGender());
        holder.textView_adress.setText(adress.getDetails());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),Change_adress_Activity.class);
                intent.putExtra("address",adress.getDetails());
                intent.putExtra("name",adress.getName());
                intent.putExtra("phone",adress.getPhone());
                intent.putExtra("gender",adress.getGender());
                intent.putExtra("position",position);
                adressActivity.startActivityForResult(intent,Constant.CHANGE_ADRESS);
                adressActivity.finish();
            }
        });
        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Constant.list.remove(position);
                adressActivity.adapter.notifyDataSetChanged();
                adressActivity.delete_adress();
                return true;
            }
        });
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
        return Constant.list.size();
    }
}
