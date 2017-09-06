package com.onetarget.onetargetdemo2.ui.main.homefragment;

import com.onetarget.onetargetdemo2.mvp.MvpView;

/**
 * Created by zzy on 2017/9/6.
 */

public interface HomeView extends MvpView {
    void initRecyclerView(HomeModel homeModel);

    void dealError(String message);
}
