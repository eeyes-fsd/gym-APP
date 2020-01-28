package com.example.myapplication.web;

import android.app.ProgressDialog;
import android.content.Context;

public class MyProgressDialog {
    public static ProgressDialog dialog=null;
    public static void CreatProgressDialog(Context context){
        dialog=new ProgressDialog(context);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("提示");
        dialog.setMessage("连接网络中，请稍等");
        dialog.show();
    }
    public static void Diss_progress_dialog(){
        dialog.dismiss();
    }
}
