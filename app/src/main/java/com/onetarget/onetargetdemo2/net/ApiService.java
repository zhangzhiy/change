package com.onetarget.onetargetdemo2.net;




import com.bumptech.glide.annotation.GlideModule;
import com.onetarget.onetargetdemo2.ui.glide.GlideModel;
import com.onetarget.onetargetdemo2.ui.login.LoginDataMode;
import com.onetarget.onetargetdemo2.ui.login.TokenDataMode;
import com.onetarget.onetargetdemo2.ui.main.homefragment.HomeModel;

import retrofit2.http.GET;
import rx.Observable;

public interface ApiService {

    @GET("login_data_copy.json")
    Observable<LoginDataMode> getLoginInfo();

    @GET("appKey.json")
    Observable<TokenDataMode> getTokenInfo();

    @GET("home.json")
    Observable<HomeModel> getHomeInfo();

    @GET("glide.json")
    Observable<GlideModel> getGlideInfo();
}
