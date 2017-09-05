package com.onetarget.onetargetdemo2.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.onetarget.onetargetdemo2.R;
import com.onetarget.onetargetdemo2.mvp.MvpActivity;
import com.onetarget.onetargetdemo2.utils.NormalTitleBar;
import com.onetarget.onetargetdemo2.utils.ToastUtil;
import com.onetarget.onetargetdemo2.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zzy on 2017/8/30.
 */

public class LoginActivity extends MvpActivity<ILoginView, LoginPresenter> implements ILoginView {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.ll_bg)
    LinearLayout llBg;
    @BindView(R.id.common_title)
    NormalTitleBar commonTitle;
    @Override
    protected void init() {
        Utils.hideKeybord(llBg,this);

        commonTitle.setTitleText("登录");
        commonTitle.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }


    @OnClick(R.id.bt_login)
    public void onViewClicked() {
        String account = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        getPresenter().login(account, password);
    }

    @Override
    public void dealNoLoginMessage() {
        ToastUtil.showToast(this, "请输入用户名和密码");
    }

    @Override
    public void dealLoginError(String message) {
        ToastUtil.showToast(this, message);
    }

    @Override
    public void dealLoginSuccuss(LoginDataMode.LoginMode data) {
        ToastUtil.showToast(this, "登录成功");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
