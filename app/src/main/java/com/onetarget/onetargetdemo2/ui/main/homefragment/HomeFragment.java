package com.onetarget.onetargetdemo2.ui.main.homefragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.onetarget.onetargetdemo2.R;
import com.onetarget.onetargetdemo2.adapter.HomeAdapter;
import com.onetarget.onetargetdemo2.mvp.MvpFragment;
import com.onetarget.onetargetdemo2.mvp.MvpPresenter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zzy on 2017/9/5.
 */

public class HomeFragment extends MvpFragment {
    @BindView(R.id.rv_header)
    MaterialHeader rvHeader;
    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    @BindView(R.id.srl_home)
    SmartRefreshLayout srlHome;
    private List<HomeModel> homeLists;
    private HomeAdapter homeAdapter;

    @Override
    public MvpPresenter createPresenter() {
        return new HomePresenter(getActivity());
    }

    @Override
    protected void init() {
        setData();
        initRecyclerview();
        setHeaderView();
    }

    private void initRecyclerview() {
        rvHome.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        rvHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeAdapter = new HomeAdapter(R.layout.item_home,homeLists);
        rvHome.setAdapter(homeAdapter);
    }

    private void setHeaderView() {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.recyclerview_header,rvHome,false);
        Banner banner=(Banner)view;
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(imgUrls);
        banner.start();
        homeAdapter.setHeaderView(banner);
    }
    public static List<BannerModel> imgUrls = new ArrayList<BannerModel>(){{
        add(new BannerModel("最后的骑士", R.drawable.image_movie_header_48621499931969370));
        add(new BannerModel("三生三世十里桃花", R.drawable.image_movie_header_12981501221820220));
        add(new BannerModel("豆福传", R.drawable.image_movie_header_12231501221682438));
    }};
    public class GlideImageLoader extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setImageResource(((BannerModel)path).getImgUrl());
        }
    }
    private void setData() {
        homeLists = new ArrayList<>();
        homeLists.add(new HomeModel("","标题1","描述"));
        homeLists.add(new HomeModel("","标题2","描述"));
        homeLists.add(new HomeModel("","标题3","描述"));
        homeLists.add(new HomeModel("","标题4","描述"));
        homeLists.add(new HomeModel("","标题5","描述"));
        homeLists.add(new HomeModel("","标题6","描述"));
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

}
