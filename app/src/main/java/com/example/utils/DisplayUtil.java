package com.example.utils;

import android.util.TypedValue;

import com.example.myrecyclerviewtest.MyApplication;

public class DisplayUtil {
    private DisplayUtil(){}
    public static int dp2px(float dpValue){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpValue,
                MyApplication.getContext().getResources().getDisplayMetrics());
    }
    public static int sp2px(float spValue){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,spValue,
                MyApplication.getContext().getResources().getDisplayMetrics());
    }
}
