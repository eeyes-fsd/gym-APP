package com.example.myapplication.adress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.main_page.BaseActivity;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.map.geolocation.TencentPoi;

import java.util.ArrayList;
import java.util.List;

public class LocaionActivity extends BaseActivity implements TencentLocationListener{
    private TencentLocationListener locationListener;
    private TencentLocationManager locationManager;
    private final int LOCATION_REQUEST=0;
    private EditText editText;//获取地址，然后返回
    private ListView listView;
    private List<POI> poiList=new ArrayList<>();
    private POIAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locaion);
        request_location_permission();
        TencentLocationRequest request=TencentLocationRequest.create();
        request.setInterval(TencentLocationRequest.REQUEST_LEVEL_POI);
        request.setInterval(1000*10);
        request.setAllowCache(true);
        editText=findViewById(R.id.loc_input);
        listView=findViewById(R.id.loc_near);
        //包含经纬度，位置所处的中国大陆行政区划及周边POI列表
        //latitude	纬度
        //longitude	经度
        //altitude	海拔
        //accuracy	精度
        //nation	国家
        //province	省
        //city	市
        //district	区
        //town	镇
        //village	村
        //street	街道
        //streetNo	门号
        // poiList	POI 列表
        locationListener =this;
        locationManager=TencentLocationManager.getInstance(this);
        int error=locationManager.requestLocationUpdates(request,locationListener);//监听器注册是否成功的返回码
        if (error!=0){
            Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
        }
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editText.setText(poiList.get(position).getAdress());
            }
        });
    }

    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
        if (tencentLocation != null){
            if (i==TencentLocation.ERROR_OK){
                Toast.makeText(this,tencentLocation.getAddress(),Toast.LENGTH_SHORT).show();
                List<TencentPoi> list=tencentLocation.getPoiList();
                for (int i1=0;i<list.size();i++){
                    poiList.add(new POI(list.get(i1).getName(),list.get(i1).getAddress()));
                }
                adapter=new POIAdapter(this,R.layout.loc_near,poiList);
                adapter.notifyDataSetChanged();
                //String stringBuilder=tencentLocation.getAddress();
            }
//            else {
//               // Toast.makeText(this,Integer.toString(i),Toast.LENGTH_SHORT).show();
//            }
        }
//        else {
//           // Toast.makeText(this,"aaa",Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {
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
    protected void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(locationListener);
    }
}
