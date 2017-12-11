package com.onetarget.onetargetdemo2.ui.navigationbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.onetarget.common.mvp.MvpActivity;
import com.onetarget.common.mvp.IPresenter;
import com.onetarget.common.view.BottomBarItem;
import com.onetarget.common.view.BottomBarLayout;
import com.onetarget.onetargetdemo2.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zzy on 2017/11/30.
 */

public class BottonNavigationBarActivity extends MvpActivity {

    @BindView(R.id.vp_content)
    ViewPager mVpContent;
    @BindView(R.id.bbl)
    BottomBarLayout mBottomBarLayout;
    private List<TabFragment> mFragmentList=new ArrayList<>();


    @Override
    protected void init() {
        initData();
        initListener();
    }
    private void initData() {

        TabFragment homeFragment = new TabFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString(TabFragment.CONTENT,"首页");
        homeFragment.setArguments(bundle1);
        mFragmentList.add(homeFragment);

        TabFragment videoFragment = new TabFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString(TabFragment.CONTENT,"视频");
        videoFragment.setArguments(bundle2);
        mFragmentList.add(videoFragment);

        TabFragment microFragment = new TabFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putString(TabFragment.CONTENT,"微头条");
        microFragment.setArguments(bundle3);
        mFragmentList.add(microFragment);

        TabFragment meFragment = new TabFragment();
        Bundle bundle4 = new Bundle();
        bundle4.putString(TabFragment.CONTENT,"我的");
        meFragment.setArguments(bundle4);
        mFragmentList.add(meFragment);
    }
    private void initListener() {
        mVpContent.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mBottomBarLayout.setViewPager(mVpContent);
        mBottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final BottomBarItem bottomBarItem, int position) {

            }
        });

        mBottomBarLayout.setUnread(0,20);//设置第一个页签的未读数为20
        mBottomBarLayout.setUnread(1,101);//设置第二个页签的未读书
        mBottomBarLayout.showNotify(2);//设置第三个页签显示提示的小红点
        mBottomBarLayout.setMsg(3,"NEW");//设置第四个页签显示NEW提示文字
    }
    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_navigation_bar;
    }

    @Override
    public IPresenter createPresenter() {
        return new BottonNavigationBarPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
