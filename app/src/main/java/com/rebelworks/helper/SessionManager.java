package com.rebelworks.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SessionManager {

    String keyToken = "token";

    private static SharedPreferences getPref(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void putToken(Context context, String value) {
        putString(context, "token", value);
    }

    public static String getToken(Context context, String value) {
        return getToken( context, value);
    }

    public static void putString(Context context, String key, String value) {
        getPref(context).edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key){
        return getPref(context).getString(key, "");
    }

    public static void putInt(Context context, String key, int value) {
        getPref(context).edit().putInt(key, value).apply();
    }

    public static String getInt(Context context, String key){
        return getPref(context).getString(key, null);
    }



}
