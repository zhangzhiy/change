package com.onetarget.onetargetdemo2.mvp;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.squareup.leakcanary.LeakCanary;

import com.onetarget.onetargetdemo2.utils.Logger;


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
        setDefaultHeaderAndFooter();
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

    /**
     * 设置RecyclerView默认的全局footer和header
     * 优先级最低，如果在布局中写入footer和header可以直接覆盖
     * 需要在布局生成之前设置，所以建议放到Application.onCreate中
     * SmartRefreshLayout
     */
    private void setDefaultHeaderAndFooter() {
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                return new ClassicsFooter(getApplicationContext());
            }
        });
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new MaterialHeader(getApplicationContext());
            }
        });
    }
}
