package com.example.myapplication.home_page;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adress.Adress;
import com.example.myapplication.food_shop.Food;
import com.example.myapplication.food_shop.Global_shop_cart;
import com.example.myapplication.food_shop.Shop_cart_adapter;
import com.example.myapplication.food_shop.TaoCan;
import com.example.myapplication.food_shop.TaoCanAdapter;
import com.example.myapplication.web.MyProgressDialog;
import com.example.myapplication.web.Token;
import com.example.myapplication.web.WebService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Fra_food extends Fragment implements View.OnClickListener{
    private TextView textView_csd;
    private TextView textView_cst;
    private TaoCan[] taoCans={new TaoCan(R.drawable.pic_test_1,"套餐一"),new TaoCan(R.drawable.pic_text_2,"套餐二"),new TaoCan(R.drawable.pic_test_3,"套三")
            ,new TaoCan(R.drawable.round_pic,"套餐四")};
//    private List<TaoCan> taoCanList=new ArrayList<>();
    private TaoCanAdapter adapter;
    private PopupWindow popupWindow=null;
    private View rootview;
    private View contentView;
    private ExpandableListView expandableListView;
    private EditText editText;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fra_food,container,false);
        contentView = LayoutInflater.from(getActivity()).inflate(R.layout.shop_cart,null);
        expandableListView=contentView.findViewById(R.id.shop_cart_elv);
        popupWindow=new PopupWindow(contentView, RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(contentView);
        editText=view.findViewById(R.id.fra_food_edit);
        rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_taocan_sp_, null);
        MainActivity.MyTouchListener myTouchListener=new MainActivity.MyTouchListener() {
            @Override
            public void onTouchEvent(MotionEvent event) {
                    if (popupWindow.isShowing()&&popupWindow!=null){
                        popupWindow.dismiss();
                    }
            }
        };
        ((MainActivity)this.getActivity()).registerMyTouchListener(myTouchListener);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Init_1();//初始化套餐
        Init_2();//初始化套餐的内容
        RecyclerView recyclerView=getView().findViewById(R.id.taocan_recycle);
        GridLayoutManager manager=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(manager);
        adapter=new TaoCanAdapter();
        recyclerView.setAdapter(adapter);
        textView_csd=getView().findViewById(R.id.text_csd);
        textView_csd.setOnClickListener(this);
        textView_cst=getView().findViewById(R.id.text_cst);
        textView_cst.setOnClickListener(this);
        FloatingActionButton floatingActionButton= getView().findViewById(R.id.shop_cart);
        floatingActionButton.setOnClickListener(this);
        super.onActivityCreated(savedInstanceState);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shop_cart:
                Shop_cart_adapter shop_cart_adapter=new Shop_cart_adapter(getActivity());
                expandableListView.setAdapter(shop_cart_adapter);
                for (int i=0;i<Global_shop_cart.taoCan_bought_list.size();i++){
                    expandableListView.expandGroup(i);
                }
                popupWindow.showAtLocation(rootview, Gravity.BOTTOM,0,0);
                break;
            case R.id.text_csd:
                textView_cst.setTextColor(getResources().getColor(R.color.food_gray));
                textView_csd.setTextColor(getResources().getColor(R.color.food_black));
                break;
            case R.id.text_cst:
                textView_csd.setTextColor(getResources().getColor(R.color.food_gray));
                textView_cst.setTextColor(getResources().getColor(R.color.food_black));
                break;
        }
    }
    private void Init(){//初始化套餐
        if(Global_shop_cart.taoCanlist.size()!=0){
            return;
        }
        Call call= WebService.GYM_call("/diets", Token.access_token,"GET",null);
        MyProgressDialog.CreatProgressDialog(getActivity());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.Diss_progress_dialog();
                Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                MyProgressDialog.Diss_progress_dialog();
                String s=response.body().string();
                ParseJson(s);
            }
        });
    }
    private void ParseJson(String s){
        TaoCan[] taoCans=new Gson().fromJson(s,TaoCan[].class);
        Global_shop_cart.taoCanlist.addAll(Arrays.asList(taoCans));
        for (int i = 0; i <taoCans.length ; i++) {
            Global_shop_cart.foodlist.add(taoCans[i].foodList);
        }
        adapter.notifyDataSetChanged();
//        Adress[] adress=new Gson().fromJson(str,Adress[].class);

    }
    private void Init_2(){
        if (Global_shop_cart.taoCanlist.isEmpty()){
            for(int i=0;i<10;i++){
                Random random=new Random();
                int index=random.nextInt(taoCans.length);
                Global_shop_cart.taoCanlist.add(taoCans[index]);
            }
        }
    }
    private void Init_1(){
        taoCans[0].foodList.add(new Food(10,"1猪肉"));
        taoCans[0].foodList.add(new Food(10,"1牛肉"));
        taoCans[1].foodList.add(new Food(5,"2胡萝卜"));
        taoCans[1].foodList.add(new Food(12,"2白菜"));
        taoCans[2].foodList.add(new Food(17,"3猪肉"));
        taoCans[2].foodList.add(new Food(16,"3牛肉"));
        taoCans[3].foodList.add(new Food(13,"4猪肉"));
        taoCans[3].foodList.add(new Food(5,"4牛肉"));
    }
    void search(String s){
        s=editText.getText().toString();

    }

}
