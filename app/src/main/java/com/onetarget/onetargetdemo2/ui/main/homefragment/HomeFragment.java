package com.onetarget.onetargetdemo2.ui.main.homefragment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.onetarget.onetargetdemo2.R;
import com.onetarget.onetargetdemo2.mvp.MvpFragment;
import com.onetarget.onetargetdemo2.ui.glide.GlideActivity;
import com.onetarget.onetargetdemo2.utils.GlideLoader;
import com.onetarget.onetargetdemo2.utils.Logger;
import com.onetarget.onetargetdemo2.utils.ToastUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zzy on 2017/9/5.
 */

public class HomeFragment extends MvpFragment<HomeView,HomePresenter> implements HomeView{
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
        if(homeLists==null||homeLists.size()==0){
            getPresenter().getData();
        }
    }

    private void initRecyclerview() {
        rvHome.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        rvHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeAdapter = new HomeAdapter(R.layout.item_home,homeLists,getActivity());
        rvHome.setAdapter(homeAdapter);
        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(position==0){
                    Intent intent=new Intent(getActivity(), GlideActivity.class);
                    startActivity(intent);
                }
            }
        });


        //srlHome.setEnableAutoLoadmore(false);
        //srlHome.setEnableRefresh(false);
        //设置header，footer的优先级，全局<xmldefine<codedefine
        srlHome.setRefreshHeader(new com.onetarget.onetargetdemo2.utils.ClassicsHeader(getActivity()));
        srlHome.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                homeAdapter.setNewData(refreshData());
                refreshlayout.finishRefresh();
            }
        });
        srlHome.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (homeAdapter.getItemCount() > 10) {
                          //  Toast.makeText(getActivity(), "数据全部加载完毕", Toast.LENGTH_SHORT).show();
                            refreshlayout.setLoadmoreFinished(true);//将不会再次触发加载更多事件
                        }else {
                            homeAdapter.setNewData(refreshData());
                            refreshlayout.finishLoadmore();
                        }
                    }
                }, 2000);
            }
        });
    }



    private List<HomeModel.DataBean.ListDataBean> refreshData() {
         homeLists.add(homeLists.get(0));
         homeLists.add(homeLists.get(1));
         homeLists.add(homeLists.get(2));
        return homeLists;
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
//            Glide.with(getActivity()).load(bannerDataBean.getImgUrl()).into(imageView);
            GlideLoader.displayImage(getActivity(),bannerDataBean.getImgUrl(),imageView);
            Logger.v(TAG,bannerDataBean.getImgUrl());
        }
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

}
