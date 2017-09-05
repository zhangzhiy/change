package com.onetarget.onetargetdemo2.ui.transucent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.onetarget.onetargetdemo2.R;
import com.onetarget.onetargetdemo2.mvp.MvpActivity;
import com.onetarget.onetargetdemo2.mvp.MvpPresenter;
import com.onetarget.onetargetdemo2.utils.NormalTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zzy on 2017/9/4.
 * 参考：https://github.com/QMUI/QMUI_Android
 */

public class TransucentActivity extends MvpActivity {


    @BindView(R.id.bt_transucent)
    Button btTransucent;
    @BindView(R.id.bt_black)
    Button btBlack;
    @BindView(R.id.bt_write)
    Button btWrite;
    @BindView(R.id.bt_getheight)
    Button btGetheight;
    @BindView(R.id.common_title)
    NormalTitleBar commonTitle;

    @Override
    protected void init() {
        commonTitle.setTitleText("状态栏");
        commonTitle.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_translucent;
    }

    @Override
    public MvpPresenter createPresenter() {
        return new TransucentPersenter(this);
    }

    @OnClick({R.id.bt_transucent, R.id.bt_black, R.id.bt_write, R.id.bt_getheight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_transucent:
                StatusBarUtil.setStatusBarColor(this,R.color.YT_7b7b7b);
                break;
            case R.id.bt_black:
                //设置状态栏黑色字体与图标
                StatusBarUtil.StatusBarLightMode(this);
                break;
            case R.id.bt_write:
                //设置状态栏白色字体与图标
                break;
            case R.id.bt_getheight:
                break;
        }
    }
}
