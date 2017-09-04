package com.onetarget.onetargetdemo2.ui.permission;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.onetarget.onetargetdemo2.R;
import com.onetarget.onetargetdemo2.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zzy on 2017/9/1.
 * 参考：https://github.com/yanzhenjie/AndPermission/blob/master/README-CN.md
 */

public class PermissionActivity extends MvpActivity<IPermissionView, PermissionPersenter> implements IPermissionView {
    @BindView(R.id.bt_save_data)
    Button btSaveData;
    @BindView(R.id.bt_call)
    Button btCall;

    @Override
    protected void init() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_permission;
    }

    @Override
    public PermissionPersenter createPresenter() {
        return new PermissionPersenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_save_data, R.id.bt_call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_save_data:
                getPresenter().saveData();
                break;
            case R.id.bt_call:
                break;
        }
    }


}
