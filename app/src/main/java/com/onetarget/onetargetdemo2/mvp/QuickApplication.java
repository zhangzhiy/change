package com.onetarget.onetargetdemo2.mvp;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.onetarget.onetargetdemo2.utils.Utils;
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
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;


public class QuickApplication extends Application {

    private static QuickApplication sInstance;

    @CallSuper
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        inithotfix();
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

    private void inithotfix() {
        // initialize最好放在attachBaseContext最前面，初始化直接在Application类里面，切勿封装到其他类
        SophixManager.getInstance().setContext(this)
                .setAppVersion(Utils.getVersionName(this))
                .setAesKey(null)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                            Logger.v("code","表明补丁加载成功");
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            Logger.v("code","用户可以监听进入后台事件, 然后应用自杀");
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后调用killProcessSafely自杀，以此加快应用补丁，详见1.3.2.3
                            //SophixManager.getInstance().cleanPatches();
                        } else {
                            Logger.v("code","其它错误信息, 查看PatchStatus类说明");
                            // 其它错误信息, 查看PatchStatus类说明
                        }
                    }
                }).initialize();
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();
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
