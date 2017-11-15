package com.onetarget.onetargetdemo2.ui.transucent;

import android.content.Context;

import com.onetarget.common.mvp.rx.scheduler.MvpRxPresenter;

/**
 * Created by zzy on 2017/9/4.
 */

class TransucentPersenter extends MvpRxPresenter {
    private Context mContext;
    public TransucentPersenter(Context context) {
        mContext=context;
    }

    @Override
    protected void onNext(Object data, int requestType) {

    }
}
