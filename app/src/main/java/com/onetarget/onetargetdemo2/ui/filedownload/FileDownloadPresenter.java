package com.onetarget.onetargetdemo2.ui.filedownload;

import android.content.Context;

import com.onetarget.common.mvp.rx.scheduler.MvpRxPresenter;

/**
 * Created by zzy on 2017/11/27.
 */

public class FileDownloadPresenter extends MvpRxPresenter {
    private  Context mContext;
    public FileDownloadPresenter(Context context){
        mContext=context;
    }
    @Override
    protected void onNext(Object data, int requestType) {

    }
}
