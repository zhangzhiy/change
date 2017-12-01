package com.onetarget.onetargetdemo2.ui.main.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.onetarget.onetargetdemo2.R;
import com.onetarget.common.mvp.MvpFragment;
import com.onetarget.common.mvp.MvpPresenter;
import com.onetarget.onetargetdemo2.ui.constraintlayout.ConstrainLayoutActivity;
import com.onetarget.onetargetdemo2.ui.filedownload.FileDownloadActivity;
import com.onetarget.onetargetdemo2.ui.leakcanary.LeakcanaryActivity;
import com.onetarget.onetargetdemo2.ui.login.LoginActivity;
import com.onetarget.onetargetdemo2.ui.navigationbar.BottonNavigationBarActivity;
import com.onetarget.onetargetdemo2.ui.permission.PermissionActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zzy on 2017/9/5.
 */

public class PersonCenterFragment extends MvpFragment {
    @BindView(R.id.bt_getMessage)
    Button btGetMessage;
    @BindView(R.id.bt_permission)
    Button btPermission;
    @BindView(R.id.bt_transucent)
    Button btTransucent;

    @Override
    public MvpPresenter createPresenter() {
        return new PersonCenterPresenter(getActivity());
    }

    @Override
    protected void init() {

    }

    @OnClick({R.id.bt_getMessage, R.id.bt_permission,
            R.id.bt_transucent,R.id.bt_constraint_layout,
            R.id.bt_navigation_bar,R.id.bt_file_download})
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.bt_getMessage:
                intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_permission:
                intent = new Intent(getActivity(), PermissionActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_transucent:
                intent = new Intent(getActivity(), LeakcanaryActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_constraint_layout:
                intent = new Intent(getActivity(), ConstrainLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_navigation_bar:
                intent = new Intent(getActivity(), BottonNavigationBarActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_file_download:
                intent = new Intent(getActivity(), FileDownloadActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_person_center;
    }


}
