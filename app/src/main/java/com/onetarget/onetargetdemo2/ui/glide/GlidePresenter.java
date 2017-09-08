package com.onetarget.onetargetdemo2.ui.glide;

import android.content.Context;

import com.bumptech.glide.annotation.GlideModule;
import com.onetarget.onetargetdemo2.mvp.MvpPresenter;
import com.onetarget.onetargetdemo2.mvp.rx.scheduler.MvpRxPresenter;
import com.onetarget.onetargetdemo2.net.ApiService;
import com.onetarget.onetargetdemo2.net.AppConstants;
import com.onetarget.onetargetdemo2.net.RetrofitUtils;

import rx.Observable;

/**
 * Created by zzy on 2017/9/8.
 */

class GlidePresenter extends MvpRxPresenter<GlideView,GlideModel> {
    private static final String TAG ="HomePresenter" ;
    private Context mContext;
    private ApiService apiService;
    public GlidePresenter(Context mContext) {
        this.mContext=mContext;
        apiService= RetrofitUtils.createApi(ApiService.class);
    }

    public void getPicURl(){
        Observable<GlideModel> glideInfo = apiService.getGlideInfo();
        subscribe(glideInfo,mContext);
    }

    @Override
    protected void onNext(GlideModel data, int requestType) {
        if(data!=null&&data.getCode()== AppConstants.NET_OK_CODE){
            getView().getDataSuccess(data);
        }else {
            getView().dealError("数据加载失败");
        }

    }
}
