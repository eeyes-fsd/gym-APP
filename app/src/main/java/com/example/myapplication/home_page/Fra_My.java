package com.example.myapplication.home_page;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.Test_Hight_WeightActivity;
import com.example.myapplication.adress.AdressActivity;
import com.example.myapplication.main_page.Un_sign_inActivity;

public class Fra_My extends Fragment implements View.OnClickListener{
    private TextView textView;
    private TextView textView_adress;
    private TextView textView_exit;
    private TextView textView_connect_us;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fra_my,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView=getActivity().findViewById(R.id.my_hight_weight);
        textView_adress=getActivity().findViewById(R.id.my_adress);
        textView_adress.setOnClickListener(this);
        textView.setOnClickListener(this);
        textView_connect_us=getActivity().findViewById(R.id.my_connect_us);
        textView_connect_us.setOnClickListener(this);
        textView_exit=getActivity().findViewById(R.id.exit);
        textView_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_adress:
                Intent intent_adress=new Intent(getActivity(), AdressActivity.class);
                startActivity(intent_adress);
                break;
            case R.id.my_hight_weight:
                Intent intent=new Intent(getActivity(), Test_Hight_WeightActivity.class);
                startActivity(intent);
                break;
            case R.id.exit:
                SharedPreferences sharedPreferences=getActivity().getSharedPreferences("token",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putInt("token",0);
                editor.apply();
                Intent intent1=new Intent(getActivity(),Un_sign_inActivity.class);
                startActivity(intent1);
                getActivity().finish();
                break;
            case R.id.my_connect_us:
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setView(R.layout.connect_us);
                builder.setPositiveButton("确定",null);
                builder.show();
                break;
        }
    }
}
