package com.example.utils;

import android.nfc.tech.IsoDep;
import android.util.Log;

public class LogUtil {
    private static boolean isDebug=true;
    private static final String TAG = "LogUtil";
    private LogUtil(){}
    public static void e(String msg){
        if(isDebug){
            Log.e(TAG,msg);
        }
    }
    public static void e(String tag,String msg){
        if(isDebug){
            Log.e(tag,msg);
        }
    }
}
