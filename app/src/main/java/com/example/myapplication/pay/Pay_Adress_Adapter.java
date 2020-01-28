package com.example.myapplication.pay;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Constant;
import com.example.myapplication.R;
import com.example.myapplication.adress.Adress;
import com.example.myapplication.adress.AdressAdapter;
import com.example.myapplication.adress.Change_adress_Activity;

public class Pay_Adress_Adapter extends RecyclerView.Adapter<Pay_Adress_Adapter.ViewHolder> {
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

    private Choose_AdressActivity adressActivity;

    public void setAdressActivity(Choose_AdressActivity adressActivity) {
        this.adressActivity = adressActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adress,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        final Adress adress= Constant.list.get(position);
        holder.textView_tel.setText(adress.getTel());
        holder.textView_name_gender.setText(adress.getName()+" "+adress.getSex());
        holder.textView_adress.setText(adress.getAdress());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(adressActivity,PayActivity.class);
                intent.putExtra("position",position);
                adressActivity.setResult(Activity.RESULT_OK,intent);
                adressActivity.finish();
            }
        });
    }
    @Override
    public int getItemCount() {
        return Constant.list.size();
    }
}
