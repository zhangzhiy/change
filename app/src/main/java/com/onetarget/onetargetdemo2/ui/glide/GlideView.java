package com.onetarget.onetargetdemo2.ui.glide;

import com.onetarget.onetargetdemo2.mvp.MvpView;

/**
 * Created by zzy on 2017/9/8.
 */

interface GlideView extends MvpView {
   void getDataSuccess(GlideModel data);

    void dealError(String message);
}
