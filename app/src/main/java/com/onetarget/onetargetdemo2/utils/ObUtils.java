package com.onetarget.onetargetdemo2.utils;


import com.onetarget.onetargetdemo2.mvp.rx.scheduler.AndroidSchedulerTransformer;
import rx.Observable;

public class ObUtils {

    private static AndroidSchedulerTransformer sTransformer = new AndroidSchedulerTransformer();

    public static Observable transformOb(Observable org) {
        return org.compose(sTransformer);
    }
}
