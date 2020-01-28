package com.example.myapplication.food_shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.List;
//这里直接拿的全局变量
public class Shop_cart_adapter extends BaseExpandableListAdapter {
    private Context mcontext;
    public Shop_cart_adapter(Context mcontext) {
        this.mcontext = mcontext;
    }

     static class TaocanHolder{
        private TextView taocan_name;
        private TextView taocan_num;
        private Button taocan_add;
        private Button taocan_sub;
        public TaocanHolder(View view) {
            taocan_name=view.findViewById(R.id.taocan_shop_cart_name);
            taocan_num=view.findViewById(R.id.tv_taocan_num);
            taocan_add=view.findViewById(R.id.btn_taocan_add);
            taocan_sub=view.findViewById(R.id.btn_taocan_sub);
        }
    }

    static class FoodHolder{
        private TextView nameText;
        private TextView priceText;
        private Button button_add;
        private Button button_sub;
        private TextView numText;
        public FoodHolder(View view) {
            nameText=view.findViewById(R.id.food_shop_cart_name);
            priceText=view.findViewById(R.id.food_shop_cart_price);
            button_add=view.findViewById(R.id.shop_cart_btn_add);
            button_sub=view.findViewById(R.id.shop_cart_btn_sub);
            numText=view.findViewById(R.id.shop_cart_num);
        }
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        return Global_shop_cart.foodlist.get(groupPosition).size();
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(mcontext).inflate(R.layout.taocan_shop_cart,parent,false);
        }

        final TaocanHolder taocanHolder=new TaocanHolder(convertView);
        final int num=Global_shop_cart.taoCanlist.get(groupPosition).getNum();
        taocanHolder.taocan_name.setText(Global_shop_cart.taoCanlist.get(groupPosition).getName());
        taocanHolder.taocan_num.setText(Integer.toString(num));
        taocanHolder.taocan_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int num=Global_shop_cart.taoCanlist.get(groupPosition).getNum();
//                Global_shop_cart.taoCanlist.get(groupPosition).setNum(num+1);
//                Toast.makeText(v.getContext(),Integer.toString(Global_shop_cart.taoCanlist.get(groupPosition).getNum()),Toast.LENGTH_SHORT).show();
//                taocanHolder.taocan_num.setText(Integer.toString(Global_shop_cart.taoCanlist.get(groupPosition).getNum()));
                Global_shop_cart.bought_add(groupPosition);
                notifyDataSetChanged();
            }
        });
        taocanHolder.taocan_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int num=Global_shop_cart.taoCanlist.get(groupPosition).getNum();
//                Global_shop_cart.taoCanlist.get(groupPosition).setNum(num-1);
//                Toast.makeText(v.getContext(),Integer.toString(Global_shop_cart.taoCanlist.get(groupPosition).getNum()),Toast.LENGTH_SHORT).show();
//                taocanHolder.taocan_num.setText(Integer.toString(Global_shop_cart.taoCanlist.get(groupPosition).getNum()));
                Global_shop_cart.bought_sub(groupPosition);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final FoodHolder foodHolder;
        if(convertView==null) {
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.food_shop_cart, parent, false);
            foodHolder=new FoodHolder(convertView);
            convertView.setTag(foodHolder);
        }else {
            foodHolder=(FoodHolder) convertView.getTag();
        }
            final int num=Global_shop_cart.foodlist.get(groupPosition).get(childPosition).getNum();
            foodHolder.numText.setText(Integer.toString(Global_shop_cart.foodlist.get(groupPosition).get(childPosition).getNum()));
            foodHolder.priceText.setText(Integer.toString(Global_shop_cart.foodlist.get(groupPosition).get(childPosition).getPrice()));
            foodHolder.nameText.setText(Global_shop_cart.foodlist.get(groupPosition).get(childPosition).getName());
            foodHolder.button_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num=Global_shop_cart.foodlist.get(groupPosition).get(childPosition).getNum();
                    Global_shop_cart.foodlist.get(groupPosition).get(childPosition).setNum(num+1);//更改全局变量
                    foodHolder.numText.setText(Integer.toString(Global_shop_cart.foodlist.get(groupPosition).get(childPosition).getNum()));
                    notifyDataSetChanged();
                }
            });
            foodHolder.button_sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (num>=1) {
                        int num=Global_shop_cart.foodlist.get(groupPosition).get(childPosition).getNum();
                        Global_shop_cart.foodlist.get(groupPosition).get(childPosition).setNum(num-1);//更改全局变量
                        foodHolder.numText.setText(Integer.toString(Global_shop_cart.foodlist.get(groupPosition).get(childPosition).getNum()));
                        notifyDataSetChanged();
                    }
                }
            });

        return convertView;
    }
    @Override
    public Object getGroup(int groupPosition) {
        return Global_shop_cart.taoCanlist.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return Global_shop_cart.foodlist.get(groupPosition).get(childPosition);
    }

    @Override
    public int getGroupCount() {
        return Global_shop_cart.taoCanlist.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
