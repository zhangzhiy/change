package com.onetarget.onetargetdemo2.ui.main.homefragment;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.onetarget.onetargetdemo2.R;
import com.onetarget.onetargetdemo2.mvp.MvpFragment;
import com.onetarget.onetargetdemo2.utils.Logger;
import com.onetarget.onetargetdemo2.utils.ToastUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zzy on 2017/9/5.
 */

public class HomeFragment extends MvpFragment<HomeView,HomePresenter> implements HomeView{
    @BindView(R.id.rv_header)
    MaterialHeader rvHeader;
    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    @BindView(R.id.srl_home)
    SmartRefreshLayout srlHome;
    private List<HomeModel.DataBean.ListDataBean>  homeLists = new ArrayList<>();
    private List<HomeModel.DataBean.BannerDataBean>  bannerLists = new ArrayList<>();
    private HomeAdapter homeAdapter;

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(getActivity());
    }

    @Override
    protected void init() {
        getPresenter().getData();
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
        banner.setImages(bannerLists);
        banner.start();
        homeAdapter.setHeaderView(banner);
    }

    @Override
    public void initRecyclerView(HomeModel homeModel) {
        homeLists=homeModel.getData().getListData();
        bannerLists=homeModel.getData().getBannerData();
        if(homeLists!=null&&homeLists.size()!=0){
            initRecyclerview();
        }
        if(bannerLists!=null&&bannerLists.size()!=0){
            setHeaderView();
        }
    }

    @Override
    public void dealError(String message) {
        ToastUtil.showToast(getActivity(),message);
    }

    public class GlideImageLoader extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            HomeModel.DataBean.BannerDataBean bannerDataBean= (HomeModel.DataBean.BannerDataBean) path;
            Glide.with(getActivity()).load(bannerDataBean.getImgUrl()).into(imageView);
            Logger.v("sss",bannerDataBean.getImgUrl());
        }
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

}
