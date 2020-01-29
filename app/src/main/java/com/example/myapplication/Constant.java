package com.example.myapplication;

import com.example.myapplication.adress.Adress;

import java.util.ArrayList;

public class Constant {
    public static final int NEW_ADRESS = 0;
    public static final int CHANGE_ADRESS = 1;
    public static final int REQUEST_INTERNET_CODE = 2;
    public static final String TAOCAN_NAME = "taocan_name";
    public static final String TAOCAN_IMAGE = "taocan_image";
    public static final String MAIN_con = "主要食材";
    public static ArrayList<Adress> list = new ArrayList<>();//地址列表
    public static final int CHOOSE_ADRESS = 3;
    public static final int CHOOES_LOCATION = 4;
    public static final String GaoDeApi_part_one = "https://restapi.amap.com/v3/geocode/geo?address=";//再这两个中间加上地址，申请反解析
    public static final String GaoDeApi_part_two = "&output=XML&key=8526e0f938c19daeb77163797eba2be9";
}
