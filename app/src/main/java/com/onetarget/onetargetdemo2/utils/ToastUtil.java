package com.onetarget.onetargetdemo2.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtil {
    private ToastUtil() {
    }

    private static String mOldMsg;
    private static Toast mToast;
    private static long mFirstTime;
    private static long mSecondTime;

    public static void showToast(Context context, String text) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.show();
            mFirstTime = System.currentTimeMillis();
        } else {
            mSecondTime = System.currentTimeMillis();
            if (text.equals(mOldMsg)) {
                if (mSecondTime - mFirstTime > 500L) {
                    mToast.show();
                }
            } else {
                mOldMsg = text;
                mToast.setText(text);
                mToast.show();
            }
        }
        mFirstTime = mSecondTime;
    }


    public static void showToast(Context context, int resId) {
        showToast(context, context.getString(resId));
    }

    /**
     * 释放静态变量
     */
    public static  void  releaseToast() {
        if (mToast != null)
            mToast = null;
        if (mOldMsg != null)
            mOldMsg = null;
    }
}
