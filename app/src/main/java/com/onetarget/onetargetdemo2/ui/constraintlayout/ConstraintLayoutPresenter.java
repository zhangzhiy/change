package com.onetarget.onetargetdemo2.ui.constraintlayout;

import android.content.Context;

import com.onetarget.common.mvp.MvpBasePresenter;
import com.onetarget.common.mvp.rx.scheduler.MvpRxPresenter;

/**
 * Created by zzy on 2017/11/27.
 */

public class ConstraintLayoutPresenter extends MvpRxPresenter {
    private  Context mContext;
    public ConstraintLayoutPresenter(Context context){
        mContext=context;
    }
    @Override
    protected void onNext(Object data, int requestType) {

    }
}
