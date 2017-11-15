package com.onetarget.onetargetdemo2.ui.main;

import android.content.Context;

import com.onetarget.common.mvp.rx.scheduler.MvpRxPresenter;
import com.onetarget.onetargetdemo2.api.ApiService;
import com.onetarget.common.net.RetrofitUtils;

/**
 * Created by zzy on 2017/8/30.
 */

public class MainPresenter extends MvpRxPresenter<MainView,MainDataMode> {
    private Context context;
    private ApiService mApiService;

    public MainPresenter(Context context) {
        this.context = context;
        mApiService = RetrofitUtils.createApi(ApiService.class);
    }

    public void call(String phone ){

    }

    @Override
    protected void onNext(MainDataMode data, int requestType) {

    }
}
