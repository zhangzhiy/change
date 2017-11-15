package com.onetarget.onetargetdemo2.ui.leakcanary;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import com.onetarget.onetargetdemo2.R;
import com.onetarget.common.mvp.MvpActivity;
import com.onetarget.common.mvp.MvpPresenter;
import com.onetarget.onetargetdemo2.utils.NormalTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 内存泄露检测，设置代码在application中
 * 点击执行耗时操作，退出当前页面，反复重复几次，即可报错
 * Created by zzy on 2017/9/5.
 */

public class LeakcanaryActivity extends MvpActivity {
    @BindView(R.id.bt_costTime)
    Button btCostTime;
    @BindView(R.id.common_title)
    NormalTitleBar commonTitle;

    @Override
    protected void init() {
        commonTitle.setTitleText("LeakCanary");
        commonTitle.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_leakcanary;
    }

    @Override
    public MvpPresenter createPresenter() {
        return new LeakcanaryPersenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_costTime)
    public void onViewClicked() {
        //执行一段耗时操作，检查
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                // Do some slow work in background
                SystemClock.sleep(20000);
                return null;
            }
        }.execute();
    }
}
