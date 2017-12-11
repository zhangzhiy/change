package com.onetarget.onetargetdemo2.ui.main.fragment;

import com.onetarget.onetargetdemo2.R;
import com.onetarget.common.mvp.MvpFragment;
import com.onetarget.common.mvp.IPresenter;

/**
 * Created by zzy on 2017/9/5.
 */

public class HomeFragment extends MvpFragment {
    @Override
    public IPresenter createPresenter() {
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
