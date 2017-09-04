package com.onetarget.onetargetdemo2.ui.login;

import android.content.Context;
import android.text.TextUtils;

import com.onetarget.onetargetdemo2.R;
import com.onetarget.onetargetdemo2.mvp.rx.scheduler.MvpRxPresenter;
import com.onetarget.onetargetdemo2.net.ApiService;
import com.onetarget.onetargetdemo2.net.AppConstants;
import com.onetarget.onetargetdemo2.net.RetrofitUtils;
import com.onetarget.onetargetdemo2.utils.Logger;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by zzy on 2017/8/30.
 */

public class LoginPresenter extends MvpRxPresenter<ILoginView, LoginDataMode> {
    private static final String TAG = "LoginPresenter";
    private Context mContext;
    private ApiService mApiService;

    public LoginPresenter(Context context) {
        this.mContext = context;
        mApiService = RetrofitUtils.createApi(ApiService.class);
    }

    public void login(String account, String password) {
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            getView().dealNoLoginMessage();
            return;
        }
        //Observable<LoginDataMode> observable = mApiService.getLoginInfo();
        Observable<LoginDataMode> observable = mApiService.getTokenInfo().flatMap(new Func1<TokenDataMode, Observable<LoginDataMode>>() {
            @Override
            public Observable<LoginDataMode> call(TokenDataMode tokenDataMode) {
                return dealTokenMessage(tokenDataMode);
            }
        });
        subscribe(observable, mContext);
    }

    private Observable<LoginDataMode> dealTokenMessage(TokenDataMode tokenDataMode) {
        if (tokenDataMode.isRootDataValid()) {
            Logger.v(TAG,"isRootDataValid");
            if (tokenDataMode.isResponseValid()) {
                Logger.v(TAG,"isResponseValid");
                if (tokenDataMode.isDataValid()) {
                    Logger.v(TAG,"isDataValid");
                    if (!TextUtils.isEmpty(tokenDataMode.getData().getAppkey())) {
                        Logger.v(TAG,"ssssssss");
                       return mApiService.getLoginInfo();
                    }
                } else {

                    getView().dealLoginError(mContext.getString(R.string.error_data_format));
                }
            } else {
                getView().dealLoginError(tokenDataMode.getMsg());
            }
        } else {
            getView().dealLoginError(mContext.getString(R.string.server_has_error));
        }
        return null;
    }

    @Override
    protected void onNext(LoginDataMode data, int requestType) {
        if (data != null) {
            if (data.getCode() == AppConstants.NET_OK_CODE) {
                if (data.getData() != null) {
                    getView().dealLoginSuccuss(data.getData());
                }
            } else {
                getView().dealLoginError(data.getMsg());
            }
        } else {
            getView().dealLoginError(mContext.getString(R.string.server_has_error));
        }
    }
}
