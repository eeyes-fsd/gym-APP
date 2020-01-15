package com.example.myapplication.food_shop;

import android.content.Context;
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
import com.example.myapplication.R;

import java.util.List;

public class TaoCanAdapter extends RecyclerView.Adapter<TaoCanAdapter.ViewHolder> {
    private Context mcontext;
    private List<TaoCan> mTaoCanlist;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView textView;
        ImageView imageView;
        Button button;
        View mview;
        public ViewHolder(View view) {
            super(view);
            cardView=(CardView) view;
            textView=view.findViewById(R.id.taocan_name);
            imageView=view.findViewById(R.id.taocan_pic);
            button=view.findViewById(R.id.taocan_add);
            mview=view;
        }
    }

    public TaoCanAdapter(List<TaoCan> mTaoCanlist) {
        this.mTaoCanlist = mTaoCanlist;
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
                Toast.makeText(v.getContext(),viewHolder.textView.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mTaoCanlist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaoCan taoCan=mTaoCanlist.get(position);
        holder.textView.setText(taoCan.getName());
        Glide.with(mcontext).load(taoCan.getImageId()).into(holder.imageView);

    }
}
