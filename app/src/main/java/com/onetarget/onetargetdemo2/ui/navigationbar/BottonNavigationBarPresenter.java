package com.onetarget.onetargetdemo2.ui.navigationbar;

import android.content.Context;

import com.onetarget.common.mvp.rx.scheduler.MvpRxPresenter;

/**
 * Created by zzy on 2017/11/27.
 */

public class BottonNavigationBarPresenter extends MvpRxPresenter {
    private  Context mContext;
    public BottonNavigationBarPresenter(Context context){
        mContext=context;
    }
    @Override
    protected void onNext(Object data, int requestType) {

    }
}
