package com.onetarget.onetargetdemo2.ui.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.onetarget.onetargetdemo2.R;
import com.onetarget.onetargetdemo2.mvp.MvpActivity;
import com.onetarget.onetargetdemo2.utils.FragmentSkipUtil;
import com.onetarget.onetargetdemo2.utils.NormalTitleBar;

import butterknife.BindView;

public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView,  BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.common_title)
    NormalTitleBar commonTitle;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.navigation_menu)
    BottomNavigationView navigationMenu;

    @Override
    protected void init() {
        commonTitle.setTitleText("首页");
        commonTitle.setBackVisibility(false);
        navigationMenu.setOnNavigationItemSelectedListener(this);
        FragmentSkipUtil.switchMainFragment(this,null,1);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void callPhone(String phone) {


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_home:
                FragmentSkipUtil.switchMainFragment(this,null,1);
                return true;
            case R.id.navigation_function:
                FragmentSkipUtil.switchMainFragment(this,null,2);
                return true;
            case R.id.navigation_person_center:
                FragmentSkipUtil.switchMainFragment(this,null,3);
                return true;
        }
        return false;
    }

}
