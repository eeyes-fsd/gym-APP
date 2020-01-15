package com.example.myapplication.home_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.food_shop.TaoCan;
import com.example.myapplication.food_shop.TaoCanAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fra_food extends Fragment {
    private TaoCan[] taoCans={new TaoCan(R.drawable.pic_test_1,"套餐一"),new TaoCan(R.drawable.pic_text_2,"套餐二"),new TaoCan(R.drawable.pic_test_3,"套三")
    ,new TaoCan(R.drawable.round_pic,"套餐四")};
    private List<TaoCan> taoCanList=new ArrayList<>();
    private TaoCanAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fra_food,container,false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Init();
        RecyclerView recyclerView=getView().findViewById(R.id.taocan_recycle);
        GridLayoutManager manager=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(manager);
        adapter=new TaoCanAdapter(taoCanList);
        recyclerView.setAdapter(adapter);
        super.onActivityCreated(savedInstanceState);
    }

    private void Init(){
        taoCanList.clear();
        for(int i=0;i<50;i++){
            Random random=new Random();
            int index=random.nextInt(taoCans.length);
            taoCanList.add(taoCans[index]);
        }
    }
}
