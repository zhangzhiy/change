package com.onetarget.onetargetdemo2.api;




import com.onetarget.onetargetdemo2.ui.login.LoginDataMode;
import com.onetarget.onetargetdemo2.ui.login.TokenDataMode;

import retrofit2.http.GET;
import rx.Observable;

public interface ApiService {

    @GET("login_data_copy.json")
    Observable<LoginDataMode> getLoginInfo();

    @GET("appKey.json")
    Observable<TokenDataMode> getTokenInfo();
}
