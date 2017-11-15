package com.onetarget.onetargetdemo2.ui.leakcanary;

import android.content.Context;

import com.onetarget.common.mvp.rx.scheduler.MvpRxPresenter;

/**
 * Created by zzy on 2017/9/5.
 */

public class LeakcanaryPersenter extends MvpRxPresenter {
    private  Context mContext;
    public LeakcanaryPersenter(Context context) {
        mContext=context;
    }

    @Override
    protected void onNext(Object data, int requestType) {

    }
}
