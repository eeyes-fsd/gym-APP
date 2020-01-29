package com.example.myapplication.adress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.example.myapplication.Constant;
import com.example.myapplication.R;
import com.example.myapplication.main_page.BaseActivity;
import com.lljjcoder.citypickerview.widget.CityPicker;

import java.util.ArrayList;
import java.util.List;

public class LocaionActivity extends BaseActivity implements AMapLocationListener, PoiSearch.OnPoiSearchListener{
    private final int LOCATION_REQUEST=0;
    private EditText editText;//获取地址，然后返回
    private ListView listView;
    private List<POI> poiList=new ArrayList<>();
    private POIAdapter adapter;
    private AMapLocationClient mlocationClient=null;
    private AMapLocationClientOption mLocationOption=null;
    private PoiSearch.Query query;
    private PoiSearch poiSearch;
    private String city="北京";
    private TextView textView_city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locaion);
        request_location_permission();//请求权限
        textView_city=findViewById(R.id.text_city);
        textView_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityPicker cityPicker = new CityPicker.Builder(LocaionActivity.this)
                        .textSize(14)
                        .title("地址选择")
                        .titleBackgroundColor("#FFFFFF")
                        .confirTextColor("#696969")
                        .cancelTextColor("#696969")
                        .province("江苏省")
                        .city("常州市")
                        .district("天宁区")
                        .provinceCyclic(true)
                        .cityCyclic(false)
                        .districtCyclic(false)
                        .visibleItemsCount(7)
                        .itemPadding(10)
                        .onlyShowProvinceAndCity(false)
                        .build();
                cityPicker.show();
                cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
                    @Override
                    public void onSelected(String... citySelected) {
                        String district = citySelected[2];
                        textView_city.setText(district.trim());
                        city=district;
                        query=new PoiSearch.Query(editText.getText().toString(),"",city);
                        poiSearch=new PoiSearch(LocaionActivity.this,query);
                        poiSearch.setOnPoiSearchListener(LocaionActivity.this);
                        poiSearch.searchPOIAsyn();//开始搜索
                    }
                });
            }
        });
        editText=findViewById(R.id.loc_input);
        listView=findViewById(R.id.loc_near);
        Init_loc();//获取地址
        adapter=new POIAdapter(this,R.layout.loc_near,poiList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(LocaionActivity.this,New_Adress_Activity.class);
                intent.putExtra("address",poiList.get(position).getAdress());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
                query=new PoiSearch.Query(s.toString(),"",city);
                poiSearch=new PoiSearch(LocaionActivity.this,query);
                poiSearch.setOnPoiSearchListener(LocaionActivity.this);
                poiSearch.searchPOIAsyn();//开始搜索
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    void Init_loc(){
        try {
            mlocationClient=new AMapLocationClient(this);
            mLocationOption=new AMapLocationClientOption();
            mlocationClient.setLocationListener(this);
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mLocationOption.setInterval(5000);
            mlocationClient.setLocationOption(mLocationOption);
            mlocationClient.startLocation();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation!=null){
            if (amapLocation.getErrorCode()==0){
//                Log.i("定位类型", amapLocation.getLocationType() + "");
//                Log.i("获取纬度", amapLocation.getLatitude() + "");
//                Log.i("获取经度", amapLocation.getLongitude() + "");
//                Log.i("获取精度信息", amapLocation.getAccuracy() + "");
//                //如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                Log.i("地址", amapLocation.getAddress());
//                Log.i("国家信息", amapLocation.getCountry());
//                Log.i("省信息", amapLocation.getProvince());
//                Log.i("城市信息", amapLocation.getCity());
//                Log.i("城区信息", amapLocation.getDistrict());
//                Log.i("街道信息", amapLocation.getStreet());
//                Log.i("街道门牌号信息", amapLocation.getStreetNum());
//                Log.i("城市编码", amapLocation.getCityCode());
//                Log.i("地区编码", amapLocation.getAdCode());
//                Log.i("获取当前定位点的AOI信息", amapLocation.getAoiName());
//                Log.i("获取当前室内定位的建筑物Id", amapLocation.getBuildingId());
//                Log.i("获取当前室内定位的楼层", amapLocation.getFloor());
//                Log.i("获取GPS的当前状态", amapLocation.getGpsAccuracyStatus() + "");
//                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date date=new Date(amapLocation.getTime());
//                Log.i("获取定位时间", simpleDateFormat.format(date));
                String s=amapLocation.getCity()+amapLocation.getDistrict()+amapLocation.getStreet()+amapLocation.getStreetNum();
                city=amapLocation.getCity();
                textView_city.setText(city);
                editText.setText(s);
                mlocationClient.stopLocation();
            }else {
                Log.e("定位错误！！！！！！！！！！", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
                editText.setText("定位失败，请检查您的网络状况和位置服务是否开启");
                mlocationClient.stopLocation();
            }
        }
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        List<PoiItem> poiItems=poiResult.getPois();
        poiList.clear();
        for (PoiItem poiItem:poiItems){
            POI poi=new POI(poiItem.getTitle(),poiItem.getCityName()+poiItem.getAdName()+poiItem.getDirection()+poiItem.getSnippet());
            poiList.add(poi);
        }
        city=poiItems.get(0).getAdName();
        textView_city.setText(city);
        adapter.notifyDataSetChanged();
//        poiItems.get(1).getSnippet();//
//        poiItems.get(1).getTitle();//名称
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    void request_location_permission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            //当没有权限时
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_REQUEST);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case LOCATION_REQUEST:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                }else{
                    Toast.makeText(this,"授权失败,无法使用定位服务",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onStop() {
        if (mlocationClient!=null){
            mlocationClient.stopLocation();
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (mlocationClient!=null){
            mlocationClient.onDestroy();
            mlocationClient=null;
        }
        super.onDestroy();
    }
}
