package com.example.myapplication.adress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;
import java.util.zip.Inflater;

public class POIAdapter extends ArrayAdapter<POI> {
    private int resId;

    public POIAdapter(Context context, int resource, List<POI> objects) {
        super(context, resource, objects);
        resId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        POI poi=getItem(position);
        View v;
        ViewHolder viewHolder;
        if (convertView==null){
            v= LayoutInflater.from(getContext()).inflate(resId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.textView_name=v.findViewById(R.id.loc_near_name);
            viewHolder.textView_adress=v.findViewById(R.id.loc_near_adress);
            v.setTag(viewHolder);
        }else {
            v=convertView;
            viewHolder=(ViewHolder) v.getTag();
        }
        viewHolder.textView_name.setText(poi.getName());
        viewHolder.textView_adress.setText(poi.getAdress());
        return v;
    }
    class ViewHolder{
        TextView textView_name;
        TextView textView_adress;
    }
}
