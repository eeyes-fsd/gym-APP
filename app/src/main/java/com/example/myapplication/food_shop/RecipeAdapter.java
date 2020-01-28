package com.example.myapplication.food_shop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Constant;
import com.example.myapplication.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    private Context mcontext;
    private List<TaoCan> taoCanList;
    public  PopupWindow  popupWindow;
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView_img;
        TextView textView_shicai;
        TextView textView_name;
        TextView textView_btn;
      //  Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_name=itemView.findViewById(R.id.recipe_name);//简介
            textView_shicai=itemView.findViewById(R.id.recipe_shicai);//食材
            imageView_img=itemView.findViewById(R.id.recipe_img);//图片
            textView_btn=itemView.findViewById(R.id.text_btn);
         //   button=itemView.findViewById(R.id.recipe_buy_btn);
        }
    }
    public RecipeAdapter(List<TaoCan> list){
        taoCanList=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mcontext==null){
            mcontext=parent.getContext();

        }
        View view= LayoutInflater.from(mcontext).inflate(R.layout.recipe_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final TaoCan taoCan=taoCanList.get(position);
        //holder.imageView_img.setImageResource(taoCan.getImageId());
        holder.textView_name.setText(taoCan.getName());
        holder.textView_shicai.setText(taoCan.getDescription());
        Log.d("SWB", "onBindViewHolder: "+taoCan.getHttp_pic());
        Request request=new Request.Builder().url(taoCan.getHttp_pic()).build();
        OkHttpClient client=new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //这里准备一张图片作为默认图片
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    InputStream inputStream = response.body().byteStream();//得到图片的流
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    holder.imageView_img.setImageBitmap(bitmap);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

//        StringBuilder stringBuilder=new StringBuilder(Constant.MAIN_con+" ");
//        for (Food food:taoCan.foodList){
//            stringBuilder.append(food.getName());
//            stringBuilder.append(",");
//        }
//        holder.textView_shicai.setText(stringBuilder);

        holder.textView_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View contentView = LayoutInflater.from(mcontext).inflate(R.layout.recipe_buy,null);
                popupWindow=new PopupWindow(contentView, RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setContentView(contentView);
//                final int check[] ={0,0,0,0};
                Button button=contentView.findViewById(R.id.recipe_buy_btn);
                RadioButton radioButton=contentView.findViewById(R.id.taocan_extra_1);
                radioButton.setText(taoCan.getName());
//                RadioGroup radioGroup=contentView.findViewById(R.id.recipe_radio_group);
//                final RadioButton radioButton1=contentView.findViewById(R.id.taocan_extra_1);
//                final RadioButton radioButton2=contentView.findViewById(R.id.taocan_extra_2);
//                final RadioButton radioButton3=contentView.findViewById(R.id.taocan_extra_3);
//                final RadioButton radioButton4=contentView.findViewById(R.id.taocan_extra_4);
//                radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                    }
//                });
//                radioButton2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int n=check[0]+check[1]+check[2]+check[3];
//                        if (!radioButton2.isChecked()&&n<2){//如果未被选中
//                            check[1]=1;
//                            radioButton2.setChecked(true);
//                        }else {//未被选中
//                            check[1]=0;
//                            radioButton2.setChecked(false);
//                        }
//                    }
//                });
//                radioButton3.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int n=check[0]+check[1]+check[2]+check[3];
//                        if (!radioButton3.isChecked()&&n<2){//如果未被选中
//                            check[2]=1;
//                            radioButton3.setChecked(true);
//                        }else {//未被选中
//                            check[2]=0;
//                            radioButton3.setChecked(false);
//                        }
//                    }
//                });
//                radioButton4.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int n=check[0]+check[1]+check[2]+check[3];
//                        if (!radioButton4.isChecked()&&n<2){//如果未被选中
//                            check[3]=1;
//                            radioButton4.setChecked(true);
//                        }else {//未被选中
//                            check[3]=0;
//                            radioButton4.setChecked(false);
//                        }
//                    }
//                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //int n=check[0]+check[1]+check[2]+check[3];
                        Toast.makeText(mcontext,"sadasd",Toast.LENGTH_SHORT).show();
                    }
                });

                View rootview = LayoutInflater.from(mcontext).inflate(R.layout.test, null);
                popupWindow.showAtLocation(rootview, Gravity.BOTTOM,0,0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taoCanList.size();
    }
}
