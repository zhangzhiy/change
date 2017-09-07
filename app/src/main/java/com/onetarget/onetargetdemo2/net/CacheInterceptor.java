package com.onetarget.onetargetdemo2.net;

import com.onetarget.onetargetdemo2.mvp.QuickApplication;
import com.onetarget.onetargetdemo2.utils.Utils;

import java.io.IOException;


import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 缓存处理类
 * Created by zzy on 2017/1/12.
 */
public class CacheInterceptor implements Interceptor {
    /**
     * 设缓存有效期为两天
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!Utils.isNetConnect(QuickApplication.getInstance())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        Response originalResponse = chain.proceed(request);
        if (Utils.isNetConnect(QuickApplication.getInstance())) {
            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            //String cacheControl = request.cacheControl().toString();
           // String cacheControl="public,max-age=0";
            //当然如果你想在有网络的情况下都直接走网络，那么只需要
            //将其超时时间设置为0即可:String cacheControl="Cache-Control:public,max-age=0"
            String cacheControl="Cache-Control:public,max-age=0";
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        } else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + AppConstants.CACHE_STALE_SEC)
                    .removeHeader("Pragma")
                    .build();
        }
    }
}
