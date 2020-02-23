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
import android.widget.CheckBox;
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
import com.example.myapplication.adress.Change_adress_Activity;

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
        TextView textView_desc;
        TextView textView_btn;
      //  Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_name=itemView.findViewById(R.id.recipe_name);
            textView_desc=itemView.findViewById(R.id.recipe_desc);//简介
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
        holder.textView_desc.setText(taoCan.getDescription());

        Request request=new Request.Builder().url(taoCan.getCover()).build();
        final OkHttpClient client=new OkHttpClient();
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
                checkBoxes[0]=contentView.findViewById(R.id.taocan_extra_1);
                checkBoxes[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkBoxes[0].isChecked()){
                            count++;
                            array[0]=1;
                            check_1();
                        }else {
                            count--;
                            array[0]=0;
                            check_2();
                        }
                    }
                });
                checkBoxes[1]=contentView.findViewById(R.id.taocan_extra_2);
                checkBoxes[1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkBoxes[1].isChecked()){
                            count++;
                            array[1]=1;
                            check_1();
                        }else {
                            count--;
                            array[1]=0;
                            check_2();
                        }
                    }
                });
                checkBoxes[2]=contentView.findViewById(R.id.taocan_extra_3);
                checkBoxes[2].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkBoxes[2].isChecked()){
                            count++;
                            array[2]=1;
                            check_1();
                        }else {
                            count--;
                            array[2]=0;
                            check_2();
                        }
                    }
                });
                checkBoxes[3]=contentView.findViewById(R.id.taocan_extra_4);
                checkBoxes[3].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkBoxes[3].isChecked()){
                            count++;
                            array[3]=1;
                            check_1();
                        }else {
                            count--;
                            array[3]=0;
                            check_2();
                        }
                    }
                });
                Button button=contentView.findViewById(R.id.recipe_buy_btn);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mcontext,"sadasd",Toast.LENGTH_SHORT).show();
                    }
                });
                View rootview = LayoutInflater.from(mcontext).inflate(R.layout.activity_recipe, null);
                popupWindow.showAtLocation(rootview, Gravity.BOTTOM,0,0);
            }
        });
    }
    int count=0;
    private CheckBox[] checkBoxes=new CheckBox[4];
    private int[] array={0,0,0,0};
    void check_1(){
        if (count==2){//有两个被点击时，取消另外两个的点击
            for (int i=0;i<array.length;i++){
                if (array[i]==0){
                    checkBoxes[i].setClickable(false);
                }
            }
        }
    }
    void check_2(){
        if (count==1){//如果点击取消后有一个为1，则都可点击
            for (CheckBox checkBox:checkBoxes){
                checkBox.setClickable(true);
            }
        }
    }
    @Override
    public int getItemCount() {
        return taoCanList.size();
    }
}
