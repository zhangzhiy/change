package com.onetarget.onetargetdemo2.utils;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.onetarget.onetargetdemo2.R;
import com.onetarget.onetargetdemo2.mvp.MvpActivity;
import com.onetarget.onetargetdemo2.mvp.MvpFragment;
import com.onetarget.onetargetdemo2.ui.main.fragment.FunctionFragment;
import com.onetarget.onetargetdemo2.ui.main.homefragment.HomeFragment;
import com.onetarget.onetargetdemo2.ui.main.fragment.PersonCenterFragment;


public class FragmentSkipUtil {
    private static final String TAG = "FragmentSkipUtil";
    private static final int FRAGMENT_HOME = 1;
    private static final int FRAGMENT_FUNCTION = 2;
    private static final int FRAGMENT_PERSON_CENTER = 3;

    /**
     * 切换主页fragment的方法
     *
     * @param currentPager
     * @param activity
     * @param bundle
     */
    public static void switchMainFragment(MvpActivity activity, Bundle bundle, int currentPager) {
        if (activity == null)
            return;
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        MvpFragment fragment;
        fragment = newFragment(currentPager, bundle);
        //设置fragment切换的动画
        //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.replace(R.id.fl_content, fragment);
        ft.commitAllowingStateLoss();
    }

    public static MvpFragment newFragment(int pageType, Bundle bundle) {

        MvpFragment bf = null;
        switch (pageType) {
            case FRAGMENT_HOME:
                bf = new HomeFragment();
                break;
            case FRAGMENT_FUNCTION:
                bf = new FunctionFragment();
                break;
            case FRAGMENT_PERSON_CENTER:
                bf = new PersonCenterFragment();
                break;
        }
        if (bf != null && bundle != null) {
            bf.setArguments(bundle);
        }
        return bf;
    }
}
