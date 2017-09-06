package com.onetarget.onetargetdemo2.ui.main.homefragment;

import android.content.Context;

import com.onetarget.onetargetdemo2.R;
import com.onetarget.onetargetdemo2.mvp.MvpPresenter;
import com.onetarget.onetargetdemo2.mvp.rx.scheduler.MvpRxPresenter;
import com.onetarget.onetargetdemo2.net.ApiService;
import com.onetarget.onetargetdemo2.net.AppConstants;
import com.onetarget.onetargetdemo2.net.RetrofitUtils;
import com.onetarget.onetargetdemo2.utils.Logger;

import rx.Observable;

/**
 * Created by zzy on 2017/9/5.
 */

class HomePresenter extends MvpRxPresenter<HomeView,HomeModel> {
    private static final String TAG ="HomePresenter" ;
    private Context mContext;
    private ApiService apiService;
    public HomePresenter(Context context) {
        mContext=context;
        apiService= RetrofitUtils.createApi(ApiService.class);
        Logger.v(TAG,"HomePresenter");
    }

    public void getData(){
        Observable<HomeModel> homeInfo = apiService.getHomeInfo();
        subscribe(homeInfo,mContext);
    }
    @Override
    protected void onNext(HomeModel data, int requestType) {
        if(data!=null){
            if(data.getCode()== AppConstants.NET_OK_CODE){
                if(data.getData()!=null){
                    getView().initRecyclerView(data);
                }else {
                    getView().dealError(mContext.getString(R.string.error_data_format));
                }
            }else {
                getView().dealError(data.getMsg());
            }
        }else {
            getView().dealError(mContext.getString(R.string.server_has_error));
        }
    }
}
