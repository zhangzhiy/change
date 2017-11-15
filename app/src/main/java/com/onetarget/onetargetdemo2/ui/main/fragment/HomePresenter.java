package com.onetarget.onetargetdemo2.ui.main.fragment;

import android.content.Context;

import com.onetarget.common.mvp.rx.scheduler.MvpRxPresenter;

/**
 * Created by zzy on 2017/9/5.
 */

class HomePresenter extends MvpRxPresenter {
    private Context mContext;
    public HomePresenter(Context context) {
        mContext=context;
    }

    @Override
    protected void onNext(Object data, int requestType) {

    }


}
