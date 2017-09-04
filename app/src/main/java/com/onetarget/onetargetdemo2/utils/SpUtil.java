package com.onetarget.onetargetdemo2.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.onetarget.onetargetdemo2.mvp.QuickApplication;

/**
 *
 */
public class SpUtil {
    static final String SP_NAME = "settings";
    Context mContext;
    private  static  SpUtil instance;

    public static SpUtil getInstance() {
        if (instance == null){
            synchronized (SpUtil.class){
                if (instance == null){
                    instance = new SpUtil(QuickApplication.getInstance());
                }
            }
        }
        return instance;
    }

    private SpUtil(Context context) {
        this.mContext = context;
    }

    public boolean getBoolean(String key, boolean value) {
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, value);
    }

    public void saveBoolean(String key, boolean value) {
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor et = sp.edit();
        et.putBoolean(key, value);
        et.commit();
    }

    public int getInt(String key, int value) {
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, value);
    }

    public void saveInt(String key, int value) {
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor et = sp.edit();
        et.putInt(key, value);
        et.commit();
    }
    public long getLong(String key, long value) {
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key, value);
    }

    public void saveLong(String key, long value) {
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor et = sp.edit();
        et.putLong(key, value);
        et.commit();
    }

    public String getString(String key, String value) {
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, value);
    }

    public void saveString(String key, String value) {
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor et = sp.edit();
        et.putString(key, value);
        et.commit();
    }
    /***
     *  移除sp中的某个值
     */
    public void  removeKey(String key){
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor et = sp.edit();
        et.remove(key);
        et.commit();
    }
}
