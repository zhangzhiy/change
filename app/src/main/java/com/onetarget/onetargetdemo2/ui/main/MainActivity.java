package com.onetarget.onetargetdemo2.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.onetarget.onetargetdemo2.R;
import com.onetarget.onetargetdemo2.mvp.MvpActivity;
import com.onetarget.onetargetdemo2.ui.leakcanary.LeakcanaryActivity;
import com.onetarget.onetargetdemo2.ui.login.LoginActivity;
import com.onetarget.onetargetdemo2.ui.permission.PermissionActivity;
import com.onetarget.onetargetdemo2.ui.transucent.TransucentActivity;
import com.onetarget.onetargetdemo2.utils.NormalTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView, View.OnClickListener {


    private static final int REQUEST_CODE = 1;
    @BindView(R.id.bt_getMessage)
    Button btGetMessage;
    @BindView(R.id.common_title)
    NormalTitleBar commonTitle;

    @Override
    protected void init() {
        btGetMessage.setOnClickListener(this);
        commonTitle.setTitleText("首页");
        commonTitle.setBackVisibility(false);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(this);
    }


    @OnClick({R.id.bt_getMessage, R.id.bt_permission, R.id.bt_transucent})
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.bt_getMessage:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_permission:
                intent = new Intent(this, PermissionActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_transucent:
                intent = new Intent(this, LeakcanaryActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void callPhone(String phone) {

    }

    private void call() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
