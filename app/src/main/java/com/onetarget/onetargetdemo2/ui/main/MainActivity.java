package com.onetarget.onetargetdemo2.ui.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.onetarget.onetargetdemo2.R;
import com.onetarget.onetargetdemo2.mvp.MvpActivity;
import com.onetarget.onetargetdemo2.mvp.MvpPresenter;
import com.onetarget.onetargetdemo2.ui.login.LoginActivity;
import com.onetarget.onetargetdemo2.ui.permission.PermissionActivity;
import com.onetarget.onetargetdemo2.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView, View.OnClickListener {


    private static final int REQUEST_CODE = 1;
    @BindView(R.id.bt_getMessage)
    Button btGetMessage;

    @Override
    protected void init() {
        btGetMessage.setOnClickListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(this);
    }



    @OnClick({R.id.bt_getMessage, R.id.bt_permission})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_getMessage:
               // callPhone("10086");
                Intent intent=new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_permission:
                // callPhone("10086");
                Intent intent2=new Intent(this, PermissionActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void callPhone(String phone) {

    }

    private void call() {

    }

}
