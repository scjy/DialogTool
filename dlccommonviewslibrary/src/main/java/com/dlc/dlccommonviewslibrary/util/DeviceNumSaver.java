package com.dlc.dlccommonviewslibrary.util;

import android.content.Context;
import android.content.SharedPreferences;

public class DeviceNumSaver {
    private final static String SAVER_CONFIG = "com.dlc.dlccommonviewslibrary_DeviceNumSaver";

    public static void saveDeviceNum(Context context, String deviceNum) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SAVER_CONFIG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("deviceNum",deviceNum);
        editor.apply();
    }

    public static String getDeviceNum(Context context){
        return context.getSharedPreferences(SAVER_CONFIG, Context.MODE_PRIVATE).getString("deviceNum",null);
    }
}
