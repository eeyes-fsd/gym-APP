package com.example.myapplication.order;

public class Order_Item {
    private String http_pic;
    private String name_taocan;

    public String getHttp_pic() {
        return http_pic;
    }

    public void setHttp_pic(String http_pic) {
        this.http_pic = http_pic;
    }

    public String getName_taocan() {
        return name_taocan;
    }

    public void setName_taocan(String name_taocan) {
        this.name_taocan = name_taocan;
    }

    public String getName_item() {
        return name_item;
    }

    public void setName_item(String name_item) {
        this.name_item = name_item;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    private String name_item;
    private int num;

}
