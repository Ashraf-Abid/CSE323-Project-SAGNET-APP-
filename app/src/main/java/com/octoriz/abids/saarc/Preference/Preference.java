package com.octoriz.abids.saarc.Preference;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class Preference {

    public static void setArrayPrefs(String arrayName, ArrayList<String> array, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.size());
        for(int i=0;i<array.size();i++)
            editor.putString(arrayName + "_" + i, array.get(i));
        editor.apply();
    }

    public static ArrayList<String> getArrayPrefs(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        ArrayList<String> array = new ArrayList<>(size);
        for(int i=0;i<size;i++)
            array.add(prefs.getString(arrayName + "_" + i, null));
        return array;
    }

    public static void setPrefs(String key, String value, Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences("preferencenameV", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getPrefs(String key, Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences("preferencenameV", Context.MODE_PRIVATE);
        return sharedpreferences.getString(key, "notfound");
    }

    public static void deleteSharedPref(Context context){
        SharedPreferences settings = context.getSharedPreferences("preferencename", 0);
        SharedPreferences settings2 = context.getSharedPreferences("preferencenameV", 0);
        settings.edit().clear().apply();
        settings2.edit().clear().apply();
    }
}
