package com.onetarget.onetargetdemo2.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

/**
 * Created by zzy on 2017/9/7.
 */
@GlideModule
public class MyGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        //设置内存缓存
       int memeryCacheSizeBytes=1024*1024*20;//20mb
        builder.setMemoryCache(new LruResourceCache(memeryCacheSizeBytes));

        int disCacheSizeBytes=1024*1024*100;
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context,"imageCache",disCacheSizeBytes));

    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        //配置glide网络加载框架
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }

    @Override
    public boolean isManifestParsingEnabled() {
        //不使用清单文件的配置，减少初始化时间
        return false;
    }
}
