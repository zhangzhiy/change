package com.onetarget.onetargetdemo2.ui.constraintlayout;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.onetarget.common.mvp.MvpActivity;
import com.onetarget.common.mvp.IPresenter;
import com.onetarget.onetargetdemo2.R;
import com.onetarget.onetargetdemo2.utils.NormalTitleBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zzy on 2017/11/27.
 */

public class ConstrainLayoutActivity extends MvpActivity {
    @BindView(R.id.common_title)
    NormalTitleBar commonTitle;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_regist)
    Button btRegist;
    @BindView(R.id.guideline2)
    Guideline guideline2;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.ll_bg)
    ConstraintLayout llBg;

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
        return R.layout.activity_constraint_layout;
    }

    @Override
    public IPresenter createPresenter() {
        return new ConstraintLayoutPresenter(this);
    }



    @OnClick({R.id.button2, R.id.button3, R.id.button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button2:
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                button4.setVisibility(View.GONE);
                break;
        }
    }
}
