package com.onetarget.onetargetdemo2.ui.login;

import com.onetarget.common.mvp.MvpView;

/**
 * Created by zzy on 2017/8/30.
 */

public interface ILoginView  extends MvpView{
    void dealNoLoginMessage();

    void dealLoginError(String message);

    void dealLoginSuccuss(LoginDataMode.LoginMode data);
}
