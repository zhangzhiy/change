package com.onetarget.common.mvp;

import android.app.Application;
import android.os.StrictMode;
import android.support.annotation.CallSuper;

import com.squareup.leakcanary.LeakCanary;

import com.onetarget.common.utils.Logger;


public class QuickApplication extends Application {

    private static QuickApplication sInstance;

    @CallSuper
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            Logger.e("m3","--in this process--");
            return;
        }
        enabledStrictMode();
        LeakCanary.install(this);
    }


    private static void enabledStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                .detectAll() //
                .penaltyLog() //
                .penaltyDeath() //
                .build());
    }

    public static QuickApplication getInstance() {
        return sInstance;
    }


}
