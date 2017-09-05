package com.onetarget.onetargetdemo2.ui.main.fragment;

import com.onetarget.onetargetdemo2.R;
import com.onetarget.onetargetdemo2.mvp.MvpFragment;
import com.onetarget.onetargetdemo2.mvp.MvpPresenter;

/**
 * Created by zzy on 2017/9/5.
 */

public class HomeFragment extends MvpFragment {
    @Override
    public MvpPresenter createPresenter() {
        return new HomePresenter(getActivity());
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }
}
