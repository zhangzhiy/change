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
 * Created by zzy on 2016/8/19.
 */
public class GlideLoader {

        private volatile static GlideLoader imageLoader;

        private GlideLoader() {
        }

        public static GlideLoader getInstance() {
            if (imageLoader == null) {
                synchronized (GlideLoader.class) {
                    if (imageLoader == null) {
                        imageLoader = new GlideLoader();
                    }
                }
            }
            return imageLoader;
        }

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FORWARD_SLASH = "/";


    RequestOptions requestOptions=new RequestOptions()
            .centerCrop()  //均衡的缩放图像
            .centerInside()
            .placeholder(R.drawable.function)
            .error(R.drawable.function)
            .priority(Priority.HIGH)
            .diskCacheStrategy(DiskCacheStrategy.NONE);
    //直接加载网络图片
    public void displayImage(Context context, String url, ImageView imageView) {
        try {
            Glide.with(context)
                    .load(url)
                    .transition(new DrawableTransitionOptions().crossFade())
                    //.crossFade()  //淡入淡出
                    .apply(requestOptions)
                    .into(imageView);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 加载失败时支持自定义图片
     * @param context
     * @param url
     * @param imageView
     * @param errorResourceId
     */
    public void displayImage1(Context context, String url, ImageView imageView,int errorResourceId) {
        try {
            Glide.with(context)
                    .load(url)
                    .into(imageView);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 加载失败时没有占位图
     * @param context
     * @param url
     * @param imageView
     */
    public void displayImageNoErrorWarning(Context context, String url, ImageView imageView) {
        try {
            Glide.with(context)
                    .load(url)
                    .into(imageView);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //加载SD卡图片
    public void displayImage(Context context, File file, ImageView imageView) {
        Glide
                .with(context)
                .load(file)
                .into(imageView);

    }

    //加载SD卡图片并设置大小
    public void displayImage(Context context, File file, ImageView imageView, int width, int height) {
        Glide
                .with(context)
                .load(file)
              //  .override(width, height)
                .into(imageView);

    }

    //加载网络图片并设置大小
    public void displayImage(Context context, String url, ImageView imageView, int width, int height) {
        Glide
                .with(context)
                .load(url)
                .into(imageView);
    }

    //加载drawable图片
    public void displayImage(Context context, int resId, ImageView imageView) {
        Glide.with(context)
                .load(resourceIdToUri(context, resId))
                .into(imageView);
    }

    //加载drawable图片显示为圆形图片
    public void displayCricleImage(Context context, int resId, ImageView imageView) {
        Glide.with(context)
                .load(resourceIdToUri(context, resId))
              //  .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    //加载网络图片显示为圆形图片
    public void displayCricleImage(Context context, String url, ImageView imageView) {
        Glide
                .with(context)
                .load(url)
               // .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    //加载SD卡图片显示为圆形图片
    public void displayCricleImage(Context context, File file, ImageView imageView) {
        Glide
                .with(context)
                .load(file)
                .into(imageView);

    }

    //将资源ID转为Uri
    public Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FORWARD_SLASH + resourceId);
    }

}