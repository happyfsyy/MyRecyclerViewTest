package com.example.utils;

import android.widget.Toast;

import com.example.myrecyclerviewtest.MyApplication;

public class ToastUtils {
    private static Toast toast;
    public static void showToast(String msg){
        if(toast==null){
            toast=Toast.makeText(MyApplication.getContext(),msg,Toast.LENGTH_SHORT);
        }else{
            toast.setText(msg);
        }
        toast.show();
    }
}

