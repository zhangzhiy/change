package com.onetarget.onetargetdemo2.utils;

/**
 * Created by zzy on 2016/8/19.
 */

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.onetarget.onetargetdemo2.R;

import java.io.File;


/**
 * 图片加载工具
 * Created by zzy on 2017/9/7.
 */
public class GlideLoader {

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FORWARD_SLASH = "/";

    private static RequestOptions getRequestOptions() {
        RequestOptions options = new RequestOptions();
        //options.format(DecodeFormat.PREFER_ARGB_8888)
        // options.centerCrop()//图片显示类型
        // options.placeholder(loadingRes)//加载中图片
        // options.error(errorRes)//加载错误的图片
        // options.error(new ColorDrawable(Color.RED))// 或者是个颜色值
        // options.priority(Priority.HIGH)// 设置请求优先级
        // options.diskCacheStrategy(DiskCacheStrategy.ALL);
        // options.diskCacheStrategy(DiskCacheStrategy.RESOURCE)//仅缓存原图片
        // options.diskCacheStrategy(DiskCacheStrategy.ALL)//全部缓存
        // options.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)缓存缩略过的
        // options.onlyRetrieveFromCache(true)//仅从缓存加载
        // options.skipMemoryCache(true)//禁用缓存,包括内存和磁盘
        // options.diskCacheStrategy(DiskCacheStrategy.NONE)仅跳过磁盘缓存
        // options.override(200,200)加载固定大小的图片 //options.dontTransform()不做渐入渐出的转换
        // options.transition(new DrawableTransitionOptions().dontTransition())//同上
        // options.circleCrop()设置成圆形头像<这个是V4.0新增的>
        // options.transform(new RoundedCorners(10))设置成圆角头像<这个是V4.0新增的>
        return options;

    }


    RequestOptions requestOptions = new RequestOptions()
            .centerCrop()  //均衡的缩放图像
            .centerInside()
            .placeholder(R.drawable.function)
            .error(R.drawable.function)
            .priority(Priority.HIGH)
            .diskCacheStrategy(DiskCacheStrategy.NONE);

    /**
     * 加载网络圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void displayRoundImage(Context context, String url, ImageView imageView) {
        try {
            GlideApp.with(context)
                    .load(url)
                    .placeholder(R.drawable.function)
                    .error(R.drawable.function)
                    .circleCrop()//圆形图片
                    .transition(new DrawableTransitionOptions().crossFade())//淡入淡出
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 直接加载网络图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void displayImage(Context context, String url, ImageView imageView) {
        try {
            GlideApp.with(context)
                    .load(url)
                    .placeholder(R.drawable.function)
                    .error(R.drawable.function)
                    .transition(new DrawableTransitionOptions().crossFade())//淡入淡出
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载失败时支持自定义图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param errorResourceId
     */
    public static void displayImage(Context context, String url, ImageView imageView, int errorResourceId) {
        try {
            GlideApp.with(context)
                    .load(url)
                    .error(errorResourceId)
                    .transition(new DrawableTransitionOptions().crossFade())//淡入淡出
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //加载SD卡图片
    public static void displaySDImage(Context context, File file, ImageView imageView) {
        try {
            GlideApp.with(context)
                    .load(file)
                    .transition(new DrawableTransitionOptions().crossFade())//淡入淡出
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //加载SD卡图片并设置大小
    public static void displaySDImage(Context context, File file, ImageView imageView, int width, int height) {
        try {
            GlideApp.with(context)
                    .load(file)
                    .override(width, height)
                    .transition(new DrawableTransitionOptions().crossFade())//淡入淡出
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //加载drawable图片
    public static  void displayDrawableImage(Context context, int resId, ImageView imageView) {
        try {
            GlideApp.with(context)
                    .load(resId)
                    .circleCrop()//圆形图片
                    .transition(new DrawableTransitionOptions().crossFade())//淡入淡出
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}